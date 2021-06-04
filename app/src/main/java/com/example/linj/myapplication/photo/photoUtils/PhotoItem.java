package com.example.linj.myapplication.photo.photoUtils;

import java.io.Serializable;

public class PhotoItem implements Serializable {
    private static final long serialVersionUID = -6050892446219237575L;

    private String name;
    private String imagePath;
    private String thumbPath;
    private boolean select;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
