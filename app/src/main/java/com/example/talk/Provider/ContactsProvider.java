package com.example.talk.Provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.talk.dbHelper.ContactOpenHelper;

import java.security.PublicKey;

import static com.example.talk.dbHelper.ContactOpenHelper.*;

public class ContactsProvider extends ContentProvider {

    /*
    主机地址的常量-->当前类的完整路径
    * getCanonicalName();获取一个类的完整路径
    * */

    public static final String AUTHORITIES= ContactsProvider.class.getCanonicalName();
    /*地址匹配对象*/
    static UriMatcher uriMatcher;

//    对应联系人表的一个uri常量
    public static final Uri URI_CONTACT=Uri.parse("content://"+AUTHORITIES+"/contact");

    public static final int CONTACT=1;

    static {
        uriMatcher=new UriMatcher(uriMatcher.NO_MATCH);
//        添加一个匹配规则
        uriMatcher.addURI(AUTHORITIES,"/contact",CONTACT);
//        content://com.example.talk.Provider.ContactsProvider/contact-->CONTACTS
    }

    private ContactOpenHelper helper;
    @Override
    public boolean onCreate() {
        helper=new ContactOpenHelper(getContext());
        if (helper!=null){
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int code=uriMatcher.match(uri);
        Cursor cursor=null;
        switch (code){
            case CONTACT:
                SQLiteDatabase db=helper.getReadableDatabase();
                cursor=db.query(Tab_CONTACT,projection,selection,selectionArgs,null,null,sortOrder);
                System.out.println("Query SUCCESSFUL!$$$$$$$$$CONTACT$$$$$$$$$");
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        /*
        * 数据存放到sqlite-->创建db文件，建立表-->sqliteOpenHelper
        */
        int code=uriMatcher.match(uri);
        switch (code){
            case CONTACT:
                SQLiteDatabase db=helper.getWritableDatabase();
               long id = db.insert(Tab_CONTACT,"",values);
                if (id!=-1){
                    System.out.println("INSERT SUCCESSFUL!$$$$$$$$$CONTACT$$$$$$$$$");
                    //拼接最新uri
                    uri=ContentUris.withAppendedId(uri,id);
                    getContext().getContentResolver().notifyChange(ContactsProvider.URI_CONTACT, null);
                }
               break;
            default:
                break;
        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code=uriMatcher.match(uri);
        int deleteCount=0;
        switch (code){
            case CONTACT:
                SQLiteDatabase db=helper.getWritableDatabase();
                /*return的是行数*/
                deleteCount=db.delete(Tab_CONTACT,selection,selectionArgs);
                if (deleteCount>0){
                    System.out.println("DELETE SUCCESSFUL!$$$$$$$$$CONTACT$$$$$$$$$");
                }
                break;
            default:
                break;
        }
        return deleteCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code=uriMatcher.match(uri);
        int updateCount=0;
        switch (code){
            case CONTACT:
                SQLiteDatabase db=helper.getWritableDatabase();
                updateCount=db.update(Tab_CONTACT,values,selection,selectionArgs);
                if (updateCount>0){
                    System.out.println("UPDATE SUCCESSFUL!$$$$$$$$$CONTACT$$$$$$$$$");
                }
                break;
            default:
                break;
        }
        return updateCount;
    }
}
