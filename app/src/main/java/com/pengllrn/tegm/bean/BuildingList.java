package com.pengllrn.tegm.bean;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${UTODO}
 * @updateAuthor ${Author}$
 * @updateDate2017/11/20.
 */

public class BuildingList {
    private String buildingname;
    private String totaldevice;
    private String usingdevice;

    public BuildingList(String schoolname, String buildingname) {
        this.buildingname = schoolname;
        this.totaldevice = buildingname;
    }


    public String getBuildingname() {
        return buildingname;
    }

    public String getTotaldevice() {
        return totaldevice;
    }

    public String getUsingdevice() {
        return usingdevice;
    }

}
