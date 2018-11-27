package com.pengllrn.tegm.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pengllrn.tegm.R;
import com.pengllrn.tegm.fragment.My_ApplyFg;
import com.pengllrn.tegm.fragment.Other_ApplyFg;
import com.pengllrn.tegm.utils.FileCache;
import com.pengllrn.tegm.utils.SharedHelper;

public class ApplyCenter extends AppCompatActivity {


    private LinearLayout lv_my_apply;
    private LinearLayout lv_other_apply;
    private TextView tv_my_apply;
    private TextView tv_other_apply;

    private String usertype;

    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_center);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back);
            actionBar.setTitle("");
        }

        lv_my_apply = (LinearLayout) findViewById(R.id.lv_my_apply);
        lv_other_apply = (LinearLayout) findViewById(R.id.lv_other_apply);
        tv_my_apply = (TextView) findViewById(R.id.tv_my_apply);
        tv_other_apply = (TextView) findViewById(R.id.tv_other_apply);
        SharedHelper sharedHelper = new SharedHelper(getApplicationContext());
        usertype = sharedHelper.readbykey("usertype");
        lv_my_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    setStateA();
                    flag = false;
                    //
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.apply_center_fg, new My_ApplyFg());
                    transaction.commit();
                }
            }
        });
        lv_other_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag){
//                    if(usertype.equals("3") || usertype.equals("4")){
//                        Toast.makeText(getApplicationContext(),"对不起，您没有操作权限！",Toast.LENGTH_SHORT).show();
//                    }else {
                    setStateB();
                    flag = true;
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.apply_center_fg, new Other_ApplyFg());
                    transaction.commit();
                }
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.apply_center_fg, new My_ApplyFg());
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    public void setStateA(){
        tv_my_apply.setTextColor(Color.parseColor("#b75906"));
        lv_my_apply.setBackgroundResource(R.mipmap.orange_line);
        tv_other_apply.setTextColor(Color.parseColor("#787878"));
        lv_other_apply.setBackground(null);
    }
    public void setStateB(){
        tv_other_apply.setTextColor(Color.parseColor("#b75906"));
        lv_other_apply.setBackgroundResource(R.mipmap.orange_line);
        tv_my_apply.setTextColor(Color.parseColor("#787878"));
        lv_my_apply.setBackground(null);
    }

    public void save(String data, String filename) {
        FileCache fileCache = new FileCache(getApplicationContext());
        fileCache.saveInCacheDir(data, filename);
    }

    public String read(String filename){
        FileCache fileCache = new FileCache(getApplication());
        return fileCache.readFromCacheDir(filename);
    }
}
