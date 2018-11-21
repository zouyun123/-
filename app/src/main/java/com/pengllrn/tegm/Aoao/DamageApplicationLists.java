package com.pengllrn.tegm.Aoao;

/**
 * Created by Aoao Bot on 2018/11/21.
 */

public class DamageApplicationLists {

    private int deal_status;
    private String name;
    private int applicationid;
    private String datetime;
    private String deviceid;
    private String type;
    private String devicenum;

    public DamageApplicationLists(int deal_status,String name,int applicationid,String datetime,String deviceid,String type,String devicenum) {
        this.deal_status = deal_status;
        this.name = name;
        this.applicationid = applicationid;
        this.datetime = datetime;
        this.deviceid = deviceid;
        this.type = type;
        this.devicenum = devicenum;
    }

    public int getDeal_status() {
        return deal_status;
    }

    public String getName() {
        return name;
    }

    public int getApplicationid() {
        return applicationid;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public String getType() {
        return type;
    }

    public String getDevicenum() {
        return devicenum;
    }
}
