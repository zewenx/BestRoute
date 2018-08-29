package com.francis.bestroute.vo;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by zeven on 2/28/2018.
 */

public class MainItemVO implements Serializable{
    int num;
    String address;
    String address2;
    double latitude;
    double longitude;

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

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }


    public void setPlace(Place place) {
        setLatitude(place.getLatLng().latitude);
        setLongitude(place.getLatLng().longitude);
        setName(place.getName().toString());
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
