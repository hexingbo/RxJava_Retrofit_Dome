package com.hxb.rxjava_retrofit_dome.http;


import com.hxb.rxjava_retrofit_dome.entity.Subject;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {

    //https://api.douban.com/v2/movie/top250?start=0&count=10
    @FormUrlEncoded
    @POST("v2/movie/top250")
    Flowable<HttpResult<Subject>> getTopMoviePost(@FieldMap Map<String, String> params);

    //获取物流信息
    @GET
    Flowable<HttpResult<Subject>> getTopMovieGet(@Url String s, @QueryMap Map<String, String> map);



}