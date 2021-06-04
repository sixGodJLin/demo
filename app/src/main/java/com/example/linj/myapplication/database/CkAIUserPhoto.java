package com.example.linj.myapplication.database;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
**@author ChenPin
**@phone 13216117237
**@create 2019/3/6/0006
**@Describe 人脸信息实体类  
**/
@Entity
public class CkAIUserPhoto implements Parcelable {

    //@Id：主键，通过这个注解标记的字段必须是Long类型的，这个字段在数据库中表示它就是主键，并且它默认就是自增的
    @Id(autoincrement = true)
    Long _id;
    String id; //人员编号
    String name; //姓名
    String sex; //性别 0女1男
    String cardNo; //村口卡号
    String isQualified; //照面是否合格 0不合格 1合格
    String photoAddressA; //人脸照片1地址
    String photoAddressB; //人脸照片2地址
    String photoAddressC; //人脸照片3地址
    String isHasEigenvalue; //照片是否转为特征值
    String photoEigenvalueA; //人脸照片1特征值 长度3000
    String photoEigenvalueB; //人脸照片1特征值 长度>3000
    String photoEigenvalueC; //人脸照片2特征值 长度3000
    String photoEigenvalueD; //人脸照片2特征值 长度>3000
    String photoEigenvalueE; //人脸照片3特征值 长度3000
    String photoEigenvalueF; //人脸照片3特征值 长度>3000
    String isChangeNo; //是否换卡 0否1是
    String standyA; //预留字段
    String standyB; //预留字段
    String standyC; //预留字段
    String standyD; //预留字段
    String insertTime; //注册时间
    String updateTime; //修改时间


    @Generated(hash = 139450064)
    public CkAIUserPhoto(Long _id, String id, String name, String sex,
                         String cardNo, String isQualified, String photoAddressA,
                         String photoAddressB, String photoAddressC, String isHasEigenvalue,
                         String photoEigenvalueA, String photoEigenvalueB,
                         String photoEigenvalueC, String photoEigenvalueD,
                         String photoEigenvalueE, String photoEigenvalueF, String isChangeNo,
                         String standyA, String standyB, String standyC, String standyD,
                         String insertTime, String updateTime) {
        this._id = _id;
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.cardNo = cardNo;
        this.isQualified = isQualified;
        this.photoAddressA = photoAddressA;
        this.photoAddressB = photoAddressB;
        this.photoAddressC = photoAddressC;
        this.isHasEigenvalue = isHasEigenvalue;
        this.photoEigenvalueA = photoEigenvalueA;
        this.photoEigenvalueB = photoEigenvalueB;
        this.photoEigenvalueC = photoEigenvalueC;
        this.photoEigenvalueD = photoEigenvalueD;
        this.photoEigenvalueE = photoEigenvalueE;
        this.photoEigenvalueF = photoEigenvalueF;
        this.isChangeNo = isChangeNo;
        this.standyA = standyA;
        this.standyB = standyB;
        this.standyC = standyC;
        this.standyD = standyD;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 969485573)
    public CkAIUserPhoto() {
    }


    protected CkAIUserPhoto(Parcel in) {
        if (in.readByte() == 0) {
            _id = null;
        } else {
            _id = in.readLong();
        }
        id = in.readString();
        name = in.readString();
        sex = in.readString();
        cardNo = in.readString();
        isQualified = in.readString();
        photoAddressA = in.readString();
        photoAddressB = in.readString();
        photoAddressC = in.readString();
        isHasEigenvalue = in.readString();
        photoEigenvalueA = in.readString();
        photoEigenvalueB = in.readString();
        photoEigenvalueC = in.readString();
        photoEigenvalueD = in.readString();
        photoEigenvalueE = in.readString();
        photoEigenvalueF = in.readString();
        isChangeNo = in.readString();
        standyA = in.readString();
        standyB = in.readString();
        standyC = in.readString();
        standyD = in.readString();
        insertTime = in.readString();
        updateTime = in.readString();
    }

