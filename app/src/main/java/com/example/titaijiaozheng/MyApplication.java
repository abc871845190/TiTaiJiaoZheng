package com.example.titaijiaozheng;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
public class MyApplication extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getBaseContext();
        MultiDex.install(this);
    }

    public static Context getAppContext(){
        return appContext;
    }
}
