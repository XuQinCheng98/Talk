package com.example.talk.utils;

import android.app.Activity;
import android.view.View;

public class StatusTextColor {
    /*Dark将沉浸式状态栏浅色时的内容改为深色*/
    public static void Drak(Activity activity){
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
