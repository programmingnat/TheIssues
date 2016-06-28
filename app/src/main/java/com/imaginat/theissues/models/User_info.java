package com.imaginat.theissues.models;

/**
 * Created by nat on 6/22/16.
 */
public class User_info {

    private String mZipCode;
    private String mLatitude;
    private String mLongitude;
    private String[] mDistricts;

    public User_info(){

    }
    public String getZipCode() {
        return mZipCode;
    }

    public void setZipCode(String zipCode) {
        mZipCode = zipCode;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    public String[] getDistrict() {
        return mDistricts;
    }

    public void setDistricts(String[] district) {
        mDistricts = district;
    }
}
