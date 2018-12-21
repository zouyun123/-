package com.pengllrn.tegm.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pengllrn.tegm.R;
import com.pengllrn.tegm.adapter.MyPopuAdapter;
import com.pengllrn.tegm.bean.School;
import com.pengllrn.tegm.bean.Statistics;
import com.pengllrn.tegm.constant.Constant;
import com.pengllrn.tegm.gson.ParseJson;
import com.pengllrn.tegm.utils.FileCache;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class StatisticsAct extends AppCompatActivity {

    private String applyUrl = Constant.URL_STATISTICS;

    private LinearLayout ll_school;
    private LinearLayout ll_statistics;
    private CheckBox cb_school;
    private CheckBox cb_statistics;
    private ColumnChartView columnchart;
    private TextView tv_info;

    private MyPopuAdapter myPopuAdapter;
    private ParseJson mParseJson = new ParseJson();
    private List<String> listSchool = new ArrayList<>();
    private List<String> listStatistics = new ArrayList<>();
    private List<School> list_School = new ArrayList<>();


    //zouyun
    private JSONArray myarray;//


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 0x2017:
                    String responseData = (msg.obj).toString();
//                    List<Statistics> statisticses = mParseJson.Json2Statistics(responseData);
                    List<Statistics> statisticses = new ArrayList<>();
                    try {
                        JSONObject object = new JSONObject(responseData);
                        JSONArray array = object.getJSONArray("property_info");
                        for(int i = 0; i < array.length(); i++){
                            JSONObject object1 = array.getJSONObject(i);
                            String type = object1.getString("device_type");
                            float value = (float) object1.getDouble("value");
                            Statistics a = new Statistics(type, value);
                            statisticses.add(a);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    System.out.println("资产统计的statics：" + statisticses);

                    setChartView(statisticses);
                    tv_info.setText("统计信息如下：");
                    break;
                default:
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back);
            actionBar.setTitle("");
        }

        ll_school = (LinearLayout) findViewById(R.id.ll_school_name);
        ll_statistics = (LinearLayout) findViewById(R.id.ll_statistics_name);
        cb_school = (CheckBox) findViewById(R.id.cb_school_name);
        cb_statistics = (CheckBox) findViewById(R.id.cb_statistics_name);
        columnchart = (ColumnChartView) findViewById(R.id.columnchart);
        tv_info = (TextView) findViewById(R.id.info);

        columnchart.setZoomEnabled(true);
        columnchart.setInteractive(false);
        initDatas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myPopuAdapter = new MyPopuAdapter(this);

        setCbListener();
//        if (list_School.size() > 0) {
          if(myarray.length() > 0){
            cb_school.setText(listSchool.get(0));
            cb_statistics.setText(listStatistics.get(0));
            getData(listSchool.get(0), listStatistics.get(0));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_statistics,menu);
        return true;
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

    private void setCbListener() {
        //学校LinearLayout点击监听器
        ll_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_school.isChecked()) cb_school.setChecked(false);
                else cb_school.setChecked(true);
            }
        });
        //统计选项LinearLayout点击监听器
        ll_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_statistics.isChecked()) cb_statistics.setChecked(false);
                else cb_statistics.setChecked(true);
            }
        });
        //学校CheckBox的监听
        cb_school.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myPopuAdapter.filterTabToggle(isChecked, ll_school, listSchool, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        myPopuAdapter.hidePopListView();
                        cb_school.setText(listSchool.get(position));
                        getData(cb_school.getText().toString(), cb_statistics.getText().toString());
                    }
                }, cb_school, cb_statistics);
            }
        });

        //统计选项CheckBox的监听
        cb_statistics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myPopuAdapter.filterTabToggle(isChecked, ll_statistics, listStatistics, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        myPopuAdapter.hidePopListView();
                        cb_statistics.setText(listStatistics.get(position));
                        getData(cb_school.getText().toString(), cb_statistics.getText().toString());
                    }
                }, cb_statistics, cb_school);
            }
        });

    }

    private String read(String filename) {
        FileCache fileCache = new FileCache(getApplication());
        return fileCache.readFromCacheDir(filename);
    }

    private void initDatas() {
//        listStatistics.add("采购统计");
//        listStatistics.add("库存统计");
        listStatistics.add("价值统计");
 //       listStatistics.add("报废统计");

        String data = read("schoolList");
        System.out.println("资产统计的data：" + data);
        if (data != null && !data.equals("")) {
//            list_School = mParseJson.SchoolPoint(data);
            try {
                JSONObject jsonObject = new JSONObject(data);
                myarray = jsonObject.getJSONArray("school_list");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println("资产统计的array：" + myarray);
//            if (list_School != null) {
//                for (int i = 0; i < list_School.size(); i++) {
//                    listSchool.add(list_School.get(i).getSchoolname());
//                }
//            }
            if(myarray.length() != 0){
                for(int i = 0; i < myarray.length(); i++){
                    try {
                        JSONObject myobject = myarray.getJSONObject(i);
                        String schoolname = myobject.getString("schoolname");
                        listSchool.add(schoolname);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println("资产统计的schoolname：" + listSchool);
                }
            }
        }
    }

    private void getData(String school, String statistics) {
        tv_info.setText("正在查询...");
        String type = "";
        String schoolid = "";
        switch (statistics) {
            case "采购统计":
                type = "1";
                break;
            case "库存统计":
                type = "2";
                break;
            case "价值统计":
                type = "3";
                break;
            case "报废统计":
                type = "4";
                break;
            default:
                type = "0";
                break;

        }
//        for (int i = 0; i < list_School.size(); i++) {
//            if (school.equals(list_School.get(i).getSchoolname())) {
//                schoolid = list_School.get(i).getId();
//                break;
//            }
//        }

        //zouyun
        for(int i = 0; i < myarray.length(); i++){
            try {
                JSONObject object = myarray.getJSONObject(i);
                if(school.equals(object.getString("schoolname"))){
                    schoolid = object.getString("schoolid");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("schoolid: " + schoolid);


        if (!school.equals("") || type.equals("0")) {
            //访问网络
//            OkHttp okHttp = new OkHttp(getApplicationContext(), mHandler);
//            RequestBody requestBody = new FormBody.Builder().add("school", schoolid).add("type", type).build();
//            okHttp.postDataFromInternet(applyUrl, requestBody);
            String url = getUrl2("http://47.107.37.50:8000/get_school_property/", getUrl1("schoolid", schoolid));
            System.out.println("资产统计的url：" + url);
            getschool_statics(url);

        }
    }

    private void setChartView(List<Statistics> list) {
        //每个集合显示几条柱子
        int numSubcolumns = 1;
        //显示多少个集合
        int numColumns = list.size();
        //保存所有的柱子
        List<Column> columns = new ArrayList<Column>();
        //保存每个竹子的值
        List<SubcolumnValue> values;
        List<AxisValue> axisXValues = new ArrayList<AxisValue>();
        //对每个集合的柱子进行遍历
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            //循环所有柱子（list）
            for (int j = 0; j < numSubcolumns; ++j) {
                //创建一个柱子，然后设置值和颜色，并添加到list中
                values.add(new SubcolumnValue(list.get(i).getValue(), ChartUtils.pickColor()));
                //设置X轴的柱子所对应的属性名称
                axisXValues.add(new AxisValue(i).setLabel(list.get(i).getType()));
            }
            //将每个属性的拥有的柱子，添加到Column中
            Column column = new Column(values);
            //是否显示每个柱子的Lable
            column.setHasLabels(true);
            //设置每个柱子的Lable是否选中，为false，表示不用选中，一直显示在柱子上
            column.setHasLabelsOnlyForSelected(false);
            //将每个属性得列全部添加到List中
            columns.add(column);
        }
        //设置Columns添加到Data中
        ColumnChartData columnChartData = new ColumnChartData(columns);
        //设置X轴显示在底部，并且显示每个属性的Lable，字体颜色为黑色，X轴的名字为“学历”，每个柱子的Lable斜着显示，距离X轴的距离为8
        columnChartData.setAxisXBottom(new Axis(axisXValues).setHasLines(true).setTextColor(Color.BLACK).setHasTiltedLabels(true).setMaxLabelChars(8));
        //属性值含义同X轴
        columnChartData.setAxisYLeft(new Axis().setHasLines(true).setName("价值").setTextColor(Color.BLACK).setMaxLabelChars(5));
        //最后将所有值显示在View中
        columnchart.setColumnChartData(columnChartData);

    }

    //ToDo zouyun 获取学校资产统计
    public void getschool_statics(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    SharedPreferences pref = getSharedPreferences("mycookie", Context.MODE_PRIVATE);
                    String sessionid = pref.getString("sessionid", " ");

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().addHeader("cookie", sessionid).url(url).build();
                    Response response = client.newCall(request).execute();
                    String responsedata = response.body().string();
                    System.out.println("资产统计的responsedata：" + responsedata);

                    if(response.isSuccessful()){
                        Message msg = new Message();
                        msg.what = 0x2017;
                        msg.obj = responsedata;
                        mHandler.sendMessage(msg);
                    }else{
                        System.out.println("资产统计的网络请求失败");
                    }
                }catch(Exception e){
                    System.out.println("资产统计的网络请求异常");
                }
            }
        }).start();
    }






    // ToDo zouyun 携带信息get请求的url的转换方法1
    public static HashMap<String, String> getUrl1(String key1, String value1){
        HashMap hashmap = new HashMap();
        hashmap.put(key1, value1);
        return hashmap;
    }
    // ToDo zouyun携带信息get请求的url的转换方法2
    public static String getUrl2(String actionUrl, HashMap<String, String> paramsMap) {
        StringBuilder myBuilder = new StringBuilder();
        String a = null;
        try {
            //处理参数
            int pos = 0;
            for(String key : paramsMap.keySet()) {
                if (pos > 0) {
                    myBuilder.append("&");
                }
                //对参数进行URLEncoder
                myBuilder.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //补全请求地址
            String requestUrl = String.format("%s?%s", actionUrl, myBuilder.toString());
            a = requestUrl;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return a;
    }

}
