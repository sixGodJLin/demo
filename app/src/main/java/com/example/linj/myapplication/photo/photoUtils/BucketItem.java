package com.example.linj.myapplication.photo.photoUtils;

import java.util.List;

public class BucketItem {
    private String bucketName;
    private List<PhotoItem> photoItems;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public List<PhotoItem> getPhotoItems() {
        return photoItems;
    }

    public void setPhotoItems(List<PhotoItem> photoItems) {
        this.photoItems = photoItems;
    }
}
