package com.example.linj.myapplication.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author wys
 * @date 2019/7/11/0011
 * Function:
 */
@Entity
public class MachineDB {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String machineCode;

    private String version ;
    private String machineStatus;
    public String doorStatus;
    public String heartSpeed ;
    public String pipeInfo ;
    public String simSingle ;
    public String runTime ;
    public String serial;
    public int serial_rate;
    public String lostConnection ;
    public String localDateNum ;
    private String videoUrl;
    @Generated(hash = 783239958)
    public MachineDB(Long id, @NotNull String machineCode, String version,
            String machineStatus, String doorStatus, String heartSpeed,
            String pipeInfo, String simSingle, String runTime, String serial,
            int serial_rate, String lostConnection, String localDateNum,
            String videoUrl) {
        this.id = id;
        this.machineCode = machineCode;
        this.version = version;
        this.machineStatus = machineStatus;
        this.doorStatus = doorStatus;
        this.heartSpeed = heartSpeed;
        this.pipeInfo = pipeInfo;
        this.simSingle = simSingle;
        this.runTime = runTime;
        this.serial = serial;
        this.serial_rate = serial_rate;
        this.lostConnection = lostConnection;
        this.localDateNum = localDateNum;
        this.videoUrl = videoUrl;
    }
    @Generated(hash = 970626609)
    public MachineDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMachineCode() {
        return this.machineCode;
    }
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }
    public String getVersion() {
        return this.version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getMachineStatus() {
        return this.machineStatus;
    }
    public void setMachineStatus(String machineStatus) {
        this.machineStatus = machineStatus;
    }
    public String getDoorStatus() {
        return this.doorStatus;
    }
    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus;
    }
    public String getHeartSpeed() {
        return this.heartSpeed;
    }
    public void setHeartSpeed(String heartSpeed) {
        this.heartSpeed = heartSpeed;
    }
    public String getPipeInfo() {
        return this.pipeInfo;
    }
    public void setPipeInfo(String pipeInfo) {
        this.pipeInfo = pipeInfo;
    }
    public String getSimSingle() {
        return this.simSingle;
    }
    public void setSimSingle(String simSingle) {
        this.simSingle = simSingle;
    }
    public String getRunTime() {
        return this.runTime;
    }
    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }
    public String getLostConnection() {
        return this.lostConnection;
    }
    public void setLostConnection(String lostConnection) {
        this.lostConnection = lostConnection;
    }
    public String getLocalDateNum() {
        return this.localDateNum;
    }
    public void setLocalDateNum(String localDateNum) {
        this.localDateNum = localDateNum;
    }
    public String getSerial() {
        return this.serial;
    }
    public void setSerial(String serial) {
        this.serial = serial;
    }
    public int getSerial_rate() {
        return this.serial_rate;
    }
    public void setSerial_rate(int serial_rate) {
        this.serial_rate = serial_rate;
    }
    public String getVideoUrl() {
        return this.videoUrl;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
