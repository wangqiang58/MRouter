package com.wuba.mobile.middle;

import com.wuba.mobile.middle.app.route.MisRouter;
import com.wuba.mobile.middle.app.route.MisRouterError;
import com.wuba.mobile.middle.mis.protocol.router.Router;
import com.wuba.mobile.middle.mis.protocol.router.RouterReqBuilder;

/**
 * this is project PluginMiddleware
 *
 * @description He is so lazy that he can't write anything.
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class RouterTest {

    public static void test1() {

        Router.rootRouter = MisRouter.getInstance();
        Router.rootRouterError = MisRouterError.getInstance();

        Router.getRootRouter()
                .build("http//:mis.com?A_A1/getInfo/detail_id=1")
                .go(null);

        Router.getRootRouter()
                .build("")
                .target("A_A1")
                .action("getInfo")
                .with("detail_id",1)
                .go(null);

        Router.getRootRouter()
                .build(new RouterReqBuilder(""))
                .target("A_A1")
                .host("")
                .action("getInfo")
                .with("detail_id",1)
                .go(null);
    }

}
