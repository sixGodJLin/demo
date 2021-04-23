package com.example.linj.myapplication.utils.third;


import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.platform.Platform;
import okio.Buffer;

import static okhttp3.internal.platform.Platform.INFO;

/**
 * @author JLin
 * @date 2019/11/14
 * @describe 日志拦截器
 */
public class RetrofitLoggingInterceptor implements Interceptor {

    private static final String TAG = "Retrofit";

    private RetrofitLoggingInterceptor(RetrofitLoggingInterceptor.Logger logger) {
        this.logger = logger;
    }

    RetrofitLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    private final Logger logger;

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        System.out.println("----------> " + request.method() + ' ' + request.url());
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            System.out.println("Content-Type: " + requestBody.contentType());
            System.out.println("Content-Length: " + requestBody.contentLength());
        }
        String params = requestBodyToString(request.body());
        if (!params.isEmpty()) {
            System.out.println("Header:" + request.headers());
            System.out.println(request.method() + " params：" + params);
        }
        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            Log.e(TAG, "<---------- HTTP FAILED: " + e);
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        System.out.println("------> END " + request.method());
        System.out.println("<------ "
                + response.code()
                + (response.message().isEmpty() ? "" : ' ' + response.message())
                + ' ' + response.request().url()
                + " (" + tookMs + "ms)");
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        String responseString = JsonUtils.jsonHandle(content);
        System.out.println(response.request().url() + responseString);
        System.out.println("<---------- END HTTP");
        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
    }

    public interface Logger {
        /**
         * log
         *
         * @param message message
         */
        void log(String message);

        /**
         * A {@link Logger} defaults output appropriate for the current platform.
         */
        Logger DEFAULT = message -> Platform.get().log(INFO, message, null);
    }

    private String requestBodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            if (request != null) {
                request.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (final IOException e) {
            logger.log("requestBodyToString: " + e.getMessage());
            return "did not work";
        }
    }
}
