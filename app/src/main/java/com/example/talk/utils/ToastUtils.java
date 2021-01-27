package com.example.talk.utils;

import android.content.Context;

import es.dmoral.toasty.Toasty;

public class ToastUtils {
    /**/
    public static void Error_toast(final Context context, final String text){
        ThreadUtils.runInUIThread(new Runnable() {
            @Override
            public void run() {
                Toasty.error(context,text).show();
            }
        });
    }
    public static void Succe_toast(Context context,String text){
        ThreadUtils.runInUIThread(new Runnable() {
            @Override
            public void run() {
                Toasty.success(context,text).show();
            }
        });
    }
    public static void Warn_toast(Context context,String text){
        ThreadUtils.runInUIThread(new Runnable() {
            @Override
            public void run() {
                Toasty.warning(context,text).show();
            }
        });
    }
}
