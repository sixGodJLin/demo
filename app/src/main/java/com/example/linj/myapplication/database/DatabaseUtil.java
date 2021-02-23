package com.example.linj.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

/**
 *
 */
public class DatabaseUtil extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Info.db";
    private static final int DATABASE_VERSION = 1;

    DatabaseUtil(Context context) {
        super(context, getMyDatabaseName(), null, DATABASE_VERSION);
    }

    /**
     * 创建数据库
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("DatabaseUtil：" + "onCreate" + "==== ");
        createUserTable(sqLiteDatabase);
        createRecordTable(sqLiteDatabase);
    }

    /**
     * 建立数据表
     */
    private void createUserTable(SQLiteDatabase db) {
        db.execSQL("create table UserInfo(" +
                "id integer  primary key autoincrement," +
                "user_id text," +
                "type text," +
                "weight text)");
    }

    /**
     * 建立数据表
     */
    private void createRecordTable(SQLiteDatabase db) {
        db.execSQL("create table RecordInfo(" +
                "id integer  primary key autoincrement," +
                "user_id text," +
                "garbage_id text)");
    }

    /**
     * 升级数据库
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    private static String getMyDatabaseName() {
        String dbPath;
        dbPath = Environment.getExternalStorageDirectory().getPath() + "/database/";
        File dbp = new File(dbPath);
        if (!dbp.exists()) {
            dbp.mkdirs();
        }
        return dbPath + DATABASE_NAME;
    }
}
