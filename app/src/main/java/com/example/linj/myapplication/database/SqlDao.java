package com.example.linj.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author JLin
 * @date 2019/6/1
 * @describe 离线模式下本地数据库保存数据
 */
public class SqlDao {
    private DatabaseUtil databaseUtil;
    private SQLiteDatabase sqLiteDatabase;

    public SqlDao(Context context) {
        databaseUtil = new DatabaseUtil(context);
        sqLiteDatabase = databaseUtil.getWritableDatabase();
    }

    /**
     * 添加数据
     */
    public void addUser(String userId, String type, String weight) {
        System.out.println("SqlDao：" + "addUser" + "==== ");
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", userId);
        contentValues.put("type", type);
        contentValues.put("weight", weight);
        sqLiteDatabase.insert("UserInfo", null, contentValues);
        contentValues.clear();
    }

    /**
     * 添加数据
     */
    public void addRecord(String userId, String garbageId) {
        System.out.println("SqlDao：" + "addRecord" + "==== ");
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", userId);
        contentValues.put("garbage_id", garbageId);
        sqLiteDatabase.insert("RecordInfo", null, contentValues);
        contentValues.clear();
    }
}
