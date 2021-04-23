package com.example.linj.myapplication;

import java.io.Serializable;
import java.util.List;

public class IndentifyRequest implements Serializable {
    private static final long serialVersionUID = -2940937290281353010L;


    /**
     * timestamp : 1619798400
     * deviceSn : DS-K1T331W
     * logList : [{"uid":7,"pigcmsId":114,"openStatus":1,"logTime":1617175798},{"uid":7,"pigcmsId":114,"openStatus":0,"logTime":1617175798},{"uid":7,"pigcmsId":114,"openStatus":1,"logTime":1617175798},{"uid":7,"pigcmsId":114,"openStatus":0,"logTime":1617175798}]
     */

    private String timestamp;
    private String deviceSn;
    private List<LogListBean> logList;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public List<LogListBean> getLogList() {
        return logList;
    }

    public void setLogList(List<LogListBean> logList) {
        this.logList = logList;
    }

    public static class LogListBean {
        /**
         * uid : 7
         * pigcmsId : 114
         * openStatus : 1
         * logTime : 1617175798
         */

        private String uid;
        private String pigcmsId;
        private String openStatus;
        private String logTime;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPigcmsId() {
            return pigcmsId;
        }

        public void setPigcmsId(String pigcmsId) {
            this.pigcmsId = pigcmsId;
        }

        public String getOpenStatus() {
            return openStatus;
        }

        public void setOpenStatus(String openStatus) {
            this.openStatus = openStatus;
        }

        public String getLogTime() {
            return logTime;
        }

        public void setLogTime(String logTime) {
            this.logTime = logTime;
        }
    }
}
