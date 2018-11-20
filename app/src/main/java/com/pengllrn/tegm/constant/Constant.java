package com.pengllrn.tegm.constant;

/**
 * Created by pengl on 2018/1/24.
 * 系统常量池
 */

public class Constant {
    /**
     * 系统所使用到的URL
     **/
    public static final String SERVER_URL = "http://47.107.37.50:8000/";//服务器所在站点以及端口号
    /**
     * 用户登录活动网址
     **/
    public static final String URL_LOGIN = SERVER_URL + "login/";
    /**
     * 主界面，MainActivity,获取用户权限内的学校信息及设备的使用数量
     **/

    public static final String URL_LOGOUT = SERVER_URL + "logout/";
    /*
    * 用戶註銷
    */
    public static final String URL_MAIN = SERVER_URL + "get_school_list/";
    /*
    * 獲取學校樓棟列表
    */
    public static final String URL_BUILDING = SERVER_URL + "get_buildings_in_school/";
    /*
    * 獲取學校中設備使用情況
    */
    public static final String URL_DEVICE_USAGE = SERVER_URL + "get_devices_usage/";
   /*
   * 獲取學校中的教室
   */
    public static final String URL_ROOM = SERVER_URL + "get_rooms_list/";
   /*
   * 獲取教室中設備
   */
    public static final String URL_DEVICES_IN_ROOM = SERVER_URL + "get_devices_in_room/";
    /**
     * DamageApplyActivity,报废申请活动界面，用户填写好报废信息进行报废提交
     **/
    public static final String URL_DAMAGE_APPLY = SERVER_URL + "damageapply/";
    /**
     * DevDetailActivity,获取设备详细信息界面，根据用户上传的deviceid 进行查询
     **/
    public static final String URL_DEVICE_DETAIL = SERVER_URL + "getdevicedetail/";
    /**
     * DeviceInRoom,获取每个房间内的所有设备界面，根据活动提供的学校，建筑名，房间名查找。
     */
    public static final String URL_GIS = SERVER_URL + "gis/";
    /**
     * LookDamageDevice,查看报废的设备
     * type=1:根据活动提供的deviceid 查询相关信息，初始化界面
     * type=2:提交用户的处理结果
     */
    public static final String URL_DEAL_DAMAGE = SERVER_URL + "dealdamage/";
    /**
     * SearchActivity,查询功能界面。根据用户的查询条件，提交查询
     */
    public static final String URL_SEARCH = SERVER_URL + "search/";
    /**
     * My_ApplyFg,碎片。获取报废申请记录表
     * type = 1:获取我的历史报废申请记录
     * type = 2：具有权限的管理员获取本校的用户申请
     */
    public static final String URL_APPLY_LIST = SERVER_URL + "showapplylist/";
    /**
     * StatisticsAct,数据统计
     * type = 1:采购统计
     * type = 2:库存统计
     * type = 3:价值统计
     * type = 4:报废统计
     */
    public static final String URL_STATISTICS = SERVER_URL + "statistics/";

    public static final String URL_ALARM = SERVER_URL + "alarm/";

    /**
     * 系统使用的其他全局常量
     */
    /**
     * 学校后台管理人员
     **/
    public static final int SCHOOL_BACK_MANAGER = 1;

    /**
     * 学校资产管理人员
     **/
    public static final int SCHOOL_PROPERTY_MANAGER = 2;

    /**
     * 学校普通用户
     **/
    public static final int SCHOOL_ORDINARY_USER = 3;

    /**
     * 地区领导等用户
     **/
    public static final int AREA_USER = 4;

    /**系统使用到的全局变量**/
    /**
     * 用户类别
     **/
    public static String USER_TYPE;
    /**
     * 用户姓名
     **/
    public static String USER_NAME;
    /**
     * 用户id
     **/
    public static String USER_ID;


}
