package com.bwie.retrofitdemo.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit工具类封装
 */
public class RetrofitUtils {

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    //属性静态私有
    private static RetrofitUtils mInstance;

    //构造方法私有
    private RetrofitUtils(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


    //实例暴露

    public static RetrofitUtils getInstance(){
        if (mInstance==null){
            synchronized (RetrofitUtils.class){
                if (mInstance==null){
                    mInstance = new RetrofitUtils();
                }
            }
        }

        return mInstance;
    }

    /**
     * 动态返回apiservice
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T createApiservice(Class<T> tClass){

        return retrofit.create(tClass);
    }
}
