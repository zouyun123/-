package com.pengllrn.tegm.Aoao;

import com.pengllrn.tegm.bean.Device;

/**
 * Created by Aoao Bot on 2018/11/20.
 */

public class DevicesInRoom {

    private int OrderNum;
    private String typename;
    private String DeviceId;
    private String schoolname;
    private boolean UseFlag;
    private String description;
    private String configureinfo;
    private String devicekind;
    private String DeviceNum;

    public DevicesInRoom(int OrderNum,String typename,String DeviceId,String schoolname,boolean UseFlag,String description,String configureinfo,String devicekind,String DeviceNum) {
        this.OrderNum = OrderNum;
        this.typename = typename;
        this.DeviceId = DeviceId;
        this.schoolname = schoolname;
        this.UseFlag = UseFlag;
        this.description = description;
        this.configureinfo = configureinfo;
        this.devicekind = devicekind;
        this.DeviceNum = DeviceNum;
    }

    public int getOrderNum() {
        return OrderNum;
    }

    public String getTypename() {
        return typename;
    }

    public String getDeviceId() {
        return DeviceId;
    }
    public String getSchoolname() {
        return schoolname;
    }

    public boolean isUseFlag() {
        return UseFlag;
    }

    public String getDescription() {
        return description;
    }

    public String getConfigureinfo() {
        return configureinfo;
    }

    public String getDevicekind() {
        return devicekind;
    }

    public String getDeviceNum() {
        return DeviceNum;
    }

}
