package com.pengllrn.tegm.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pengllrn.tegm.Aoao.AddingUrl;
import com.pengllrn.tegm.Aoao.AlarmDevice;
import com.pengllrn.tegm.R;
import com.pengllrn.tegm.adapter.AlarmAdaper;
import com.pengllrn.tegm.adapter.MyPopuAdapter;
import com.pengllrn.tegm.bean.School;
import com.pengllrn.tegm.constant.Constant;
import com.pengllrn.tegm.gson.ParseJson;
import com.pengllrn.tegm.internet.OkHttp;
import com.pengllrn.tegm.utils.FileCache;
import com.pengllrn.tegm.utils.SharedHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeviceAlarmAct extends AppCompatActivity {
    private String applyUrl = Constant.URL_GET_ALARM_DEVICE;

    private ListView list_alarm;
    private List<AlarmDevice> alarmLists = new ArrayList<>();
    private LinearLayout ll_alarm_type;
    private CheckBox cb_alram_type;
    private TextView tv_count;

    private List<String> alarmTypeList = new ArrayList<>();

    private SharedHelper sharedHelper = new SharedHelper(this);
    private ParseJson mParseJson = new ParseJson();
    private MyPopuAdapter myPopuAdapter = new MyPopuAdapter(this);

    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x2020:
                    String responseData = (msg.obj).toString();
                    System.out.println("Alarm reponse is " + responseData);
                    alarmLists = mParseJson.Json2AlarmLists(responseData).getAlarm_list();
                    tv_count.setText("总共计"+ alarmLists.size()+ "件设备");
                    list_alarm.setAdapter(new AlarmAdaper(DeviceAlarmAct.this,alarmLists,R.layout.item_alarm));
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_alarm);
        initView();
        alarmTypeList.add("全部");
        alarmTypeList.add("设备使用状态异常");
        alarmTypeList.add("使用时间异常");
        alarmTypeList.add("位置异常");
        setCbListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        String userid = sharedHelper.readbykey("userid");
//        String school = sharedHelper.readbykey("school");
//        OkHttp okHttp = new OkHttp(getApplicationContext(),mHander);
//        RequestBody requestBody = new FormBody.Builder().add("userid",userid).add("school",school).build();
//        okHttp.postDataFromInternet(applyUrl, requestBody);
        String data = read("schoolList");
        List<School> listSchool = new ArrayList<School>();
        if (data != null && !data.equals("")) {
            listSchool = mParseJson.SchoolPoint(data);
            String getalarmlisturl;
            HashMap<String,String> hashMap;
            if (listSchool != null) {
                for (int i = 0;i < listSchool.size();i++) {
                    String schoolid = listSchool.get(i).getId();
                    hashMap = AddingUrl.createHashMap1("schoolid",schoolid);
                    OkHttp okHttp = new OkHttp(getApplicationContext(),mHander);
                    getalarmlisturl = AddingUrl.getUrl(applyUrl,hashMap);
                    okHttp.getDataFromInternet(getalarmlisturl);
                }
            }
        }

//        alarmLists.add(new AlarmList("03","更新异常","投影仪屏幕","科研楼B322室 ","2018-02-24 18:20:17","电子科技大学","Xl473297jf84-ds"));
//        alarmLists.add(new AlarmList("04","位置异常","投影仪屏幕","科研楼B322室 ","2018-02-24 18:20:17","电子科技大学","Xl4732sdadsa8"));
//        alarmLists.add(new AlarmList("05","更新异常","投影仪屏幕","科研楼B322室 ","2018-02-24 18:20:17","电子科技大学","Xl473297jdsadsasa8"));
//        alarmLists.add(new AlarmList("06","使用时间异常","投影仪屏幕","科研楼B322室 ","2018-02-24 18:20:17","电子科技大学","Xl473dsaf84ds"));
//        alarmLists.add(new AlarmList("07","更新异常","投影仪屏幕","科研楼B322室 ","2018-02-24 18:20:17","电子科技大学","Xl473297jf84-dsa8"));
//        alarmLists.add(new AlarmList("09","更新异常","电脑","科研楼B322室 ","2018-02-24 18:20:17","电子科技大学","Xl473297jf84-ds"));
//        alarmLists.add(new AlarmList("10","位置异常","电脑","科研楼B322室 ","2018-02-24 18:20:17","电子科技大学","Xl4732sdadsa8"));
//        alarmLists.add(new AlarmList("11","更新异常","电脑","科研楼B322室 ","2018-02-24 18:20:17","电子科技大学","Xl473297jdsadsasa8"));
//        alarmLists.add(new AlarmList("12","使用时间异常","电脑","科研楼B322室 ","2018-02-24 18:20:17","电子科技大学","Xl473dsaf84ds"));
//        alarmLists.add(new AlarmList("13","更新异常","投影仪屏幕","科研楼B322室 ","2018-02-24 18:20:17","电子科技大学","Xl473297jf84-dsa8"));
//        list_alarm.setAdapter(new AlarmAdaper(DeviceAlarmAct.this,alarmLists,R.layout.item_alarm));
    }

    private void initView() {
        list_alarm = (ListView) findViewById(R.id.list_alarm);
        ll_alarm_type = (LinearLayout) findViewById(R.id.ll_alarm_type);
        cb_alram_type = (CheckBox) findViewById(R.id.cb_alarm_type);
        tv_count = (TextView) findViewById(R.id.tv_count);
    }

    private void setCbListener() {
        //统计选项LinearLayout点击监听器
        ll_alarm_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_alram_type.isChecked()) cb_alram_type.setChecked(false);
                else cb_alram_type.setChecked(true);
            }
        });

        //统计选项CheckBox的监听
        cb_alram_type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myPopuAdapter.filterTabToggle(isChecked, ll_alarm_type, alarmTypeList, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        myPopuAdapter.hidePopListView();
                        cb_alram_type.setText(alarmTypeList.get(position));
                        getData(cb_alram_type.getText().toString());
                    }
                }, cb_alram_type);
            }
        });
    }

    private void getData(String s) {
        if(s.equals(alarmTypeList.get(0))){
            tv_count.setText("总共计"+ alarmLists.size()+ "件设备");
            list_alarm.setAdapter(new AlarmAdaper(DeviceAlarmAct.this,alarmLists,R.layout.item_alarm));
        }else {
            List<AlarmDevice> alarms = new ArrayList<>();
            for (int i = 0; i < alarmLists.size(); i++) {
                if (alarmLists.get(i).getAlarmtype().equals(s)) {
                    alarms.add(alarmLists.get(i));
                }
            }
            tv_count.setText("总共计" + alarms.size() + "件设备");
            list_alarm.setAdapter(new AlarmAdaper(DeviceAlarmAct.this, alarms, R.layout.item_alarm));
        }
    }

    public String read(String filename){
        FileCache fileCache = new FileCache(getApplication());
        return fileCache.readFromCacheDir(filename);
    }

}
