package com.bwie.retrofitdemo.api;

import com.bwie.retrofitdemo.entity.LoginUserEntity;
import com.bwie.retrofitdemo.entity.ProductEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 接口统一管理
 */

public interface ApiService {

    @POST//post方式,只要是post必须多配置一个请求类型
    @FormUrlEncoded//请求数类型
    Call<LoginUserEntity> login(@Url String url, @Field("phone") String phoneNum, @Field("pwd") String password);


    @POST("small/user/v1/login")//post方式,只要是post必须多配置一个请求类型
    @FormUrlEncoded//请求数类型
    Call<LoginUserEntity> login1(@FieldMap HashMap<String,String> params);



    @GET(Api.LIST_URL)
    Call<ProductEntity> getProductList(@Query("keyword") String key, @Query("page") String page, @Query("count") String c);

    @GET("small/commodity/v1/findCommodityByKeyword")
    Call<ProductEntity> getProductList1(@QueryMap HashMap<String,String> params);

    @GET("small/commodity/v1/findCommodityByKeyword")
    Call<ResponseBody> getProductList2(@QueryMap HashMap<String,String> params);

    @POST("")
    @FormUrlEncoded
    Observable<LoginUserEntity> loginRxjava(@FieldMap HashMap<String,String> params);

}
