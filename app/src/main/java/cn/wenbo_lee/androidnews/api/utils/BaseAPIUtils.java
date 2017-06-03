package cn.wenbo_lee.androidnews.api.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016-11-26.
 */

public class BaseAPIUtils {

    public static final String BASE_URL_JUHE = "http://v.juhe.cn/";
    public static final String BASE_NEWS_KEY_JUHE = "9b9c755e7776c056534cd2b3ffc0788e";

    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseAPIUtils.BASE_URL_JUHE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
