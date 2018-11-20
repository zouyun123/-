package com.pengllrn.tegm.Aoao;

/**
 * Created by Aoao Bot on 2018/11/20.
 */

public class DevicesUsageLists {

    private String schoolid;
    private String schoolname;
    private int total_device;
    private int using_device;
    private int rate;

    public DevicesUsageLists(String schoolid,String schoolname,int total_device,int using_device,int rate) {
        this.schoolid = schoolid;
        this.schoolname = schoolname;
        this.total_device = total_device;
        this.using_device = using_device;
        this.rate = rate;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public int getTotal_device() {
        return total_device;
    }

    public int getUsing_devicce() {
        return using_device;
    }

    public int getRate() {
        return rate;
    }
}
