package com.hxb.rxjava_retrofit_dome.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.hxb.rxjava_retrofit_dome.R;
import com.hxb.rxjava_retrofit_dome.base.BaseActivity;
import com.hxb.rxjava_retrofit_dome.entity.Subject;
import com.hxb.rxjava_retrofit_dome.http.ApiService;
import com.hxb.rxjava_retrofit_dome.http.ApiSubscriber;
import com.hxb.rxjava_retrofit_dome.http.DefaultTransformer;
import com.hxb.rxjava_retrofit_dome.http.HttpMethods;
import com.hxb.rxjava_retrofit_dome.http.HttpResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.click_me_BN)
    Button clickMeBN;
    @BindView(R.id.result_TV)
    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.click_me_BN)
    public void onClick() {
        getMovie();
    }

    @Override
    protected void initData() {
    }


    protected void getMovie() {
        Map<String, String> map = new HashMap<>();
        map.put("start", "0");
        map.put("count", "10");
// TODO: 2018/1/29 Post
//        addDisposable(new HttpMethods()
//                .createService(ApiService.class)
//                .getTopMoviePost(map)
//                .compose(new DefaultTransformer<HttpResult<Subject>>())
//                .subscribeWith(new ApiSubscriber<HttpResult<Subject>>(this) {
//                    @Override
//                    public void onNext(HttpResult<Subject> entity) {
//                        resultTV.setText(entity.getSubjects().toString());
//                    }
//                }));

// TODO: 2018/1/29 Get
        addDisposable(new HttpMethods()
                .createService(ApiService.class)
                .getTopMovieGet("v2/movie/top250?",map)
                .compose(new DefaultTransformer<HttpResult<Subject>>())
                .subscribeWith(new ApiSubscriber<HttpResult<Subject>>(this) {
                    @Override
                    public void onNext(HttpResult<Subject> entity) {
                        resultTV.setText(entity.getSubjects().toString());
                    }
                }));

    }
}
