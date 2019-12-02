package com.example.linj.myapplication.utils.third;

import android.content.Context;
import android.support.annotation.NonNull;


import com.example.linj.myapplication.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Retrofit Get请求时缓存策略
 * Created by shenhua on 2018/8/3.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class RetrofitGetCacheInterceptor implements Interceptor {

    private Context context;

    RetrofitGetCacheInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response;
        boolean netAvailable = NetworkUtils.isNetworkAvailable(context);
        if (netAvailable) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
            response = chain.proceed(request);
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + 60 * 60)
                    .build();
        } else {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            response = chain.proceed(request);
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached,max-stale=" + 7 * 24 * 60 * 60)
                    .build();
        }
        return response;
    }
}
