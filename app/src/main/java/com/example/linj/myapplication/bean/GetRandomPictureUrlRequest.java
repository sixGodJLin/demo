package com.example.linj.myapplication.bean;

import com.example.linj.myapplication.BuildConfig;
import com.example.linj.myapplication.utils.Constants;

public class GetRandomPictureUrlRequest {
    private String exchangeTime;
    private String channelId = "CKGI_Android";
    private String channelNo = "CKGI";
    private String channelPassword = "CKGI";
    private String appVersion = BuildConfig.VERSION_NAME;
    private String appMobileModel;
    private String machineCode = "9902571000410";

    public String getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelPassword() {
        return channelPassword;
    }

    public void setChannelPassword(String channelPassword) {
        this.channelPassword = channelPassword;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppMobileModel() {
        return appMobileModel;
    }

    public void setAppMobileModel(String appMobileModel) {
        this.appMobileModel = appMobileModel;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }
}
