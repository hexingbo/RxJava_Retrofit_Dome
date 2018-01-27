package com.hxb.rxjava_retrofit_dome.http;


import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class DefaultTransformer<T> implements FlowableTransformer<T, T> {

    public static <T> DefaultTransformer<T> create() {
        return new DefaultTransformer<>();
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<T, T>() {// 通用错误处理，判断code
                    @Override
                    public T apply(T t) {
//                        if (((HttpResult<T>)t).getStatus_code() != 10000) {
//                            throw new ApiException(((HttpResult<T>)t).getStatus_code(),
//                                    ((HttpResult<T>)t).getError_msg());
//                        }
                        return t;
                    }
                });
    }
}
