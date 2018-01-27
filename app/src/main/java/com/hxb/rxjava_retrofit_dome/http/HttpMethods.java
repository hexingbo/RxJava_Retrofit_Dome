package com.hxb.rxjava_retrofit_dome.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xp
 *         网络请求类
 */
public class HttpMethods {

    private static final String TAG = "=====HttpMethods======";

    public static final String BASE_URL = "https://api.douban.com/";//物流baseUrl

    private static final int DEFAULT_TIMEOUT = 10000;//10s
    private static final int DEFAULT_connectTimeout = 10000;
    private static final int DEFAULT_readTimeout = 10000;

    private Retrofit retrofit;
    private OkHttpClient client;
    private  Gson gson;

    public HttpMethods(String BASE_URL) {
        client = new OkHttpClient.Builder()
                // 添加通用的Header
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("token", "123");
                        return chain.proceed(builder.build());
                    }
                })*/
                /*
                这里可以添加一个HttpLoggingInterceptor，因为Retrofit封装好了从Http请求到解析，
                出了bug很难找出来问题，添加HttpLoggingInterceptor拦截器方便调试接口
                 */
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e(TAG, "==============" + message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_connectTimeout, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_readTimeout, TimeUnit.SECONDS)
                .build();
         gson = new GsonBuilder()
//                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public HttpMethods() {
        client = new OkHttpClient.Builder()
                // 添加通用的Header
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("token", "123");
                        return chain.proceed(builder.build());
                    }
                })*/
                /*
                这里可以添加一个HttpLoggingInterceptor，因为Retrofit封装好了从Http请求到解析，
                出了bug很难找出来问题，添加HttpLoggingInterceptor拦截器方便调试接口
                 */
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e(TAG, "==============" + message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(ResponseConvertFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public HttpMethods(final String key, final String value) {
        client = new OkHttpClient.Builder()
                // 添加通用的Header
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader(key, value);
                        return chain.proceed(builder.build());
                    }
                })
                /*
                这里可以添加一个HttpLoggingInterceptor，因为Retrofit封装好了从Http请求到解析，
                出了bug很难找出来问题，添加HttpLoggingInterceptor拦截器方便调试接口
                 */
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e(TAG, "==============" + message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
         gson = new GsonBuilder()
//                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }



    public <T> T createService(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
