package com.pengllrn.tegm.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengl on 2018/1/17.
 * 用于把后台活动放入到列表中，以便管理
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
