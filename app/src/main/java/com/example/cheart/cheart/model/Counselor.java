package com.example.cheart.cheart.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 5/7/2016.
 */
public class Counselor implements Serializable {
    private String name, thumbnailUrl, description;
    private int idCounselor;

    public Counselor() {
    }

    public Counselor(String name, String thumbnailUrl, String description, int idCounselor) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.idCounselor = idCounselor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCounselor() {
        return idCounselor;
    }

    public void setIdCounselor(int idCounselor) {
        this.idCounselor = idCounselor;
    }
}
