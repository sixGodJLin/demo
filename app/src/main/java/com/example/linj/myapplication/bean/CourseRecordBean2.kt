package com.example.linj.myapplication.bean

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Created by shenhua on 2018/11/28.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class CourseRecordBean2 : Serializable {
    /**
     * currentMonthBean : {"page":"0","size":"1","total":"3","rows":[{"date":"2017-12-07","events":[{"morbidityLog":{"morbidityId":"163","patientId":"106","type":"2","morbidityType":"128009","morbidityTypeName":"全面性强直发作","morbidityTime":"2017-12-07 13:04:00","timeSlot":"5","season":"4","continueSecond":"10","morbidityLevel":"0","causeCount":"1","isCorrect":"1","createTime":"2018-02-07 13:04:27","updateTime":"2018-02-07 13:04:27","version":"1"},"atStatusList":[{"id":"6","morbidityId":"163","status_id":"130006","status_name":"意识障碍"}],"afterStatusList":[{"id":"6","morbidityId":"163","status_id":"130006","status_name":"意识障碍"}],"bodyStatusList":[],"causeList":[{"id":"282","morbidityId":"163","cause_id":"127008","cause_name":"惊吓","createTime":"2018-02-07 13:04:27","updateTime":"2018-02-07 13:04:27","version":"1"}]},{"morbidityLog":{"morbidityId":"165","patientId":"106","type":"2","morbidityType":"128002","morbidityTypeName":"复杂部分性发作（伴有意识障碍）","morbidityTime":"2017-12-07 13:04:00","timeSlot":"5","season":"4","continueSecond":"10","morbidityLevel":"0","causeCount":"1","isCorrect":"1","createTime":"2018-02-07 13:04:54","updateTime":"2018-02-07 13:04:54","version":"1"},"atStatusList":[{"id":"8","morbidityId":"165","status_id":"130006","status_name":"意识障碍"}],"afterStatusList":[{"id":"8","morbidityId":"165","status_id":"130006","status_name":"意识障碍"}],"bodyStatusList":[{"id":"5","morbidityId":"165","status_id":"130003","status_name":"恐惧愤怒"}],"causeList":[{"id":"284","morbidityId":"165","cause_id":"127008","cause_name":"惊吓","createTime":"2018-02-07 13:04:54","updateTime":"2018-02-07 13:04:54","version":"1"}]},{"morbidityLog":{"morbidityId":"164","patientId":"106","type":"2","morbidityType":"128009","morbidityTypeName":"全面性强直发作","morbidityTime":"2017-12-07 13:04:00","timeSlot":"5","season":"4","continueSecond":"10","morbidityLevel":"0","causeCount":"1","isCorrect":"1","createTime":"2018-02-07 13:04:28","updateTime":"2018-02-07 13:04:28","version":"1"},"atStatusList":[{"id":"7","morbidityId":"164","status_id":"130006","status_name":"意识障碍"}],"afterStatusList":[{"id":"7","morbidityId":"164","status_id":"130006","status_name":"意识障碍"}],"bodyStatusList":[],"causeList":[{"id":"283","morbidityId":"164","cause_id":"127008","cause_name":"惊吓","createTime":"2018-02-07 13:04:28","updateTime":"2018-02-07 13:04:28","version":"1"}]}]}]}
     * lastMonthCount : 0
     */
    @SerializedName("current_month")
    var currentMonthBean: CurrentMonthBean? = null
    @SerializedName("last_month_count")
    var lastMonthCount: String? = null

    class CurrentMonthBean : Serializable {
        /**
         * page : 0
         * size : 1
         * total : 3
         * rows : [{"date":"2017-12-07","events":[{"morbidityLog":{"morbidityId":"163","patientId":"106","type":"2","morbidityType":"128009","morbidityTypeName":"全面性强直发作","morbidityTime":"2017-12-07 13:04:00","timeSlot":"5","season":"4","continueSecond":"10","morbidityLevel":"0","causeCount":"1","isCorrect":"1","createTime":"2018-02-07 13:04:27","updateTime":"2018-02-07 13:04:27","version":"1"},"atStatusList":[{"id":"6","morbidityId":"163","status_id":"130006","status_name":"意识障碍"}],"afterStatusList":[{"id":"6","morbidityId":"163","status_id":"130006","status_name":"意识障碍"}],"bodyStatusList":[],"causeList":[{"id":"282","morbidityId":"163","cause_id":"127008","cause_name":"惊吓","createTime":"2018-02-07 13:04:27","updateTime":"2018-02-07 13:04:27","version":"1"}]},{"morbidityLog":{"morbidityId":"165","patientId":"106","type":"2","morbidityType":"128002","morbidityTypeName":"复杂部分性发作（伴有意识障碍）","morbidityTime":"2017-12-07 13:04:00","timeSlot":"5","season":"4","continueSecond":"10","morbidityLevel":"0","causeCount":"1","isCorrect":"1","createTime":"2018-02-07 13:04:54","updateTime":"2018-02-07 13:04:54","version":"1"},"atStatusList":[{"id":"8","morbidityId":"165","status_id":"130006","status_name":"意识障碍"}],"afterStatusList":[{"id":"8","morbidityId":"165","status_id":"130006","status_name":"意识障碍"}],"bodyStatusList":[{"id":"5","morbidityId":"165","status_id":"130003","status_name":"恐惧愤怒"}],"causeList":[{"id":"284","morbidityId":"165","cause_id":"127008","cause_name":"惊吓","createTime":"2018-02-07 13:04:54","updateTime":"2018-02-07 13:04:54","version":"1"}]},{"morbidityLog":{"morbidityId":"164","patientId":"106","type":"2","morbidityType":"128009","morbidityTypeName":"全面性强直发作","morbidityTime":"2017-12-07 13:04:00","timeSlot":"5","season":"4","continueSecond":"10","morbidityLevel":"0","causeCount":"1","isCorrect":"1","createTime":"2018-02-07 13:04:28","updateTime":"2018-02-07 13:04:28","version":"1"},"atStatusList":[{"id":"7","morbidityId":"164","status_id":"130006","status_name":"意识障碍"}],"afterStatusList":[{"id":"7","morbidityId":"164","status_id":"130006","status_name":"意识障碍"}],"bodyStatusList":[],"causeList":[{"id":"283","morbidityId":"164","cause_id":"127008","cause_name":"惊吓","createTime":"2018-02-07 13:04:28","updateTime":"2018-02-07 13:04:28","version":"1"}]}]}]
         */
        @SerializedName("page")
        var page: String? = null
        @SerializedName("size")
        var size: String? = null
        @SerializedName("total")
        var total: String? = null
        @SerializedName("rows")
        var rows: List<RowsBean>? = null
    }

    class RowsBean : Serializable {
        /**
         * date : 2017-12-07
         * events : [{"morbidityLog":{"morbidityId":"163","patientId":"106","type":"2","morbidityType":"128009","morbidityTypeName":"全面性强直发作","morbidityTime":"2017-12-07 13:04:00","timeSlot":"5","season":"4","continueSecond":"10","morbidityLevel":"0","causeCount":"1","isCorrect":"1","createTime":"2018-02-07 13:04:27","updateTime":"2018-02-07 13:04:27","version":"1"},"atStatusList":[{"id":"6","morbidityId":"163","status_id":"130006","status_name":"意识障碍"}],"afterStatusList":[{"id":"6","morbidityId":"163","status_id":"130006","status_name":"意识障碍"}],"bodyStatusList":[],"causeList":[{"id":"282","morbidityId":"163","cause_id":"127008","cause_name":"惊吓","createTime":"2018-02-07 13:04:27","updateTime":"2018-02-07 13:04:27","version":"1"}]},{"morbidityLog":{"morbidityId":"165","patientId":"106","type":"2","morbidityType":"128002","morbidityTypeName":"复杂部分性发作（伴有意识障碍）","morbidityTime":"2017-12-07 13:04:00","timeSlot":"5","season":"4","continueSecond":"10","morbidityLevel":"0","causeCount":"1","isCorrect":"1","createTime":"2018-02-07 13:04:54","updateTime":"2018-02-07 13:04:54","version":"1"},"atStatusList":[{"id":"8","morbidityId":"165","status_id":"130006","status_name":"意识障碍"}],"afterStatusList":[{"id":"8","morbidityId":"165","status_id":"130006","status_name":"意识障碍"}],"bodyStatusList":[{"id":"5","morbidityId":"165","status_id":"130003","status_name":"恐惧愤怒"}],"causeList":[{"id":"284","morbidityId":"165","cause_id":"127008","cause_name":"惊吓","createTime":"2018-02-07 13:04:54","updateTime":"2018-02-07 13:04:54","version":"1"}]},{"morbidityLog":{"morbidityId":"164","patientId":"106","type":"2","morbidityType":"128009","morbidityTypeName":"全面性强直发作","morbidityTime":"2017-12-07 13:04:00","timeSlot":"5","season":"4","continueSecond":"10","morbidityLevel":"0","causeCount":"1","isCorrect":"1","createTime":"2018-02-07 13:04:28","updateTime":"2018-02-07 13:04:28","version":"1"},"atStatusList":[{"id":"7","morbidityId":"164","status_id":"130006","status_name":"意识障碍"}],"afterStatusList":[{"id":"7","morbidityId":"164","status_id":"130006","status_name":"意识障碍"}],"bodyStatusList":[],"causeList":[{"id":"283","morbidityId":"164","cause_id":"127008","cause_name":"惊吓","createTime":"2018-02-07 13:04:28","updateTime":"2018-02-07 13:04:28","version":"1"}]}]
         */
        @SerializedName("date")
        var date: String? = null
        @SerializedName("events")
        var events: List<EventsBean>? = null
    }

    class EventsBean : Serializable {

        /**
         * morbidityLog : {"morbidityId":"163","patientId":"106","type":"2","morbidityType":"128009","morbidityTypeName":"全面性强直发作","morbidityTime":"2017-12-07 13:04:00","timeSlot":"5","season":"4","continueSecond":"10","morbidityLevel":"0","causeCount":"1","isCorrect":"1","createTime":"2018-02-07 13:04:27","updateTime":"2018-02-07 13:04:27","version":"1"}
         * atStatusList : [{"id":"6","morbidityId":"163","status_id":"130006","status_name":"意识障碍"}]
         * afterStatusList : [{"id":"6","morbidityId":"163","status_id":"130006","status_name":"意识障碍"}]
         * bodyStatusList : []
         * causeList : [{"id":"282","morbidityId":"163","cause_id":"127008","cause_name":"惊吓","createTime":"2018-02-07 13:04:27","updateTime":"2018-02-07 13:04:27","version":"1"}]
         */
        @SerializedName("morbidity_log")
        var morbidityLog: MorbidityLogBean? = null
        @SerializedName("at_status_list")
        var atStatusList: List<AtStatusListBean>? = null
        @SerializedName("after_status_list")
        var afterStatusList: List<AfterStatusListBean>? = null
        @SerializedName("body_status_list")
        var bodyStatusList: List<BodyStatusListBean>? = null
        @SerializedName("cause_list")
        var causeList: List<CauseListBean>? = null

        companion object {
            private const val serialVersionUID = 2404915217098001975L
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as EventsBean

            if (morbidityLog != other.morbidityLog) return false
            if (atStatusList != other.atStatusList) return false
            if (afterStatusList != other.afterStatusList) return false
            if (bodyStatusList != other.bodyStatusList) return false
            if (causeList != other.causeList) return false

            return true
        }

        override fun hashCode(): Int {
            var result = morbidityLog?.hashCode() ?: 0
            result = 31 * result + (atStatusList?.hashCode() ?: 0)
            result = 31 * result + (afterStatusList?.hashCode() ?: 0)
            result = 31 * result + (bodyStatusList?.hashCode() ?: 0)
            result = 31 * result + (causeList?.hashCode() ?: 0)
            return result
        }

    }

    class MorbidityLogBean : Serializable {
        /**
         * morbidityId : 163
         * patientId : 106
         * type : 2
         * morbidityType : 128009
         * morbidityTypeName : 全面性强直发作
         * morbidityTime : 2017-12-07 13:04:00
         * timeSlot : 5
         * season : 4
         * continueSecond : 10
         * morbidityLevel : 0
         * causeCount : 1
         * isCorrect : 1
         * createTime : 2018-02-07 13:04:27
         * updateTime : 2018-02-07 13:04:27
         * version : 1
         */
        @SerializedName("morbidity_id")
        var morbidityId: Int = 0
        @SerializedName("patient_id")
        var patientId: Int = 0
        @SerializedName("type")
        var type: Int = 0
        @SerializedName("morbidity_type")
        var morbidityType: Long = 0
        @SerializedName("morbidity_type_name")
        var morbidityTypeName: String? = null
        @SerializedName("morbidity_time")
        var morbidityTime: String? = null
        @SerializedName("continue_second")
        var continueSecond: Int = 0
        @SerializedName("create_time")
        var createTime: String? = null
        @SerializedName("update_time")
        var updateTime: String? = null
        @SerializedName("version")
        var version: String? = null
        @SerializedName("file_id")
        var fileId: String? = null
        @SerializedName("time_slot")
        var timeSlot: String? = null
        @SerializedName("morbidity_level")
        var morbidityLevel: String? = null
        @SerializedName("cause_count")
        var causeCount: String? = null
        @SerializedName("is_correct")
        var isCorrect: String? = null
        @SerializedName("season")
        var season: String? = null

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MorbidityLogBean

            if (morbidityId != other.morbidityId) return false
            if (patientId != other.patientId) return false
            if (type != other.type) return false
            if (morbidityType != other.morbidityType) return false
            if (morbidityTypeName != other.morbidityTypeName) return false
            if (morbidityTime != other.morbidityTime) return false
            if (continueSecond != other.continueSecond) return false
            if (createTime != other.createTime) return false
            if (updateTime != other.updateTime) return false
            if (version != other.version) return false
            if (fileId != other.fileId) return false
            if (timeSlot != other.timeSlot) return false
            if (morbidityLevel != other.morbidityLevel) return false
            if (causeCount != other.causeCount) return false
            if (isCorrect != other.isCorrect) return false
            if (season != other.season) return false

            return true
        }

        override fun hashCode(): Int {
            var result = morbidityId
            result = 31 * result + patientId
            result = 31 * result + type
            result = 31 * result + morbidityType.hashCode()
            result = 31 * result + (morbidityTypeName?.hashCode() ?: 0)
            result = 31 * result + (morbidityTime?.hashCode() ?: 0)
            result = 31 * result + continueSecond
            result = 31 * result + (createTime?.hashCode() ?: 0)
            result = 31 * result + (updateTime?.hashCode() ?: 0)
            result = 31 * result + (version?.hashCode() ?: 0)
            result = 31 * result + (fileId?.hashCode() ?: 0)
            result = 31 * result + (timeSlot?.hashCode() ?: 0)
            result = 31 * result + (morbidityLevel?.hashCode() ?: 0)
            result = 31 * result + (causeCount?.hashCode() ?: 0)
            result = 31 * result + (isCorrect?.hashCode() ?: 0)
            result = 31 * result + (season?.hashCode() ?: 0)
            return result
        }


    }

    class AtStatusListBean : BaseStatus, Serializable {
        /**
         * id : 6
         * morbidityId : 163
         * status_id : 130006
         * status_name : 意识障碍
         */
        constructor(statusId: Long, statusName: String) {
            this.statusId = statusId
            this.statusName = statusName
        }

        constructor(statusId: Long, statusName: String, text: String) {
            this.statusId = statusId
            this.statusName = statusName
            this.text = text
        }

        override fun equals(other: Any?): Boolean {
            if (other is DictionaryListResponse.DictionaryBean) {
                val bean = other as DictionaryListResponse.DictionaryBean?
                return bean?.dictId == statusId && bean.name == statusName
            }
            if (this === other) return true
            if (other == null || javaClass != other.javaClass) return false

            val that = other as AtStatusListBean?

            if (statusId != that!!.statusId) return false
            return if (statusName != null) statusName == that.statusName else that.statusName == null
        }

        override fun hashCode(): Int {
            var result = (statusId xor statusId.ushr(32)).toInt()
            result = 31 * result + if (statusName != null) statusName!!.hashCode() else 0
            return result
        }
    }

    class AfterStatusListBean : BaseStatus, Serializable {
        /**
         * id : 6
         * morbidityId : 163
         * status_id : 130006
         * status_name : 意识障碍
         */
        constructor(statusId: Long, statusName: String) {
            this.statusId = statusId
            this.statusName = statusName
        }

        constructor(statusId: Long, statusName: String, text: String) {
            this.statusId = statusId
            this.statusName = statusName
            this.text = text
        }

        override fun equals(other: Any?): Boolean {
            if (other is DictionaryListResponse.DictionaryBean) {
                val bean = other as DictionaryListResponse.DictionaryBean?
                return bean?.dictId == statusId && bean.name == statusName
            }
            if (this === other) return true
            if (other == null || javaClass != other.javaClass) return false

            val that = other as AfterStatusListBean?

            if (statusId != that!!.statusId) return false
            return if (statusName != null) statusName == that.statusName else that.statusName == null
        }

        override fun hashCode(): Int {
            var result = (statusId xor statusId.ushr(32)).toInt()
            result = 31 * result + if (statusName != null) statusName!!.hashCode() else 0
            return result
        }
    }

    class CauseListBean : BaseStatus, Serializable {
        /**
         * id : 282
         * morbidityId : 163
         * cause_id : 127008
         * cause_name : 惊吓
         * createTime : 2018-02-07 13:04:27
         * updateTime : 2018-02-07 13:04:27
         * version : 1
         */
        @SerializedName("cause_id")
        override var statusId: Long = 0
        @SerializedName("cause_name")
        override var statusName: String? = null

        constructor(causeId: Long, causeName: String) {
            this.statusId = causeId
            this.statusName = causeName
        }

        constructor(causeId: Long, causeName: String, text: String) {
            this.statusId = causeId
            this.statusName = causeName
            this.text = text
        }

        override fun equals(other: Any?): Boolean {
            if (other is DictionaryListResponse.DictionaryBean) {
                val bean = other as DictionaryListResponse.DictionaryBean?
                return bean?.dictId == statusId && bean.name == statusName
            }
            if (this === other) return true
            if (other == null || javaClass != other.javaClass) return false

            val that = other as CauseListBean?

            if (statusId != that!!.statusId) return false
            return if (statusName != null) statusName == that.statusName else that.statusName == null
        }

        override fun hashCode(): Int {
            var result = (statusId xor statusId.ushr(32)).toInt()
            result = 31 * result + if (statusName != null) statusName!!.hashCode() else 0
            return result
        }
    }

    class BodyStatusListBean : BaseStatus, Serializable {
        /**
         * id : 6
         * morbidityId : 163
         * status_id : 130006
         * status_name : 意识障碍
         */
        constructor(statusId: Long, statusName: String) {
            this.statusId = statusId
            this.statusName = statusName
        }

        constructor(statusId: Long, statusName: String, text: String) {
            this.statusId = statusId
            this.statusName = statusName
            this.text = text
        }

        override fun equals(other: Any?): Boolean {
            if (other is DictionaryListResponse.DictionaryBean) {
                val bean = other as DictionaryListResponse.DictionaryBean?
                return bean?.dictId == statusId && bean.name == statusName
            }
            if (this === other) return true
            if (other == null || javaClass != other.javaClass) return false

            val that = other as BodyStatusListBean?

            if (statusId != that!!.statusId) return false
            return if (statusName != null) statusName == that.statusName else that.statusName == null
        }

        override fun hashCode(): Int {
            var result = (statusId xor statusId.ushr(32)).toInt()
            result = 31 * result + if (statusName != null) statusName!!.hashCode() else 0
            return result
        }
    }

    open class BaseStatus : Serializable {
        @SerializedName("id")
        var id: Int = 0
        @SerializedName("morbidity_id")
        var morbidityId: Int = 0
        @SerializedName("status_id")
        open var statusId: Long = 0
        @SerializedName("status_name")
        open var statusName: String? = null
        @SerializedName("text")
        var text: String? = null
    }

    companion object {
        private const val serialVersionUID = -6079001269236987648L
    }
}
