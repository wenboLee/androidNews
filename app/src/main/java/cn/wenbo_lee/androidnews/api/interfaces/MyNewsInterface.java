package cn.wenbo_lee.androidnews.api.interfaces;

import cn.wenbo_lee.androidnews.api.pojo.MyNews;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 请求地址：http://v.juhe.cn/toutiao/index
 * 请求参数：type=&key=9b9c755e7776c056534cd2b3ffc0788e
 * 请求方式：GET
 *
 * Created by Administrator on 2016-11-26.
 */

public interface MyNewsInterface {

    @GET("/toutiao/index")
    Call<MyNews> getyNews(@Query("type") String type, @Query("key") String key);
}
