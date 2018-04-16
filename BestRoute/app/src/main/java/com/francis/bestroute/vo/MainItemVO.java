package com.francis.bestroute.vo;

import com.google.android.gms.location.places.Place;

import java.io.Serializable;

/**
 * Created by zeven on 2/28/2018.
 */

public class MainItemVO implements Serializable{
    int num;
    String address;
    Place place;

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
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
