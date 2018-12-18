package com.pengllrn.tegm.Aoao;

import java.util.List;

/**
 * Created by Aoao Bot on 2018/12/10.
 */

public class AlarmLists {

    private int status;
    private String schoolname;
    private List<AlarmDevice> alarm_list;

    public int getStatus() {
        return status;
    }

    public String getSchoolname () {
        return schoolname;
    }

    public List<AlarmDevice> getAlarm_list() {
        return alarm_list;
    }
}
