package com.example.linj.myapplication.retrofit;


import android.util.Log;

import com.example.linj.myapplication.BuildConfig;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 辅助类
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public class RetrofitHelper {
    private static String TAG = "RetrofitHelper";
    private static final long CONNECT_TIMEOUT = 60L;
    private static final long READ_TIMEOUT = 30L;
    private static final long WRITE_TIMEOUT = 30L;
    private static RetrofitHelper mInstance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance() {
        synchronized (RetrofitHelper.class) {
            if (mInstance == null) {
                mInstance = new RetrofitHelper();
            }
        }
        return mInstance;
    }

    private RetrofitHelper() {
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.7:8001/")
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 获取OkHttpClient实例
     *
     * @return okHttpClient
     */
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.retryOnConnectionFailure(true);
        okHttpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new RqInterceptor());
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(new LogInterceptor());
        }
        return okHttpClient.build();
    }

    /**
     * 请求拦截器
     */
    private class RqInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("X-APP-TYPE", "android")
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * 日志拦截器
     */
    private class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String url = request.url().toString();
            String params = requestBodyToString(request.body());
            Response response = chain.proceed(request);
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            String responseString = new Gson().toJson(content);
            String log = "\n*******路径*******:\n" + url + "\n*******参数*******:\n" + params + "\n*******报文*******:\n" + responseString + "\n \n";
            Log.d(TAG, log);
            return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
        }
    }

    private String requestBodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null) {
                copy.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (final IOException e) {
            Log.e(TAG, "requestBodyToString: " + e.getMessage());
            return "did not work";
        }
    }

    public ApiService getServer() {
        return mRetrofit.create(ApiService.class);
    }
}
