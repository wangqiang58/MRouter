package com.wuba.mobile.compiler.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;

import com.wuba.mobile.annotation.Route;
import com.wuba.mobile.compiler.util.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import static com.wuba.mobile.compiler.util.Consts.ACTIVITY_FULL_NAME;
import static com.wuba.mobile.compiler.util.Consts.APT_PACKAGE_NAME;
import static com.wuba.mobile.compiler.util.Consts.CLASS_JAVA_DOC;
import static com.wuba.mobile.compiler.util.Consts.FRAGMENT_FULL_NAME;
import static com.wuba.mobile.compiler.util.Consts.FRAGMENT_V4_FULL_NAME;
import static com.wuba.mobile.compiler.util.Consts.METHOD_HANDLE;
import static com.wuba.mobile.compiler.util.Consts.OPTION_MODULE_NAME;
import static com.wuba.mobile.compiler.util.Consts.ROUTE_TABLE;
import static com.wuba.mobile.compiler.util.Consts.ROUTE_TABLE_FULL_NAME;

/**
 * author : wangqiang
 * date : 2018/8/14
 * desc :Router注解处理器
 */
@AutoService(Processor.class) //自动注册
@SupportedOptions(OPTION_MODULE_NAME) //处理器接收参数
public class RouterProcessor extends AbstractProcessor {

    private Filer mFiler; //文件相关的辅助类
    private Elements mElements; //元素相关的辅助类
    private Logger mLogger; //日志相关的辅助类
    private String mModuleName;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElements = processingEnvironment.getElementUtils();
        mLogger = new Logger(processingEnvironment.getMessager());
        mModuleName = processingEnvironment.getOptions().get(OPTION_MODULE_NAME);
    }

    //生成java文件会在使用该注解Route的模块build目录下面
    ///build/generated/source/apt/debug
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Route.class);
        if (elements == null || elements.isEmpty()) {
            return true;
        }
        mLogger.info("RouterProcessor begin...");

        Set<TypeElement> typeElements = new HashSet<>();
        for (Element element : elements) {
            if (element.getKind().isClass() && validateClass((TypeElement) element)) {
                typeElements.add((TypeElement) element);
            }
        }
        if (mModuleName != null) {
            String validModuleName = mModuleName.replace(".", "_").replace("-", "_");
            generateRouteTable(validModuleName, typeElements);
        } else {
            throw new RuntimeException(String.format("No option %s passed to Route annation processor", OPTION_MODULE_NAME));
        }
        mLogger.info(String.format(">>> %s : RouterProcessor end", mModuleName));
        return true;
    }

    /**
     * 必须是Fragment/Activity子类，并且不能注解在abract类上
     */
    private boolean validateClass(TypeElement typeElement) {
        if (!isSubtype(typeElement, ACTIVITY_FULL_NAME) && !isSubtype(typeElement, FRAGMENT_V4_FULL_NAME)
                && !isSubtype(typeElement, FRAGMENT_FULL_NAME)) {
            mLogger.error(typeElement, String.format("%s is not a subclass of Activity or Fragment.",
                                                     typeElement.getSimpleName().toString()));
            return false;
        }
        Set<Modifier> modifiers = typeElement.getModifiers();
        // abstract class.
        if (modifiers.contains(Modifier.ABSTRACT)) {
            mLogger.error(typeElement, String.format("The class %s is abstract. You can't annotate abstract classes with @%s.",
                                                     (typeElement).getQualifiedName(), Route.class.getSimpleName()));
            return false;
        }
        return true;
    }

    private boolean isSubtype(Element typeElement, String type) {
        return processingEnv.getTypeUtils().isSubtype(typeElement.asType(),
                                                      processingEnv.getElementUtils().getTypeElement(type).asType());
    }

    private void generateRouteTable(String moduleName, Set<TypeElement> elements) {
        // Map<String, Class<?>> map
        ParameterizedTypeName mapTypeName = ParameterizedTypeName.get(ClassName.get(Map.class),
                                                                      ClassName.get(String.class), ParameterizedTypeName.get(ClassName.get(Class.class),
                                                                                                                             WildcardTypeName.subtypeOf(Object.class)));
        ParameterSpec mapParameterSpec = ParameterSpec.builder(mapTypeName, "map").build();

        MethodSpec.Builder methodHandle = MethodSpec.methodBuilder(METHOD_HANDLE)
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(mapParameterSpec);

        // 记录path->element，防止重复的route path
        Map<String, String> pathRecorder = new HashMap<>();

        for (TypeElement element : elements) {
            mLogger.info(String.format("Found routed target: %s", element.getQualifiedName()));
            Route route = element.getAnnotation(Route.class);
            String path = route.path();
            if (pathRecorder.containsKey(path)) {
                throw new RuntimeException(String.format("Duplicate route path: %s[%s, %s]",
                                                         path, element.getQualifiedName(), pathRecorder.get(path)));
            }
            methodHandle.addStatement("map.put($S, $T.class)", path, ClassName.get(element));
            pathRecorder.put(path, element.getQualifiedName().toString());
        }

        TypeElement interfaceType = processingEnv.getElementUtils().getTypeElement(ROUTE_TABLE_FULL_NAME);
        TypeSpec type = TypeSpec.classBuilder(capitalize(moduleName) + ROUTE_TABLE)
                .addSuperinterface(ClassName.get(interfaceType))
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodHandle.build())
                .addJavadoc(CLASS_JAVA_DOC)
                .build();
        try {
            JavaFile.builder(APT_PACKAGE_NAME, type).build().writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(Route.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    private String capitalize(CharSequence self) {
        return self.length() == 0 ? "" :
                "" + Character.toUpperCase(self.charAt(0)) + self.subSequence(1, self.length());
    }
}
