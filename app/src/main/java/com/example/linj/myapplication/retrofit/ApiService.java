package com.example.linj.myapplication.retrofit;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}