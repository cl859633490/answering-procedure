package com.example.titi.help;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

        private Context context;
        public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
            super(context,name,factory,version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {  //建表//带全部参数的构造函数，此构造函数必不可少,name为数据库名称
            db.execSQL("CREATE TABLE information(\n" +
                    "             id INTEGER PRIMARY KEY NOT NULL,\n" +
                    "             name TEXT NOT NULL,\n" +
                    "             sex TEXT NOT NULL,\n" +
                    "             class TEXT NOT NULL\n" +
                    "             )");
            db.execSQL("INSERT into information(id,name,sex,class) VALUES (202001,\"张三\",\"男\",\"嵌入式1班\");");
            db.execSQL("INSERT into information(id,name,sex,class) VALUES (202002,\"王乐\",\"男\",\"嵌入式1班\");");
            db.execSQL("INSERT into information(id,name,sex,class) VALUES (202003,\"刘小慧\",\"女\",\"网编1班\");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
        //只有conCreate()和onUpgrade是抽象方法，所以重写，


    }

