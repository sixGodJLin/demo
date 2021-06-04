package com.example.linj.myapplication.bean;


import java.io.Serializable;

public class CommonBean implements Serializable {
    private static final long serialVersionUID = 7956623206872093502L;

    private String exchangeTime;
    private String channelId;
    private String channelNo;
    private String channelPassword;
    private String appVersion;
    private String appMobileModel;
    private String machineCode;

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