    public static final Creator<CkAIUserPhoto> CREATOR = new Creator<CkAIUserPhoto>() {
        @Override
        public CkAIUserPhoto createFromParcel(Parcel in) {
            return new CkAIUserPhoto(in);
        }

        @Override
        public CkAIUserPhoto[] newArray(int size) {
            return new CkAIUserPhoto[size];
        }
    };

    public String getPhotoEigenvalueD() {
        return photoEigenvalueD;
    }

    public void setPhotoEigenvalueD(String photoEigenvalueD) {
        this.photoEigenvalueD = photoEigenvalueD;
    }

    public String getPhotoEigenvalueE() {
        return photoEigenvalueE;
    }

    public void setPhotoEigenvalueE(String photoEigenvalueE) {
        this.photoEigenvalueE = photoEigenvalueE;
    }

    public String getPhotoEigenvalueF() {
        return photoEigenvalueF;
    }

    public void setPhotoEigenvalueF(String photoEigenvalueF) {
        this.photoEigenvalueF = photoEigenvalueF;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(String isQualified) {
        this.isQualified = isQualified;
    }

    public String getPhotoAddressA() {
        return photoAddressA;
    }

    public void setPhotoAddressA(String photoAddressA) {
        this.photoAddressA = photoAddressA;
    }

    public String getPhotoAddressB() {
        return photoAddressB;
    }

    public void setPhotoAddressB(String photoAddressB) {
        this.photoAddressB = photoAddressB;
    }

    public String getPhotoAddressC() {
        return photoAddressC;
    }

    public void setPhotoAddressC(String photoAddressC) {
        this.photoAddressC = photoAddressC;
    }

    public String getIsHasEigenvalue() {
        return isHasEigenvalue;
    }

    public void setIsHasEigenvalue(String isHasEigenvalue) {
        this.isHasEigenvalue = isHasEigenvalue;
    }

    public String getPhotoEigenvalueA() {
        return photoEigenvalueA;
    }

    public void setPhotoEigenvalueA(String photoEigenvalueA) {
        this.photoEigenvalueA = photoEigenvalueA;
    }

    public String getPhotoEigenvalueB() {
        return photoEigenvalueB;
    }

    public void setPhotoEigenvalueB(String photoEigenvalueB) {
        this.photoEigenvalueB = photoEigenvalueB;
    }

    public String getPhotoEigenvalueC() {
        return photoEigenvalueC;
    }

    public void setPhotoEigenvalueC(String photoEigenvalueC) {
        this.photoEigenvalueC = photoEigenvalueC;
    }

    public String getIsChangeNo() {
        return isChangeNo;
    }

    public void setIsChangeNo(String isChangeNo) {
        this.isChangeNo = isChangeNo;
    }

    public String getStandyA() {
        return standyA;
    }

    public void setStandyA(String standyA) {
        this.standyA = standyA;
    }

    public String getStandyB() {
        return standyB;
    }

    public void setStandyB(String standyB) {
        this.standyB = standyB;
    }

    public String getStandyC() {
        return standyC;
    }

    public void setStandyC(String standyC) {
        this.standyC = standyC;
    }

    public String getStandyD() {
        return standyD;
    }

    public void setStandyD(String standyD) {
        this.standyD = standyD;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(_id);
        }
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeString(cardNo);
        dest.writeString(isQualified);
        dest.writeString(photoAddressA);
        dest.writeString(photoAddressB);
        dest.writeString(photoAddressC);
        dest.writeString(isHasEigenvalue);
        dest.writeString(photoEigenvalueA);
        dest.writeString(photoEigenvalueB);
        dest.writeString(photoEigenvalueC);
        dest.writeString(photoEigenvalueD);
        dest.writeString(photoEigenvalueE);
        dest.writeString(photoEigenvalueF);
        dest.writeString(isChangeNo);
        dest.writeString(standyA);
        dest.writeString(standyB);
        dest.writeString(standyC);
        dest.writeString(standyD);
        dest.writeString(insertTime);
        dest.writeString(updateTime);
    }
}
