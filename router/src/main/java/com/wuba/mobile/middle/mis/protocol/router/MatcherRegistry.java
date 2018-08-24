package com.wuba.mobile.middle.mis.protocol.router;

import com.wuba.mobile.middle.mis.protocol.router.matcher.AbsExplicitMatcher;
import com.wuba.mobile.middle.mis.protocol.router.matcher.AbsImplicitMatcher;
import com.wuba.mobile.middle.mis.protocol.router.matcher.AbsMatcher;
import com.wuba.mobile.middle.mis.protocol.router.matcher.BrowserMatcher;
import com.wuba.mobile.middle.mis.protocol.router.matcher.DirectMatcher;
import com.wuba.mobile.middle.mis.protocol.router.matcher.ImplicitMatcher;
import com.wuba.mobile.middle.mis.protocol.router.matcher.SchemeMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * author : wangqiang
 * date : 2018/8/23
 * desc :
 */
public class MatcherRegistry {

    private static final List<AbsMatcher> ALL = new ArrayList<>();
    private static final List<AbsExplicitMatcher> explicitMatcher = new ArrayList<>();
    private static final List<AbsImplicitMatcher> implicitMatcher = new ArrayList<>();


    /**
     * 默认注册
     */
    static {
        ALL.add(new DirectMatcher());
        ALL.add(new SchemeMatcher());
        ALL.add(new ImplicitMatcher());
        ALL.add(new BrowserMatcher());
        classifyMatcher();
    }

    public static void reigster(AbsMatcher matcher) {
        if (matcher instanceof AbsExplicitMatcher) {
            ALL.add(matcher);
        }
    }

    public static List<AbsExplicitMatcher> getExplicitMatcher() {
        return explicitMatcher;
    }

    public static List<AbsImplicitMatcher> getImplicitMatcher() {
        return implicitMatcher;
    }

    public static List<AbsMatcher> getMatcher() {
        return ALL;
    }

    private static void classifyMatcher() {
        explicitMatcher.clear();
        implicitMatcher.clear();
        for (AbsMatcher matcher : ALL) {
            if (matcher instanceof AbsImplicitMatcher) {
                implicitMatcher.add((AbsImplicitMatcher) matcher);
            } else if (matcher instanceof AbsExplicitMatcher) {
                explicitMatcher.add((AbsExplicitMatcher) matcher);
            }
        }
    }
}
