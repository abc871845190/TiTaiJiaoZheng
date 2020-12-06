package com.example.titaijiaozheng;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.xuexiang.xui.XUI;

public class MyApplication extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getBaseContext();
        MultiDex.install(this);
        XUI.init(this);
        XUI.debug(true);
    }

    public static Context getAppContext() {
        return appContext;
    }
}
