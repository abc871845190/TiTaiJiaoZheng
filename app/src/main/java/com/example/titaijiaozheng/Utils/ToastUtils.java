package com.example.titaijiaozheng.Utils;


import android.content.Context;
import android.widget.Toast;

/**
 * toast工具类
 */
public class ToastUtils {
    public static void showShortToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT);
    }

    public static void showLongToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG);
    }
}
