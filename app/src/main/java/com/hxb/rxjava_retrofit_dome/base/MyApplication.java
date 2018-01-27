package com.hxb.rxjava_retrofit_dome.base;

import android.app.Application;


/**
 * @author: xp
 * @date: 2017/1/3
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppManager.getInstance().init(this);

    }

}
