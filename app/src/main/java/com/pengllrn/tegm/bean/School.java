package com.pengllrn.tegm.bean;

/**
 * Created by pengl on 2018/1/17.
 */

public class School {
    private String schoolid;
    private String schoolname;
    private Double longitude; //经度
    private Double latitude;  //纬度
    private int rate;

    private String totaldevice;
    private String usingdevice;

    public School(String id, String schoolname, Double longitude, Double latitude) {
        this.schoolid = id;
        this.schoolname = schoolname;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rate = rate;
        this.totaldevice = totaldevice;
        this.usingdevice = usingdevice;
    }

    public String getId() {
        return schoolid;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public int getRate() {
        return rate;
    }

    public String getTotaldevice() {
        return totaldevice;
    }

    public String getUsingdevice() {
        return usingdevice;
    }
}
