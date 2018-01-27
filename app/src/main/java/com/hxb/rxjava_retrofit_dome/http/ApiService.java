package com.hxb.rxjava_retrofit_dome.http;


import com.hxb.rxjava_retrofit_dome.entity.Subject;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    //https://api.douban.com/v2/movie/top250?start=0&count=10
    @FormUrlEncoded
    @POST("v2/movie/top250")
    Flowable<HttpResult<Subject>> getTopMovie(@FieldMap Map<String, String> params);

}