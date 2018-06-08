package com.ifeng_tech.treasuryyitong.utils;


import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by mypc on 2018/1/6.
 *
 * 子类的共有方法
 */

public abstract class BaseServer implements Observer<String> {

    public abstract void onSuccess(String myBean);
    public abstract void onErroy(String ss);

    @Override
    public void onError(Throwable e) {
        onErroy("请求失败");
    }

    @Override
    public void onNext(String myBean) {
        onSuccess(myBean);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
