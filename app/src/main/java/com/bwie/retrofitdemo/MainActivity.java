package com.bwie.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bwie.retrofitdemo.api.Api;
import com.bwie.retrofitdemo.api.ApiService;
import com.bwie.retrofitdemo.entity.LoginUserEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.login)
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @OnClick(R.id.login)
    public void login(View view){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

//        //创建表单请求体
//        FormBody requestBody = new FormBody.Builder()
//                .add("phone","186")
//                .add("pwd","12121212")
//                .build();
//        //创建请求对象
//        Request request = new Request.Builder()
//                .url("")
//                .get()
//                .post(requestBody)
//                .build();
//
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            /**
//             *
//             * @param call
//             * @param response 响应数据
//             * @throws IOException
//             */
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });

        //第一步,第一类的作用，统筹规划
        Retrofit retrofit = new Retrofit.Builder()//构建者模式
                .addConverterFactory(GsonConverterFactory.create())//工厂模式，数据转换器：gson数据转换器
                .baseUrl(Api.BASE_URL)//主机名：http://172.21.34.23/活着www.baidu.com
                .client(okHttpClient)//使用okhttp配置的所有功能，配置okhttp
                .build();

        //第二步：得到请求的url，比如登录url, 初始化接口管理类：动态代理模式
        ApiService apiService = retrofit.create(ApiService.class);

        //第三步，构建请求对象执行请求

//        retrofit2.Call<LoginUserEntity> call = apiService.login("1861299", "121212");
//
//        //请求数据
//        call.enqueue(new retrofit2.Callback<LoginUserEntity>() {
//            @Override
//            public void onResponse(retrofit2.Call<LoginUserEntity> call, retrofit2.Response<LoginUserEntity> response) {
//
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<LoginUserEntity> call, Throwable t) {
//
//            }
//        });

//        apiService.login("","","").execute();

        apiService.login(Api.LOGIN_URL,"","").enqueue(new retrofit2.Callback<LoginUserEntity>() {
            @Override
            public void onResponse(retrofit2.Call<LoginUserEntity> call, retrofit2.Response<LoginUserEntity> response) {
                //回调方法的线程，是ui线程，okhttp是子线程需要handler切换线程

                LoginUserEntity loginUserEntity = response.body();//对象
                String phone = loginUserEntity.result.phone;



            }

            @Override
            public void onFailure(retrofit2.Call<LoginUserEntity> call, Throwable t) {

            }
        });



        //集合包装参数进行传输
        HashMap<String,String> params = new HashMap<>();
        params.put("phone","");
        params.put("pwd","");



        apiService.login1(params).enqueue(new Callback<LoginUserEntity>() {
            @Override
            public void onResponse(Call<LoginUserEntity> call, Response<LoginUserEntity> response) {

            }

            @Override
            public void onFailure(Call<LoginUserEntity> call, Throwable t) {

            }
        });


//        apiService.loginRxjava(params).subscribe(new Consumer<LoginUserEntity>() {
//            @Override
//            public void accept(LoginUserEntity loginUserEntity) throws Exception {
//
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//
//            }
//        });

    }
}
