package com.example.gaurav.bootcamplocator.model;

/**
 * Created by Gaurav on 2017-04-25.
 */

public class DevSlopes {
    private float longitude;
    private float latitude;
    private String locationTittle;
    private String locationAddress;
    private String locationImgUrl;
    final String DRAWABLE = "drawable/";


    public DevSlopes(float latitude, float longitude, String locationTittle, String locationAddress, String locationImgUrl) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.locationTittle = locationTittle;
        this.locationAddress = locationAddress;
        this.locationImgUrl = locationImgUrl;

    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLocationTittle(String locationTittle) {
        this.locationTittle = locationTittle;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public void setLocationImgUrl(String locationImgUrl) {
        this.locationImgUrl = locationImgUrl;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getLocationTittle() {
        return locationTittle;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getLocationImgUrl() {
        return  DRAWABLE + locationImgUrl;
    }
}
