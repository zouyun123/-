package com.pengllrn.tegm.Aoao;

/**
 * Created by Aoao Bot on 2018/11/20.
 */

public class BuildingLists {

    private String schoolid;
    private String buildingname;
    private String schoolname;

    public BuildingLists(String buildingname, String schoolname) {
        this.buildingname = buildingname;
        this.schoolname = schoolname;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public String getSchoolname() {
        return schoolname;
    }
}
