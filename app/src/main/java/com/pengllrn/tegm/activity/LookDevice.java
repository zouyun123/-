package com.pengllrn.tegm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.pengllrn.tegm.R;
import com.pengllrn.tegm.fragment.BuildingListFg;
import com.pengllrn.tegm.fragment.SchoolListFg;
import com.pengllrn.tegm.utils.FileCache;

public class LookDevice extends AppCompatActivity {
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lool_device);
        //A
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.close);
            actionBar.setTitle("");
        }
        Intent intent = getIntent();
        String schoolid=intent.getStringExtra("schoolid");

        //初始化fragment
        if(schoolid != null && !schoolid.equals("") ){//是什么？
            Bundle bundle = new Bundle();
            bundle.putString("schoolid",schoolid);
            BuildingListFg buildingListFg = new BuildingListFg();
            buildingListFg.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.fragment_list, buildingListFg);
            transaction.commit();
        }else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_list, new SchoolListFg());
            transaction.commit();
        }
        fab = (FloatingActionButton) findViewById(R.id.fab_back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LookDevice.super.onBackPressed();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }

    public String read(String filename){
        FileCache fileCache = new FileCache(getApplication());
        return fileCache.readFromCacheDir(filename);
    }

}
