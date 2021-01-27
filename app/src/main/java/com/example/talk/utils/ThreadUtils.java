package com.example.talk.utils;


import android.os.Handler;

public class ThreadUtils {
    /*子线程执行*/
    public static void runThread(Runnable task){
        new Thread(task).start();
    }
    /*主线程的一个Handler*/
    public static Handler mhandler=new Handler();

    /*UI线程执行task*/
    public static void runInUIThread(Runnable task){
        mhandler.post(task);
    }
}
