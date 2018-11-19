package com.pengllrn.tegm.bean;

/**
 * Created by pengl on 2017/12/22.
 */

public class User {
    private String loginid;
    private String username;
    private Boolean isOfficial;
    private String usertype;
    private String school;
    private String job;
    private String email;
    private String wechat;
    private String qq;
    private String regist_time;

    public String getLoginid(){
        return loginid;
    }

    public String getUsername() {
        return username;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getSchool() {
        return school;
    }

    public String getEmail() {
        return email;
    }

    public String getWechat() {
        return wechat;
    }

    public String getQq() {
        return qq;
    }

    public Boolean getIsOfficial() {
        return isOfficial;
    }

    public String getJob() {
        return job;
    }

    public String getRegist_time() {
        return regist_time;
    }
}
