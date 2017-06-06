package cn.wenbo_lee.androidnews;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2017/6/6.
 */

public class MyApplication extends LitePalApplication {

    private IWXAPI iwxapi;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);   //数据库初始化

        iwxapi = WXAPIFactory.createWXAPI(this,Contants.APP_ID,true);    //注册微信API
        iwxapi.registerApp(Contants.APP_ID);
    }
}
