package com.francis.bestroute.vo;

import java.io.Serializable;

/**
 * Created by zeven on 2/28/2018.
 */

public class MainItemVO implements Serializable{
    int num;
    String address;
    double latitude; // latitude

    double longitude; // longitude

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getNum() {
        return num;
    }

    public MainItemVO setNum(int num) {
        this.num = num;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
