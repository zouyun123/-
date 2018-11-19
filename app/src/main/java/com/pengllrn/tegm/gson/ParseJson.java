package com.pengllrn.tegm.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pengllrn.tegm.Aoao.LoginStatus;
import com.pengllrn.tegm.bean.AlarmList;
import com.pengllrn.tegm.bean.All;
import com.pengllrn.tegm.bean.ApplyCenterBean;
import com.pengllrn.tegm.bean.DamageDevice;
import com.pengllrn.tegm.bean.DevDetail;
import com.pengllrn.tegm.bean.Device;
import com.pengllrn.tegm.bean.DeviceApply;
import com.pengllrn.tegm.bean.Gis;
import com.pengllrn.tegm.bean.School;
import com.pengllrn.tegm.bean.Statistics;
import com.pengllrn.tegm.bean.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseJson {
    public List<Device> JsonToDevice(String json) {
        Gson gson = new Gson();
        All all = gson.fromJson(json, All.class);
        return all.getDevice();
    }

    public List<String> JsonToBuildname(String json) {
        Gson gson = new Gson();
        All all = gson.fromJson(json, All.class);
        List<String> buildName = new ArrayList<>();
        for (int i = 0; i < all.getSchoolbyid().size(); i++) {
            buildName.add(all.getSchoolbyid().get(i).getBuilding());
        }
        return buildName;
    }
    public All JsonToAll(String json){
        Gson gson = new Gson();
        All all = gson.fromJson(json, All.class);
        return all;
    }

    public DevDetail JsonToDevDetail(String json){
        Gson gson = new Gson();
        DevDetail detail = gson.fromJson(json,DevDetail.class);
        return detail;
    }

    public DeviceApply Json2DamageList(String json){
        Gson gson = new Gson();
        DeviceApply deviceApply = gson.fromJson(json,DeviceApply.class);
        return deviceApply;
    }

    public DamageDevice Json2DamageDevice(String json){
        Gson gson = new Gson();
        DamageDevice damageDevice = gson.fromJson(json,DamageDevice.class);
        return damageDevice;
    }

    public Gis Json2Gis(String json){
        Gson gson = new Gson();
        Gis gis = gson.fromJson(json,Gis.class);
        return gis;
    }

    public User Json2User(String json){
        Gson gson = new Gson();
        User user = gson.fromJson(json,User.class);
        return user;
    }

    public LoginStatus Json2LoginStatus(String json) {
        Gson gson = new Gson();
        LoginStatus loginStatus = gson.fromJson(json,LoginStatus.class);
        return loginStatus;
    }

    public List<Statistics> Json2Statistics(String json){
        Gson gson = new Gson();
        List<Statistics> statistics = new ArrayList<>();
        statistics = gson.fromJson(json,new TypeToken<ArrayList<Statistics>>(){}.getType());
        return statistics;
    }

    public List<AlarmList> Json2AlarmList(String json){
        Gson gson = new Gson();
        List<AlarmList> alarmLists = new ArrayList<>();
        alarmLists = gson.fromJson(json,new TypeToken<ArrayList<AlarmList>>(){}.getType());
        return alarmLists;
    }

    public List<School> SchoolPoint(String json) {
        List<School> listSchool = new ArrayList<School>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray shoolListObject = jsonObject.getJSONArray("school_list");
            for (int i = 0; i < shoolListObject.length(); i++) {
                JSONObject jObject=shoolListObject.getJSONObject(i);
                String id=jObject.getString("schoolid");
                String schoolname=jObject.getString("schoolname");
                Double longitude=jObject.getDouble("longitude");
                Double latitude = jObject.getDouble("latitude");
                /*int rate = jObject.getInt("rate");
                String totaldevice = jObject.getString("totaldevice");
                String usingdevice = jObject.getString("usingdevice");*/
                listSchool.add(new School(id,schoolname,longitude,latitude));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSchool;
    }

    public List<ApplyCenterBean> ApplyList(String json){
        List<ApplyCenterBean> listApply = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject=jsonArray.getJSONObject(i);
                String deviceid = jObject.getString("deviceid");
                String devicenum = jObject.getString("devicenum");
                String appliername = jObject.getString("name");
                String type = jObject.getString("type");
                String datetime = jObject.getString("datetime");
                String deal_status = jObject.getString("deal_status");
                listApply.add(new ApplyCenterBean(deviceid,devicenum,appliername,type,datetime,deal_status));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listApply;
    }


}
