#### 一：什么是路由？
 一个用于帮助 Android App 进行组件化改造的框架 —— 支持模块间的路由、通信、解耦
 
 #### 二:功能介绍
    1.支持直接解析标准URL进行跳转，并自动注入参数到目标页面中
    2.支持多模块工程使用
    3.支持依赖注入，可单独作为依赖注入框架使用
    4.支持获取Fragment
    5.支持显式、隐式跳转
    
    
#### 三:典型应用

    1.从外部URL映射到内部页面，以及参数传递与解析
    2.跨模块页面跳转，模块间解耦
    3.跨模块API调用，通过控制反转来做组件解耦
    
#### 四:基础功能
1.添加依赖与配置

    android {
      defaultConfig {
        ...
         javaCompileOptions {
            annotationProcessorOptions {
                arguments = [moduleName: project.getName()]
            }
        }
     }
    }

     dependencies {
        compile 'com.alibaba:arouter-api:x.x.x'
        annotationProcessor 'com.alibaba:arouter-compiler:x.x.x'
    ...
    }

2.初始化SDK


3.添加注解

    @Route(path = "/im/main")
     public class MainActivity extends Activity {
         ...
     }

4.发起路由操作

     //携带参数并路由页面
     Router.build("/im/first").with("args", "hello ,word!").go(MainActivity.this);
     
5.获取Fragment

      FirstFragment fragment = (FirstFragment) Router.build("/im/fragment1").getFragment(this);
#### 五:进阶用法

##### 5.1 URL方式跳转
   
1.html模板
   
     <!doctype html>
       <html lang="en">
       <head>
        <meta charset="UTF-8">
         <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Router</title>
        </head>
       <body>
       <div>This's a WebView.</div>
       <ul>
       <li>
        <a href="router://mis.android/target?id=123&status=browser">router://mis.android/target?id=123&status=browser</a>
      </li>

      <li>
        <a href="http://www.baidu.com">http://www.baidu.com</a>
      </li>
      </ul>
      </body>
     </html>


2.通过URL跳转
    

    public class SchemeFilterActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri = getIntent().getData();
        Router.build(uri).callback(new RouterCallback() {
            @Override
            public void callback(RouteStatus status, Uri uri, String message) {
                finish();
            }
        }).go(this);
    }
    }
    
    
3.AndroidManifest.xml

     <activity android:name=".SchemeFilterActivity">

            <intent-filter>
                <action android:name="android.intent.action.VIEW"></action>
                <category android:name="android.intent.category.DEFAULT"></category>
                <category android:name="android.intent.category.BROWSABLE"></category>

                <data
                    android:host="mis.android"
                    android:scheme="router"></data>
            </intent-filter>
    </activity>
    
4.解析URL中参数

     public class ImplicitActivity extends Activity {

      @Override
       protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);
        String id = getIntent().getStringExtra("id");
        Toast.makeText(this, "id:" + id, Toast.LENGTH_LONG).show();
    }
    }
    
#### 六:更多功能

1.初始化中的其他设置
   
        //开发阶段开启日志
      Router.openLog();
      
2.详细API说明

     #构建标准的路由请求
    Router.build("/im/first").with("args", "hello ,word!").go(MainActivity.this);
    
    #获取fragment
    FirstFragment fragment = (FirstFragment) Router.build("/im/fragment1").getFragment(this);
    FragmentManager fm = getFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.add(R.id.framen_container, fragment);
    ft.commitAllowingStateLoss();
    
    #路由回调
    Router.build("/im/second").callback(new RouterCallback() {
                    @Override
                    public void callback(RouteStatus status, Uri uri, String message) {
                        ...
                    }
                }).with("args", "hello ,word2!").go(MainActivity.this);
                
    #获取页面返回结果
      Router.build("/im/second").requestCode(100).callback(new RouterCallback() {
                    @Override
                    public void callback(RouteStatus status, Uri uri, String message) {

                    }
      }).with("args", "hello ,word2!").go(MainActivity.this);
   
        
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_LONG).show();
        }
    }
    
    
    
3.获取原始的URI

    String uriStr = getIntent().getStringExtra(Router.RAW_URI);
    
#### 七:FQA
 待补充
