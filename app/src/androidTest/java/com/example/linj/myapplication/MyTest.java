package com.example.linj.myapplication;


import com.example.linj.myapplication.utils.third.RetrofitApp;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @author JLin
 * @date 2019/6/12
 */
public class MyTest {
    //1、对要上传的数组进行 ksort 按键名升序排序
    //2、按key1=value1&key2=value2&....拼接成字符串  str
    //3、将 秘钥appSecret拼接在 str两头 ：appSecret + str + appSecret
    //4、将上3步骤的新字符串通过base64编码
    //5、将上4步骤编码后的字符串用MD5 加密，小写32位
    //6、将上5步骤小写转成大写，生成最终秘钥
    public static void main(String[] args) {
//        RetrofitApp.inject("http://www.cunkou.co/cunKouApp/cunKouService/");
        String appSecret = "14625f167075ca51612b3568bcc0c0aa0";
//        Map<String, Object> map = new HashMap<>();
//        Map<String, Object> map2 = new HashMap<>();
//        map.put("uid", "7");
//        map.put("pigcmsId", "114");
//        map.put("openStatus", "1");
//        map.put("logTime", "1617175798");
//
//        String time = "1617267424107";
//        map.put("timestamp", time);
//        map.put("deviceSn", "DS-K1T331W");
//        map.put("logList", map2);
//        System.out.println("MyTest：" + "main" + "==== " + Ksort(map));
//        String str = appSecret + "deviceSn=DS-K1T331W&logList[0][uid]=7&logList[0][pigcmsId]=119&logList[0][openStatus]=1&logList[0][logTime]=1617354675&timestamp=1617354675" + appSecret;
//        System.out.println("MyTest：" + "main" + "==== " + str);
//        String sign = getMD5String(Base64.getEncoder().encodeToString(str.getBytes())).toUpperCase();
//        System.out.println("MyTest：" + "main" + "==== " + sign);
//        RetrofitApp.with().load(Api.class).getUserList(sign, time, "DS-K1T331W").enqueue(new Callback<UserInfoResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<UserInfoResponse> call, @NonNull Response<UserInfoResponse> response) {
//                System.out.println("MyTest：" + "onResponse" + "==== " + response.body().toString());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<UserInfoResponse> call, @NonNull Throwable t) {
//
//            }
//        });

        IndentifyRequest indentifyRequest = new IndentifyRequest();
        indentifyRequest.setTimestamp("1617354675");
        indentifyRequest.setDeviceSn("DS-K1T331W");

        List<IndentifyRequest.LogListBean> listBeans = new ArrayList<>();
        IndentifyRequest.LogListBean logListBean = new IndentifyRequest.LogListBean();
        logListBean.setUid("7");
        logListBean.setPigcmsId("119");
        logListBean.setOpenStatus("1");
        logListBean.setLogTime("1617354675");
        listBeans.add(logListBean);
        indentifyRequest.setLogList(listBeans);
        System.out.println("MyTest：" + "main" + "==== " + Ksort(indentifyRequest));
    }

    private static String Ksort(IndentifyRequest indentifyRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("deviceSn=").append(indentifyRequest.getDeviceSn()).append("&");
        for (int i = 0; i < indentifyRequest.getLogList().size(); i++) {
            sb.append("logList").append("[").append(i).append("]").append("[logTime]=").append(indentifyRequest.getLogList().get(i).getLogTime()).append("&")
                    .append("logList").append("[").append(i).append("]").append("[openStatus]=").append(indentifyRequest.getLogList().get(i).getOpenStatus()).append("&")
                    .append("logList").append("[").append(i).append("]").append("[pigcmsId]=").append(indentifyRequest.getLogList().get(i).getPigcmsId()).append("&")
                    .append("logList").append("[").append(i).append("]").append("[uid]=").append(indentifyRequest.getLogList().get(i).getUid());
        }
        sb.append("timestamp=").append(indentifyRequest.getTimestamp());
        return sb.toString();
    }

    interface Api {
        /**
         * 更新设备本地人脸库
         */
        @FormUrlEncoded
        @POST("https://sq.kml169.com/ckapi.php?c=Face&a=getUserList")
        Call<UserInfoResponse> getUserList(@Header("authSign") String authSign, @Field("timestamp") String timeStamp, @Field("deviceSn") String deviceSn);
    }

    public static String Ksort(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        String[] key = new String[map.size()];
        System.out.println("MyTest：" + "Ksort" + "==== " + map);
        int index = 0;
        for (String k : map.keySet()) {
            key[index] = k;
            index++;
        }
        Arrays.sort(key);

        for (String s : key) {
            if (map.get(s) instanceof Map) {
                System.out.println("MyTest：" + "Ksort" + "==== " + s);
                Map inside = (Map) map.get(s);
                System.out.println("MyTest：" + "Ksort" + "==== " + inside);
//                if (inside != null) {
//                    System.out.println("MyTest：" + "Ksort" + "==== " + inside.get("uid"));
//                    Object[] keyInside = new String[inside.size()];
//                    int indexInside = 0;
//                    for (Object k : inside.keySet()) {
//                        keyInside[indexInside] = k;
//                        indexInside++;
//                    }
//                    for (int i = 0; i < keyInside.length; i++) {
//                        sb.append(s).append("[").append(i).append("]").append("[").append(keyInside[i]).append("]")
//                                .append("=").append(inside.get(keyInside[i])).append("&");
//                    }
//                }
            } else {
                sb.append(s).append("=").append(map.get(s)).append("&");
            }
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        // 将得到的字符串进行处理得到目标格式的字符串
        try {
            sb = new StringBuilder(URLEncoder.encode(sb.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }// 使用常见的UTF-8编码
        sb = new StringBuilder(sb.toString().replace("%3D", "=").replace("%26", "&"));
        return sb.toString();
    }


    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
