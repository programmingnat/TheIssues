package com.imaginat.theissues.api.sunlightApi.models;

/**
 * Created by nat on 6/22/16.
 */
public class SunLight_District_Query_Result {

    private String state,district;
    private int count=0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "SunLight_District_Query_Result{" +
                "state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", count=" + count +
                '}';
    }
}
