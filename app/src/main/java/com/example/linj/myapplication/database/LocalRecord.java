package com.example.linj.myapplication.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class LocalRecord {

    @Id(autoincrement = true)
    private Long id;

    private Integer geziIndex;
    private String bagFlag;
    private String goodsCode;
    private String goodsId;
    private String userCardNo;
    private String getTime;
    private Integer huodaoIndex;
    @Generated(hash = 1788975908)
    public LocalRecord(Long id, Integer geziIndex, String bagFlag, String goodsCode,
            String goodsId, String userCardNo, String getTime,
            Integer huodaoIndex) {
        this.id = id;
        this.geziIndex = geziIndex;
        this.bagFlag = bagFlag;
        this.goodsCode = goodsCode;
        this.goodsId = goodsId;
        this.userCardNo = userCardNo;
        this.getTime = getTime;
        this.huodaoIndex = huodaoIndex;
    }
    @Generated(hash = 1667100475)
    public LocalRecord() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getGeziIndex() {
        return this.geziIndex;
    }
    public void setGeziIndex(Integer geziIndex) {
        this.geziIndex = geziIndex;
    }
    public String getBagFlag() {
        return this.bagFlag;
    }
    public void setBagFlag(String bagFlag) {
        this.bagFlag = bagFlag;
    }
    public String getGoodsCode() {
        return this.goodsCode;
    }
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }
    public String getGoodsId() {
        return this.goodsId;
    }
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    public String getUserCardNo() {
        return this.userCardNo;
    }
    public void setUserCardNo(String userCardNo) {
        this.userCardNo = userCardNo;
    }
    public String getGetTime() {
        return this.getTime;
    }
    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }
    public Integer getHuodaoIndex() {
        return this.huodaoIndex;
    }
    public void setHuodaoIndex(Integer huodaoIndex) {
        this.huodaoIndex = huodaoIndex;
    }
   
}
