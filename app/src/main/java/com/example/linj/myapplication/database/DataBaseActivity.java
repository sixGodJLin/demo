package com.example.linj.myapplication.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.linj.greendao.db.DaoSession;
import com.example.linj.greendao.db.LocalRecordDao;
import com.example.linj.greendao.db.ServerDataDao;
import com.example.linj.greendao.db.ServerGoodsDao;
import com.example.linj.myapplication.App;
import com.example.linj.myapplication.BuildConfig;
import com.example.linj.myapplication.R;
import com.example.linj.myapplication.bean.CommonBean;
import com.example.linj.myapplication.bean.InventoryGoodsResponse;
import com.example.linj.myapplication.bean.ServerRecordResponse;
import com.example.linj.myapplication.retrofit.RetrofitHelper;
import com.example.linj.myapplication.utils.TimeUtils;
import com.example.linj.myapplication.utils.third.RetrofitApp;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class DataBaseActivity extends AppCompatActivity {

    private static final String TAG = "DataBaseActivity";
    private SqlDao sqlDao;

    private DaoSession daoSession;

    private ServerDataDao serverDataDao;
    private ServerGoodsDao serverGoodsDao;
    private LocalRecordDao localRecordDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        ButterKnife.bind(this);

        RetrofitApp.inject("http://116.62.28.38");

        sqlDao = new SqlDao(this);
        daoSession = ((App) getApplication()).getDaoSession();
        serverDataDao = daoSession.getServerDataDao();
        serverGoodsDao = daoSession.getServerGoodsDao();
        localRecordDao = daoSession.getLocalRecordDao();

        new Thread(() -> {
            for (int i = 1; i <= 80; i++) {
                for (int j = 1; j <= 6; j++) {
                    ServerGoods serverGoods = new ServerGoods();
                    serverGoods.setHuodaoIndex(i);
                    serverGoods.setGeziIndex(j);
                    serverGoods.setGoodsCode("");
                    serverGoods.setGeziStatus("");
                    serverGoods.setGoodsId("");
                    serverGoods.setGoodsType("");
                    Log.d(TAG, "----> setDatabase: " + new Gson().toJson(serverGoods));
                    serverGoodsDao.insert(serverGoods);
                }
            }
            Log.d(TAG, "----> init success: ");
        }).start();
    }

    @OnClick({R.id.insert, R.id.delete, R.id.modify, R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.insert:
                CommonBean commonBean = new CommonBean();
                commonBean.setExchangeTime(TimeUtils.format(System.currentTimeMillis(), TimeUtils.Formatter.YYYYMMDDHHMMSS));
                commonBean.setChannelId("CKGI_ANDROID");
                commonBean.setChannelNo("ANDROID");
                commonBean.setChannelPassword("ANDROID");
                commonBean.setAppVersion(BuildConfig.VERSION_NAME);
                commonBean.setMachineCode("9901571000333");

                RetrofitApp.with().load(Api.class).getServerRecord(commonBean).enqueue(new Callback<ServerRecordResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ServerRecordResponse> call, @NonNull Response<ServerRecordResponse> response) {
                        if (response.body() == null) {
                            Log.d(TAG, "获取服务器当月领取记录失败");
                            return;
                        }
                        if (response.body().getList() == null || response.body().getList().size() == 0) {
                            Log.d(TAG, "获取服务器当月领取记录失败");
                            return;
                        }
                        List<ServerData> serverDataList = new ArrayList<>();
                        for (ServerRecordResponse.ListBean listBean : response.body().getList()) {
                            ServerData serverData = new ServerData();
                            serverData.setUserCardNo(listBean.getUserCardNo());
                            serverData.setCardId(listBean.getCardId());
                            serverData.setGoodsCode(listBean.getGoodsCode());
                            serverData.setDate(TimeUtils.format(System.currentTimeMillis(), TimeUtils.Formatter.MM));
                            serverDataList.add(serverData);
                            Log.d(TAG, "----> 设备领取记录: " + new Gson().toJson(serverData));
                        }
                        serverDataDao.insertOrReplaceInTx(serverDataList);
                        Log.d(TAG, "----> 设备领取记录 end: ");
                    }

                    @Override
                    public void onFailure(@NonNull Call<ServerRecordResponse> call, @NonNull Throwable t) {
                        Log.d(TAG, "获取服务端数据失败");
                    }
                });
                break;
            case R.id.delete:
                break;
            case R.id.modify:
                break;
            case R.id.query:
                List<ServerGoods> serverGoodsList = serverGoodsDao.loadAll();
                for (ServerGoods serverGoods : serverGoodsList) {
                    Log.d(TAG, "----> onViewClicked: " + new Gson().toJson(serverGoods));
                }
                break;
            default:
                break;
        }
    }

    interface Api {
        /**
         * 获取服务端当月领取记录
         *
         * @param commonBean
         * @return
         */
        @POST("http://116.62.28.38/ckljhs/ckfd/ckfdPro/getUserBagRecordByMachine")
        Call<ServerRecordResponse> getServerRecord(@Body CommonBean commonBean);

        /**
         * 获取服务端设备商品序列信息
         *
         * @param commonBean
         * @return
         */
        @POST("http://116.62.28.38/ckljhs/ckfd/ckfdPro/getInventoryGoodsByMachine")
        Call<InventoryGoodsResponse> getInventoryGoodsByMachine(@Body CommonBean commonBean);

    }
}
