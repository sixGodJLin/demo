package com.example.linj.myapplication.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by NeuroAndroid on 2017/11/16.
 */

public class DictionaryListResponse implements Serializable {
    /**
     * 当前页码
     */
    private int page;

    /**
     * 记录数
     */
    private int size;

    /**
     * 总记录数
     */
    private int total;

    /**
     * 每页的数据
     */
    private ArrayList<DictionaryBean> rows;

    public ArrayList<DictionaryBean> getRows() {
        return rows;
    }

    public void setRows(ArrayList<DictionaryBean> rows) {
        this.rows = rows;
    }

    public static class DictionaryBean implements Serializable{
        @SerializedName("dict_id")
        private Long dictId = 0L;

        @SerializedName("parent_id")
        private Long parentId;

        private String name;

        @SerializedName("sort_id")
        private Integer sortId;

        public DictionaryBean() {
        }

        public DictionaryBean(Long dictId, Long parentId, String name, Integer sortId) {
            this.dictId = dictId;
            this.parentId = parentId;
            this.name = name;
            this.sortId = sortId;
        }

        private boolean isSelected;

        public Long getDictId() {
            return dictId;
        }

        public void setDictId(Long dictId) {
            this.dictId = dictId;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name == null ? null : name.trim();
        }

        public Integer getSortId() {
            return sortId;
        }

        public void setSortId(Integer sortId) {
            this.sortId = sortId;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }


        @Override
        public String toString() {
            return "DictionaryBean{" +
                    "dictId=" + dictId +
                    ", parentId=" + parentId +
                    ", name='" + name + '\'' +
                    ", sortId=" + sortId +
                    ", isSelected=" + isSelected +
                    '}';
        }
    }
}
