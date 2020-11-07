package com.example.titaijiaozheng.Utils;

import android.util.Log;

public class LogUtils {
    //控制log的输出

    //定义等级，通过条件就可以控制
    //当前等级
    private static int currentLevel = 4;
    //调试等级
    private static final int DEBUG_LEVEL = 4;
    //info等级
    private static final int INFO_LEVEL = 3;
    //警告级别
    private static final int WARRING_LEVEL = 2;
    //错误级别
    private static final int ERROR_LEVEL = 1;

    public static void d(Object object,String log){
        if(currentLevel>=DEBUG_LEVEL){
            Log.d(object.getClass().getSimpleName(),log);
        }
    }
    public static void i(Object object,String log){
        if(currentLevel>=INFO_LEVEL){
            Log.i(object.getClass().getSimpleName(),log);
        }
    }
    public static void w(Object object,String log){
        if(currentLevel>=WARRING_LEVEL){
            Log.w(object.getClass().getSimpleName(),log);
        }
    }
    public static void e(Object object,String log){
        if(currentLevel>=ERROR_LEVEL){
            Log.e(object.getClass().getSimpleName(),log);
        }
    }
}
