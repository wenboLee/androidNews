package cn.wenbo_lee.androidnews.api.utils;

import cn.wenbo_lee.androidnews.api.interfaces.MyNewsInterface;
import cn.wenbo_lee.androidnews.api.pojo.MyNews;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * top(头条，默认),
 * shehui(社会),
 * guonei(国内),
 * guoji(国际),
 * yule(娱乐),
 * tiyu(体育)
 * junshi(军事),
 * keji(科技),
 * caijing(财经),
 * shishang(时尚)
 * Created by Administrator on 2016-11-26.
 */

public class MyNewsUtils {

    public static Call<MyNews> getMyNews(String title) {
        Retrofit retrofit = BaseAPIUtils.getRetrofit();
        MyNewsInterface myNewsInterface = retrofit.create(MyNewsInterface.class);
        Call<MyNews> myNewsCall = myNewsInterface.getyNews(title, BaseAPIUtils.BASE_NEWS_KEY_JUHE);
        return myNewsCall;
    }

}
