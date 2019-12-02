package com.example.linj.myapplication.utils.third;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.linj.myapplication.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shenhua on 2018/8/3.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
public class RetrofitApp {

    private static final long CONNECT_TIMEOUT = 30000;
    private static final long READ_TIMEOUT = 10000;
    private static final long WRITE_TIMEOUT = 10000;
    private static final String CACHE_DIR = "retrofit";
    private static final long CACHE_SIZE = 1024 * 1024 * 10;
    private static final String TAG = "RetrofitApp";
    private static RetrofitApp sInstance = null;
    private static String sBaseUrl = "";
    private Retrofit mRetrofit;

    public static void inject(String baseUrl) {
        sBaseUrl = baseUrl;
    }

    public static String baseUrl() {
        return sBaseUrl;
    }

    /**
     * 无缓存
     */
    public static RetrofitApp with() {
        if (sInstance == null) {
            synchronized (RetrofitApp.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitApp(null);
                }
            }
        }
        return sInstance;
    }

    public static RetrofitApp with(Context context) {
        if (sInstance == null) {
            synchronized (RetrofitApp.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitApp(context);
                }
            }
        }
        return sInstance;
    }

    private RetrofitApp(@Nullable Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        if (BuildConfig.DEBUG) {
            RetrofitLoggingInterceptor loggingInterceptor = new RetrofitLoggingInterceptor();
            loggingInterceptor.setLevel(RetrofitLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        builder.addInterceptor(new RetrofitBaseUrlInterceptor());
        if (context != null) {
            builder.cache(new Cache(new File(context.getApplicationContext().getCacheDir()
                    + File.separator + CACHE_DIR), CACHE_SIZE));
            builder.addInterceptor(new RetrofitGetCacheInterceptor(context.getApplicationContext()));
            builder.addNetworkInterceptor(new RetrofitGetCacheInterceptor(context.getApplicationContext()));
        }
        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(client)
                .build();
    }

    /**
     * 日志拦截器
     */
    private class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            Log.d(TAG, "--> " + request.method() + ' ' + request.url());
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                Log.d(TAG, "Content-Type: " + requestBody.contentType());
                Log.d(TAG, "Content-Length: " + requestBody.contentLength());
            }
            Log.d(TAG, "--> END " + request.method());

            long startNs = System.nanoTime();
            Response response;
            try {
                response = chain.proceed(request);
            } catch (Exception e) {
                Log.d(TAG, "<-- HTTP FAILED: " + e);
                throw e;
            }
            long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
            Log.d(TAG, "<-- "
                    + response.code()
                    + (response.message().isEmpty() ? "" : ' ' + response.message())
                    + ' ' + response.request().url()
                    + " (" + tookMs + "ms)");

            return response;
        }
    }


    public <T> T load(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }
}
