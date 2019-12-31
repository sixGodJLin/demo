package com.example.linj.myapplication.retrofit;


import com.example.linj.myapplication.bean.BaiduGetNumberResponse;
import com.example.linj.myapplication.bean.BaiduTokenResponse;
import com.example.linj.myapplication.bean.CommonResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 请求数据接口
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public interface ApiService {
    /**
     * 登录接口  获取token
     *
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("api/login")
    Call<CommonResponse> login(@Field("username") String username, @Field("password") String password);

    @POST("oauth/2.0/token")
    Call<BaiduTokenResponse> getToken(@Query("grant_type") String grantType, @Query("client_id") String clientId, @Query("client_secret") String clientSecret);

    @HTTP(method = "POST", path = "https://aip.baidubce.com/rest/2.0/ocr/v1/numbers", hasBody = true)
    Call<BaiduGetNumberResponse> getNumbers(@Query("access_token") String accessToken, @Body RequestBody requestBody);
}