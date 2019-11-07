package com.bwie.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bwie.retrofitdemo.entity.LoginUserEntity;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxjavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        studyRxjqva();
    }

    /**
     * 观察者：四个，observaale，observer，订阅关系，事件
     */
    private void studyRxjqva() {
        //被观察者和发送事件，操作符（被观察者的api，接口，方法，网络：请求接口）
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            /**
             * 发送事件的作用
             * @param emitter 发射器：rxjava的被观察者的发射器，发送数据的
             * @throws Exception
             */
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

//                LoginUserEntity loginUserEntity = new LoginUserEntity();
//                loginUserEntity.result.phone = "132323";
                emitter.onNext("zhangsan");
                emitter.onNext("lisi");
                emitter.onNext("wangwu");
//                emitter.onNext(loginUserEntity);
//                TextView textView = null;
//                textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
            }
        });

        //观察者和接收事件
        Observer observer = new Observer() {
            /**
             * 是否订阅成功
             * @param d
             */
            @Override
            public void onSubscribe(Disposable d) {

                d.isDisposed();
                //返回fasle，成功了
                //true 失败了

            }

            /**
             * 接收数据的方法
             * @param o
             */
            @Override
            public void onNext(Object o) {
                String s = (String) o;

                System.out.println(s);

            }

            /**
             * 错误码
             * @param e
             */
            @Override
            public void onError(Throwable e) {

            }

            /**
             * 发送完成
             */
            @Override
            public void onComplete() {

            }
        };

        //订阅
        observable.subscribe(observer);//订阅

        //观察者第二
        Consumer<Integer> consumer = new Consumer<Integer>() {
            /**
             * 接收数据
             * @param integer
             */
            @Override
            public void accept(Integer integer) {

            }
        };



        //链式调用
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        }).subscribe(new Consumer<Integer>() {
            /**
             * 成功的
             * @param integer
             * @throws Exception
             */
            @Override
            public void accept(Integer integer) throws Exception {

            }
        }, new Consumer<Throwable>() {
            /**
             * 失败异常
             * @param throwable
             * @throws Exception
             */
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });


    }
}
