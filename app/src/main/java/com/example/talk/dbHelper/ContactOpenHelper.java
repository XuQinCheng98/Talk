package com.example.talk.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class ContactOpenHelper extends SQLiteOpenHelper {

    public static final String Tab_CONTACT="tab_contact";

    public class ContactTable implements BaseColumns{//默认添加一列 _id
        /*
        * 1. _id主键
        * 2. account账号
        * 3. nickname昵称
        * 4. avatar头像
        * 5. pinyin账号拼音
        * */
        public static final String ACCOUNT="account";
        public static final String NICKNAME="nickname";
        public static final String AVATAR="avatar";
        public static final String PINYIN="pinyin";

    }

    public ContactOpenHelper(@Nullable Context context) {
        super(context, "contact.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+Tab_CONTACT+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ContactTable.ACCOUNT+" TEXT,"
                +ContactTable.AVATAR+" TEXT,"
                 +ContactTable.NICKNAME+" TEXT,"
                 +ContactTable.PINYIN+" TEXT);";
        //表结构
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
