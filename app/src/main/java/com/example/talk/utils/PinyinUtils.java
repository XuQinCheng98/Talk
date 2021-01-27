package com.example.talk.utils;

import opensource.jpinyin.PinyinFormat;
import opensource.jpinyin.PinyinHelper;

public class PinyinUtils {
    public static  String getPinyin(String s){
        return PinyinHelper.convertToPinyinString(s,"", PinyinFormat.WITHOUT_TONE);
    }
}
