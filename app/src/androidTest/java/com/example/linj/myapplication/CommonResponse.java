package com.example.linj.myapplication;

import java.io.Serializable;

public class CommonResponse implements Serializable {
    private static final long serialVersionUID = -18681008788284300L;

    /**
     * fromChannelId : CKAI
     * platformId : CKCORE
     * responseCode : 000000
     * responseMsg : 通信正常,处理成功
     * responseTime : 2020-07-24 15:30:41
     */

    private String fromChannelId;
    private String platformId;
    private String responseCode;
    private String responseMsg;
    private String responseTime;
    private String dataType;
    private String imgUrl;
    private String userCardNo;
    private String username;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUserCardNo() {
        return userCardNo;
    }

    public void setUserCardNo(String userCardNo) {
        this.userCardNo = userCardNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFromChannelId() {
        return fromChannelId;
    }

    public void setFromChannelId(String fromChannelId) {
        this.fromChannelId = fromChannelId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }
}
