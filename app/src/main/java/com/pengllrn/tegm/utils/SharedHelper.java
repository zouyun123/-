package com.pengllrn.tegm.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.pengllrn.tegm.Aoao.LoginStatus;
import com.pengllrn.tegm.bean.User;

/**
 * Created by pengl on 2018/1/15.
 */

public class SharedHelper {

    private Context mContext;

    public SharedHelper(){}

    public SharedHelper(Context mContext){
        this.mContext=mContext;
    }

    //定义一个保存数据的方法
    /**
     *
     */
    public void save(User user){
        SharedPreferences sp = mContext.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginid",user.getLoginid());
        editor.putString("username",user.getUsername());
        editor.putBoolean("isOfficial",user.getIsOfficial());
        editor.putString("usertype",user.getUsertype());
        editor.putString("school",user.getSchool());
        editor.putString("email",user.getEmail());
        editor.putString("wechat",user.getWechat());
        editor.putString("qq",user.getRegist_time());
        editor.putString("regist_time",user.getQq());
        editor.putString("job",user.getJob());
        editor.putString("status","fine");
        editor.apply();
    }

    public void save(LoginStatus loginStatus) {
        SharedPreferences sp = mContext.getSharedPreferences("loginstatus",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("status",loginStatus.getStatus());
        editor.putInt("usertype",loginStatus.getUsertype());
        editor.putString("username",loginStatus.getUsername());
        editor.apply();
    }

    public void save(String loginid,String username,String usertype,String school,
        String email,String wechat,String qq){
        SharedPreferences sp = mContext.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginid",loginid);
        editor.putString("username",username);
        editor.putString("usertype",usertype);
        editor.putString("school",school);
        editor.putString("email",email);
        editor.putString("wechat",wechat);
        editor.putString("qq",qq);
        editor.putString("status","fine");
        editor.apply();
    }

    public String readbykey(String key){
        SharedPreferences sp = mContext.getSharedPreferences("user",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public int readbyKey(String key){
        SharedPreferences sp = mContext.getSharedPreferences("loginstatus",Context.MODE_PRIVATE);
        return sp.getInt(key,100);
    }

    public String readByKey(String key){
        SharedPreferences sp = mContext.getSharedPreferences("loginstatus",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public void clear(){
        SharedPreferences sp = mContext.getSharedPreferences("loginstatus",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    public Boolean readOfficial(){
        SharedPreferences sp = mContext.getSharedPreferences("user",Context.MODE_PRIVATE);
        return sp.getBoolean("isOfficial",false);
    }
}
