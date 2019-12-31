package com.example.linj.myapplication.utils;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

import com.example.linj.myapplication.bean.BaiduGetNumberResponse;
import com.example.linj.myapplication.retrofit.ApiService;
import com.example.linj.myapplication.bean.BaiduTokenResponse;
import com.example.linj.myapplication.utils.third.RetrofitApp;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author JLin
 * @date 2019/12/31
 * @describe
 */
public class BaiduApiUtils {
    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     *
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static void getAuth() {
        RetrofitApp.with().load(ApiService.class).getToken("client_credentials", "GBb4Q9lBVMYEf0Gr8wSPPIrN", "zl9ZDFUzsRojV6LslQ027IGjeFXkOvjl")
                .enqueue(new Callback<BaiduTokenResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<BaiduTokenResponse> call, @NonNull Response<BaiduTokenResponse> response) {
                        if (response.body() != null) {
                            System.out.println("BaiduApiUtils:" + "onResponse" + "====" + new Gson().toJson(response.body()));
                            Constants.BAIDU_API_TOKEN = response.body().getAccessToken();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<BaiduTokenResponse> call, @NonNull Throwable t) {

                    }
                });
        System.out.println("BaiduApiUtils:" + "getAuth" + "====");
    }

    public static void getNumbers(Bitmap bitmap) {
        BaiduNumberRequest request = new BaiduNumberRequest();
        request.setImage(encodeImage(bitmap));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), request.toString());
        RetrofitApp.with().load(ApiService.class).getNumbers(Constants.BAIDU_API_TOKEN, requestBody).enqueue(new Callback<BaiduGetNumberResponse>() {
            @Override
            public void onResponse(@NonNull Call<BaiduGetNumberResponse> call, @NonNull Response<BaiduGetNumberResponse> response) {
                System.out.println("BaiduApiUtils:" + "onResponse" + "====" + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<BaiduGetNumberResponse> call, @NonNull Throwable t) {

            }
        });
    }

    public static void getNumbers(String fileName) {
        BaiduNumberRequest request = new BaiduNumberRequest();
        request.setImage(imageToBase64(fileName));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), request.toString());
        RetrofitApp.with().load(ApiService.class).getNumbers(Constants.BAIDU_API_TOKEN, requestBody).enqueue(new Callback<BaiduGetNumberResponse>() {
            @Override
            public void onResponse(@NonNull Call<BaiduGetNumberResponse> call, @NonNull Response<BaiduGetNumberResponse> response) {
                System.out.println("BaiduApiUtils:" + "onResponse" + "====" + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<BaiduGetNumberResponse> call, @NonNull Throwable t) {

            }
        });
    }

    public static class BaiduNumberRequest {
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @NonNull
        @Override
        public String toString() {
            return "image=" + image + "&recognize_granularity=big&detect_direction=true";
        }
    }

    private static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
            result = URLEncoder.encode(result, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static String encodeImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //读取图片到ByteArrayOutputStream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //参数如果为100那么就不压缩
        byte[] bytes = baos.toByteArray();
        String string = "";
        try {
            string = URLEncoder.encode(Base64.encodeToString(bytes, Base64.DEFAULT), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }
}
