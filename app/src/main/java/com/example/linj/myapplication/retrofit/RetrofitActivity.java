package com.example.linj.myapplication.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.linj.myapplication.R;
import com.example.linj.myapplication.bean.CommonResponse;
import com.example.linj.myapplication.utils.third.RetrofitApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author JLin
 * @date 2019/11/13
 * @describe retrofit demo
 */
public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        RetrofitApp.inject("http://192.168.0.7:8001/");
        RetrofitApp.with().load(ApiService.class).login("collection", "123456").enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
            }
        });

    }
}
