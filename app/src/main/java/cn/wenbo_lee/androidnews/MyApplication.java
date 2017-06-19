package cn.wenbo_lee.androidnews;



import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * 启动
 * Created by Administrator on 2017/6/6.
 */

public class MyApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);   //数据库初始化

    }
}
