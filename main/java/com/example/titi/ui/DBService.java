package com.example.titi.ui;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.titi.help.MySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * 连接数据库
 */
public class DBService {

    private final SQLiteDatabase db;
    MySQLiteOpenHelper dbhelp;


    //构造方法
    @SuppressLint("SdCardPath")
    public DBService() {
        //连接数据库        //手机内部存储根目录
        	 db = SQLiteDatabase.openDatabase("/data/data/com.example.titi/databases/shui.db",null,SQLiteDatabase.OPEN_READWRITE);


    }
    //获取数据库的数据
    @SuppressLint("Range")
    public List<Question> getQuestion() {

            List<Question> list = new ArrayList<>();
        //执行sql语句
        Cursor cursor = db.rawQuery("select * from question", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            //遍历
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                Question question = new Question();
                //ID
                question.ID = cursor.getInt(cursor.getColumnIndex("Field1"));
                //问题
                question.question = cursor.getString(cursor.getColumnIndex("Field2"));
                //四个选择
                question.answerA = cursor.getString(cursor.getColumnIndex("Field3"));
                question.answerB = cursor.getString(cursor.getColumnIndex("Field4"));
                question.answerC = cursor.getString(cursor.getColumnIndex("Field5"));
                question.answerD = cursor.getString(cursor.getColumnIndex("Field6"));
                //答案
                question.answer = cursor.getInt(cursor.getColumnIndex("Field7"));
                //解析
                question.explaination = cursor.getString(cursor.getColumnIndex("Field8"));
                //设置为没有选择任何选项
                question.selectedAnswer = -1;
                list.add(question);
            }
        }
        return list;

    }
}
