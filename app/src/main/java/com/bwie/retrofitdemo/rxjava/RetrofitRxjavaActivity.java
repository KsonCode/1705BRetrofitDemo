package com.bwie.retrofitdemo.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bwie.retrofitdemo.R;
import com.bwie.retrofitdemo.api.Api;
import com.bwie.retrofitdemo.api.CartApiService;
import com.bwie.retrofitdemo.entity.LoginUserEntity;
import com.bwie.retrofitdemo.utils.RetrofitUtils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRxjavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_rxjava);
    }

    public void request(View view) {


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor()

                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//数据转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//请求适配器，适配什么类型数据
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .build();


        //动态代理
        CartApiService cartApiService = retrofit.create(CartApiService.class);//


        //订阅之前需要配置被观察者的操作符
        //subscribeOn(Schedulers.io()) 发送事件的线程：被观察发送数据的线程
        //observeOn()，观察者接收事件的线程
        cartApiService.login("","").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LoginUserEntity>() {

            @Override
            public void accept(LoginUserEntity loginUserEntity) throws Exception {


                //绘制ui

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
            //
                Log.e("error",throwable.getMessage());
            }
        });


        RetrofitUtils.getInstance().createApiservice(CartApiService.class).login("","")
                .subscribeOn(Schedulers.io())//发送
                .observeOn(AndroidSchedulers.mainThread())//接收
                .subscribe(new Consumer<LoginUserEntity>() {
                    @Override
                    public void accept(LoginUserEntity loginUserEntity) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
