package com.example.linj.myapplication.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class ServerData {
    @Id(autoincrement = true)
    private Long id;

    @Unique
    private String userCardNo;
    private String cardId;
    private String goodsCode;
    private String date;
    @Generated(hash = 1645456559)
    public ServerData(Long id, String userCardNo, String cardId, String goodsCode,
            String date) {
        this.id = id;
        this.userCardNo = userCardNo;
        this.cardId = cardId;
        this.goodsCode = goodsCode;
        this.date = date;
    }
    @Generated(hash = 261955646)
    public ServerData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserCardNo() {
        return this.userCardNo;
    }
    public void setUserCardNo(String userCardNo) {
        this.userCardNo = userCardNo;
    }
    public String getCardId() {
        return this.cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public String getGoodsCode() {
        return this.goodsCode;
    }
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
