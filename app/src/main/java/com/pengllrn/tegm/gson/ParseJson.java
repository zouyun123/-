package com.pengllrn.tegm.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pengllrn.tegm.Aoao.AlarmLists;
import com.pengllrn.tegm.Aoao.ApplicationCheckStatus;
import com.pengllrn.tegm.Aoao.BuildingLists;
import com.pengllrn.tegm.Aoao.DamageApplicationDetailLists;
import com.pengllrn.tegm.Aoao.DamageApplicationLists;
import com.pengllrn.tegm.Aoao.DevicesInRoom;
import com.pengllrn.tegm.Aoao.DevicesUsageLists;
import com.pengllrn.tegm.Aoao.LoginStatus;
import com.pengllrn.tegm.bean.AlarmList;
import com.pengllrn.tegm.bean.All;
import com.pengllrn.tegm.bean.ApplyCenterBean;
import com.pengllrn.tegm.bean.DamageDevice;
import com.pengllrn.tegm.bean.DevDetail;
import com.pengllrn.tegm.bean.Device;
import com.pengllrn.tegm.bean.DeviceApply;
import com.pengllrn.tegm.bean.Gis;
import com.pengllrn.tegm.bean.Room;
import com.pengllrn.tegm.bean.School;
import com.pengllrn.tegm.bean.Statistics;
import com.pengllrn.tegm.bean.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



public class ParseJson {

    private List<DevicesUsageLists> listDevicesUsage = new ArrayList<DevicesUsageLists>();
    private List<DamageApplicationLists> listDamageApplication = new ArrayList<DamageApplicationLists>();
    private List<DamageApplicationDetailLists> listDamageApplicationDetail = new ArrayList<DamageApplicationDetailLists>();

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

    public ApplicationCheckStatus Json2ApplicationCheckStatus(String json) {
        Gson gson = new Gson();
        ApplicationCheckStatus applicationCheckStatus = gson.fromJson(json,ApplicationCheckStatus.class);
        return applicationCheckStatus;
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

    public AlarmLists Json2AlarmLists(String json) {
        Gson gson = new Gson();
        AlarmLists alarmLists = gson.fromJson(json,AlarmLists.class);
        return alarmLists;
    }

    public List<School> SchoolPoint(String json) {
        List<School> listSchool = new ArrayList<School>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray shoolListArray = jsonObject.getJSONArray("school_list");
            for (int i = 0; i < shoolListArray.length(); i++) {
                JSONObject jObject=shoolListArray.getJSONObject(i);
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

    public List<BuildingLists> BuildingPoint(String json) {
        List<BuildingLists> listBuilding = new ArrayList<BuildingLists>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray buildingListArray = jsonObject.getJSONArray("buildinglist");
            for (int i = 0;i < buildingListArray.length();i++) {
                JSONObject jObject = buildingListArray.getJSONObject(i);
                String schoolid = jObject.getString("schoolid");
                String buildingname = jObject.getString("buildingname");
                String schoolname = jObject.getString("schoolname");
                listBuilding.add(new BuildingLists(buildingname,schoolname));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBuilding;
    }

    public List<Room> RoomListsPoint(String json) {
        List<Room> listRoom = new ArrayList<Room>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray roomListArray = jsonObject.getJSONArray("room_list");
            for (int i = 0;i < roomListArray.length();i++) {
                JSONObject jObject = roomListArray.getJSONObject(i);
                String roomid = jObject.getString("roomid");
                String buildingname = jObject.getString("buildingname");
                String roomname = jObject.getString("roomname");
                listRoom.add(new Room(buildingname,roomname,roomid));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRoom;
    }

    public List<DevicesInRoom> DevicesInRoomPoint(String json) {
        List<DevicesInRoom> listDeviceInRoom = new ArrayList<DevicesInRoom>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray devicesinroomArray = jsonObject.getJSONArray("device_list");
            for (int i = 0;i < devicesinroomArray.length();i++) {
                JSONObject jObject = devicesinroomArray.getJSONObject(i);
                int OrderNum = jObject.getInt("OrderNum");
                String typename = jObject.getString("typename");
                String DeviceId = jObject.getString("DeviceId");
                String schoolname = jObject.getString("schoolname");
                boolean UseFlag = jObject.getBoolean("UseFlag");
                String description = jObject.getString("description");
                String configureinfo = jObject.getString("configureinfo");
                String devicekind = jObject.getString("devicekind");
                String DeviceNum = jObject.getString("DeviceNum");
                listDeviceInRoom.add(new DevicesInRoom(OrderNum,typename,DeviceId,schoolname,UseFlag,description,configureinfo,devicekind,DeviceNum));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDeviceInRoom;
    }

    public List<DevicesUsageLists> DevicesUsagePoint(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject devicesUsageObject = jsonObject.getJSONObject("device_usage");

            String schoolid = devicesUsageObject.getString("schoolid");
            String schoolname = devicesUsageObject.getString("schoolname");
            int total_device = devicesUsageObject.getInt("total_device");
            int using_device = devicesUsageObject.getInt("using_device");
            int rate;
            if (total_device != 0) {
                double Rate = new BigDecimal((float)using_device/total_device).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                rate = (int) (Rate * 100);
                System.out.println("Rate is: " + Rate);
                System.out.println("usingdevice is " + using_device);
                System.out.println("totaldevice is " + total_device);
            } else {
                rate = 0;
            }
            listDevicesUsage.add(new DevicesUsageLists(schoolid,schoolname,total_device,using_device,rate));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listDevicesUsage;
    }

    public List<DamageApplicationLists> DamageApplicationListsPoint(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray damageapplicationArray = jsonObject.getJSONArray("application_list");
            for (int i = 0;i < damageapplicationArray.length();i++) {
                JSONObject jObject = damageapplicationArray.getJSONObject(i);
                int deal_status = jObject.getInt("deal_status");
                String name = jObject.getString("name");
                int applicationid = jObject.getInt("applicationid");
                String datetime = jObject.getString("datetime");
                String deviceid = jObject.getString("deviceid");
                String type = jObject.getString("type");
                String devicenum = jObject.getString("devicenum");
                listDamageApplication.add(new DamageApplicationLists(deal_status,name,applicationid,datetime,deviceid,type,devicenum));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDamageApplication;
    }

    public List<DamageApplicationDetailLists> DamageApplicationDetailPoint(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject damageapplicationdetailobject = jsonObject.getJSONObject("application_detail");
            String appliertel = damageapplicationdetailobject.getString("appliertel");
            String datetime = damageapplicationdetailobject.getString("datetime");
            String devicenum = damageapplicationdetailobject.getString("devicenum");
            String schoolid = damageapplicationdetailobject.getString("schoolid");
            String damagedepict = damageapplicationdetailobject.getString("damagedepict");
            String deviceid = damageapplicationdetailobject.getString("deviceid");
            String applier = damageapplicationdetailobject.getString("applier");
            String type = damageapplicationdetailobject.getString("type");
            String photo1 = damageapplicationdetailobject.getString("photo1");
            String photo2 = damageapplicationdetailobject.getString("photo2");
            String photo3 = damageapplicationdetailobject.getString("photo3");
            String photo4 = damageapplicationdetailobject.getString("photo4");
            String photo5 = damageapplicationdetailobject.getString("photo5");
            String photo6 = damageapplicationdetailobject.getString("photo6");
            listDamageApplicationDetail.add(new DamageApplicationDetailLists(appliertel,datetime,
                    devicenum,schoolid,damagedepict,deviceid,applier,type,photo1,photo2,photo3,
                    photo4,photo5,photo6));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDamageApplicationDetail;
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
