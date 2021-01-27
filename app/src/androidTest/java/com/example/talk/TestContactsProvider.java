package com.example.talk;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.test.espresso.internal.inject.InstrumentationContext;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.talk.Provider.ContactsProvider;
import com.example.talk.dbHelper.ContactOpenHelper;
import com.example.talk.utils.PinyinUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import opensource.jpinyin.PinyinFormat;
import opensource.jpinyin.PinyinHelper;

@RunWith(AndroidJUnit4.class)

public class TestContactsProvider {

    @Test
    public void testInsert(){
        Context context= InstrumentationRegistry.getInstrumentation().getTargetContext();
        ContentValues values=new ContentValues();
        values.put(ContactOpenHelper.ContactTable.ACCOUNT,"XuQ@747912624.qq.com");
        values.put(ContactOpenHelper.ContactTable.NICKNAME,"阿诚");
        values.put(ContactOpenHelper.ContactTable.AVATAR,"0");
        values.put(ContactOpenHelper.ContactTable.PINYIN,"Xuq");
        context.getContentResolver().insert(ContactsProvider.URI_CONTACT,values);

    }
   @Test
    public void testDelete(){
        Context context= InstrumentationRegistry.getInstrumentation().getTargetContext();
        context.getContentResolver().delete(ContactsProvider.URI_CONTACT,ContactOpenHelper.ContactTable.ACCOUNT+"=?",new String[]{"XuQ@747912624.qq.com"});
    }
    @Test
    public void testUpdate(){
        Context context= InstrumentationRegistry.getInstrumentation().getTargetContext();
        ContentValues values=new ContentValues();
        values.put(ContactOpenHelper.ContactTable.ACCOUNT,"XuQ@747912624.qq.com");
        values.put(ContactOpenHelper.ContactTable.NICKNAME,"修改后的阿诚");
        values.put(ContactOpenHelper.ContactTable.AVATAR,"0");
        values.put(ContactOpenHelper.ContactTable.PINYIN,"Xuq");
        context.getContentResolver().update(ContactsProvider.URI_CONTACT,values,ContactOpenHelper.ContactTable.ACCOUNT+"=?",new String[]{"XuQ@747912624.qq.com"});
    }
    @Test
    public void testQuery(){
        Context context= InstrumentationRegistry.getInstrumentation().getTargetContext();
        Cursor cursor=context.getContentResolver().query(ContactsProvider.URI_CONTACT,null,null,null,null);
        int column_Count=cursor.getColumnCount();
        while(cursor.moveToNext()){
            for (int i=0;i<column_Count;i++){
                System.out.print(cursor.getString(i) +"   ");
            }
            System.out.println("747912624");
        }
    }
    @Test
    public void testPINYIN(){
        /*
        * PinyinHelper.convertToPinyinString("内容","分隔符", 拼音格式);
        * 已被封装到Util's
        */
        System.out.println(PinyinUtils.getPinyin("胥钦诚"));
        //
    }
}
