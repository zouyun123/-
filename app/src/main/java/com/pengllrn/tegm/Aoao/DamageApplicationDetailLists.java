package com.pengllrn.tegm.Aoao;

/**
 * Created by Aoao Bot on 2018/11/27.
 */

public class DamageApplicationDetailLists {

    private String appliertel;
    private String datetime;
    private String devicenum;
    private String schoolid;
    private String damagedepict;
    private String deviceid;
    private String applier;
    private String type;

    public DamageApplicationDetailLists(String appliertel,String datetime,String devicenum,String schoolid,String damagedepict,String deviceid,String applier,String type) {
        this.appliertel = appliertel;
        this.datetime = datetime;
        this.devicenum = devicenum;
        this.schoolid = schoolid;
        this.damagedepict = damagedepict;
        this.deviceid = deviceid;
        this.applier = applier;
        this.type = type;
    }

    public String getAppliertel() {
        return appliertel;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getDevicenum() {
        return devicenum;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public String getDamagedepict() {
        return damagedepict;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public String getApplier() {
        return applier;
    }

    public String getType() {
        return type;
    }
}
