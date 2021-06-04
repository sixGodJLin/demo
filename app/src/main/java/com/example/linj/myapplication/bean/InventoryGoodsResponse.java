package com.example.linj.myapplication.bean;

import java.io.Serializable;
import java.util.List;

public class InventoryGoodsResponse implements Serializable {

    private static final long serialVersionUID = -3655888495511433196L;
    private String fromChannelId;
    private List<ListBean> list;
    private String machineCode;
    private String platformId;
    private String responseCode;
    private String responseMsg;
    private String responseTime;

    public String getFromChannelId() {
        return fromChannelId;
    }

    public void setFromChannelId(String fromChannelId) {
        this.fromChannelId = fromChannelId;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
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

    public static class ListBean implements Serializable{
        private static final long serialVersionUID = 6067318424951136281L;
        private Integer geziIndex;
        private String geziStatus;
        private String goodsCode;
        private String goodsId;
        private String goodsType;
        private Integer huodaoIndex;

        public Integer getGeziIndex() {
            return geziIndex;
        }

        public void setGeziIndex(Integer geziIndex) {
            this.geziIndex = geziIndex;
        }

        public String getGeziStatus() {
            return geziStatus;
        }

        public void setGeziStatus(String geziStatus) {
            this.geziStatus = geziStatus;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public Integer getHuodaoIndex() {
            return huodaoIndex;
        }

        public void setHuodaoIndex(Integer huodaoIndex) {
            this.huodaoIndex = huodaoIndex;
        }
    }
}
