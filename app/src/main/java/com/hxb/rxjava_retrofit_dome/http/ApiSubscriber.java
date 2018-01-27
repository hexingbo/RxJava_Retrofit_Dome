package com.hxb.rxjava_retrofit_dome.http;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonSyntaxException;
import com.hxb.rxjava_retrofit_dome.base.CenterToast;
import com.hxb.rxjava_retrofit_dome.progress.view.LoadingDialog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;


public abstract class ApiSubscriber<T> extends ResourceSubscriber<T> {

    private LoadingDialog mDialog;

    public ApiSubscriber() { }

    public ApiSubscriber(@NonNull Context context) {
        mDialog = new LoadingDialog(context);
    }

    @Override
    public void onStart() {
        if (mDialog != null)
            mDialog.show();
        super.onStart();
    }

    @Override
    public void onComplete() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }

    /**
     * 只要链式调用中抛出了异常都会走这个回调
     */
    @Override
    public void onError(Throwable e) {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
      if (e instanceof ConnectException || e instanceof UnknownHostException) {
            CenterToast.show("网络异常，请检查网络");
        } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
            CenterToast.show("连接超时，请稍后再试！");
        } else if (e instanceof JsonSyntaxException) {
            CenterToast.show("数据解析异常");
        } else {
            CenterToast.show("服务端错误");
        }
        e.printStackTrace();
    }
}
