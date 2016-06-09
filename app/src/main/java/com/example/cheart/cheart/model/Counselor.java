package com.example.cheart.cheart.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 5/7/2016.
 */
public class Counselor implements Serializable {
    private String name, thumbnailUrl, profession, thumbnailInsideUrl, bdate;
    private int idCounselor;
    private double longitude,latitude;


    public Counselor(int idCounselor, String name, String profession, String bdate, String thumbnailUrl, double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.bdate = bdate;
        this.idCounselor = idCounselor;
        this.profession = profession;
        this.thumbnailUrl = thumbnailUrl;
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }



    public Counselor() {
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getThumbnailInsideUrl() {
        return thumbnailInsideUrl;
    }

    public void setThumbnailInsideUrl(String thumbnailInsideUrl) {
        this.thumbnailInsideUrl = thumbnailInsideUrl;
    }

    public int getIdCounselor() {
        return idCounselor;
    }

    public void setIdCounselor(int idCounselor) {
        this.idCounselor = idCounselor;
    }
}
