package com.example.linj.myapplication.utils.third;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shenhua on 2018/8/5.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class RetrofitBaseUrlInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        List<String> headerValues = request.headers("baseUrl");
        if (headerValues == null || headerValues.size() == 0) {
            return chain.proceed(request);
        }
        builder.removeHeader("baseUrl");
        String headerValue = headerValues.get(0);
        if (TextUtils.isEmpty(headerValue)) {
            throw new IllegalArgumentException("Url's value must not be null.");
        }
        HttpUrl httpUrl = HttpUrl.parse(headerValue);
        if (httpUrl == null) {
            throw new NullPointerException("Url's value parse to HttpUrl is null.");
        }
        HttpUrl newFullUrl = request.url()
                .newBuilder()
                .scheme(httpUrl.scheme())
                .host(httpUrl.host())
                .port(httpUrl.port())
                .build();
        return chain.proceed(builder.url(newFullUrl).build());
    }
}
