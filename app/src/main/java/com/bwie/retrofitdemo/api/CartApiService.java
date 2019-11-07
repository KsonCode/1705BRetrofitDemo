package com.bwie.retrofitdemo.api;

import com.bwie.retrofitdemo.entity.LoginUserEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * login，rxjava如何实现额登录接口
 */
public interface CartApiService {

    //原生表单：key，value（text），//多表单：text/file //raw，原始数据 //binary（二进制）
    @POST
    @FormUrlEncoded
    Observable<LoginUserEntity> login(@Field("phone") String phone, @Field("pwd") String pwd);


}
