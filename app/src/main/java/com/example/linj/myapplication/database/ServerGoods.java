package com.example.linj.myapplication.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ServerGoods {

    @Id(autoincrement = true)
    @Unique
    private Long id;
    private Integer geziIndex;
    private String geziStatus;
    private String goodsCode;
    private String goodsId;
    private String goodsType;
    private Integer huodaoIndex;
    @Generated(hash = 1983529586)
    public ServerGoods(Long id, Integer geziIndex, String geziStatus,
            String goodsCode, String goodsId, String goodsType,
            Integer huodaoIndex) {
        this.id = id;
        this.geziIndex = geziIndex;
        this.geziStatus = geziStatus;
        this.goodsCode = goodsCode;
        this.goodsId = goodsId;
        this.goodsType = goodsType;
        this.huodaoIndex = huodaoIndex;
    }
    @Generated(hash = 874391041)
    public ServerGoods() {
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
    public String getGeziStatus() {
        return this.geziStatus;
    }
    public void setGeziStatus(String geziStatus) {
        this.geziStatus = geziStatus;
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
    public String getGoodsType() {
        return this.goodsType;
    }
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
    public Integer getHuodaoIndex() {
        return this.huodaoIndex;
    }
    public void setHuodaoIndex(Integer huodaoIndex) {
        this.huodaoIndex = huodaoIndex;
    }
}
