package com.example.linj.myapplication;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;

import com.example.linj.greendao.db.DaoMaster;
import com.example.linj.greendao.db.DaoSession;
import com.example.linj.myapplication.utils.ResourcesUtils;
import com.example.linj.myapplication.utils.ToastUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        setDatabase();
        ToastUtils.initAppContext(getApplicationContext(), false);
        ResourcesUtils.initAppContext(getApplicationContext());
    }

    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "ck-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
