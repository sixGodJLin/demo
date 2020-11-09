package com.example.linj.myapplication;

import com.example.linj.myapplication.bean.GetRandomPictureUrlRequest;
import com.example.linj.myapplication.utils.TimeUtils;
import com.example.linj.myapplication.utils.third.RetrofitApp;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author JLin
 * @date 2019/6/12
 */
public class MyTest {
    public static void main(String[] args) {
        RetrofitApp.inject("http://www.cunkou.co/cunKouApp/cunKouService/");
        GetRandomPictureUrlRequest request = new GetRandomPictureUrlRequest();

        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println("-------------------------------------------------------");
            request.setExchangeTime(TimeUtils.format(System.currentTimeMillis(), TimeUtils.Formatter.YYYY_MM_DD_HH_MM_SS_24HOUR));
            RetrofitApp.with().load(Api.class).getRandomPictureUrl(request).enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    System.out.println("000000000000000000000000000000000000000000000000000000");
                    if (response.body() == null) {
                        return;
                    }
                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {
                    System.out.println("111111111111111111111111111111111111111111111");
                }
            });
        }, 0, 7000, TimeUnit.MILLISECONDS);
    }

    interface Api {
        @POST("getRandomPictureUrl")
        Call<CommonResponse> getRandomPictureUrl(@Body GetRandomPictureUrlRequest request);
    }
}
