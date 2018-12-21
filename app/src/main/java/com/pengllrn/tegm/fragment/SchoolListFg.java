package com.pengllrn.tegm.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pengllrn.tegm.Aoao.AddingUrl;
import com.pengllrn.tegm.Aoao.DevicesUsageLists;
import com.pengllrn.tegm.Aoao.DevicesUsageListsAdapter;
import com.pengllrn.tegm.R;
import com.pengllrn.tegm.activity.LookDevice;
import com.pengllrn.tegm.adapter.SchoolListAdapter;
import com.pengllrn.tegm.bean.School;
import com.pengllrn.tegm.constant.Constant;
import com.pengllrn.tegm.gson.ParseJson;
import com.pengllrn.tegm.internet.OkHttp;

import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${UTODO}
 * @updateAuthor ${Author}$
 * @updateDate2017/11/23.
 */

public class SchoolListFg extends Fragment {
    private String applyUrl = Constant.URL_DEVICE_USAGE;
    private LookDevice lookDeviceActivity;
    private ListView list_gis;

    private ParseJson mParseJson = new ParseJson();
    private List<School> listSchool;
    private boolean flag = false;
    private List<DevicesUsageLists> listDevicesUsage = new ArrayList<DevicesUsageLists>();

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

//    public final int GETOK = 0x2020;
//    public final int WRANG = 0x22;
//    public final int EXCEPTION = 0x30;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 0x2020:
//                    if (flag) {
//                        listDevicesUsage.clear();
//                        flag = !flag;
//                    }
//                    List<School> listSchool = mParseJson.Json2Gis(responseData).getSchoolLists();
//                    if(listSchool!=null) {
//                        list_gis.setAdapter(new SchoolListAdapter(lookDeviceActivity,
//                                listSchool, R.layout.base_list_item));
//                        setListListener(listSchool);
//                    }
                    String responseData = (msg.obj).toString();
                    listDevicesUsage = mParseJson.DevicesUsagePoint(responseData);
                    if (listDevicesUsage != null) {
                        list_gis.setAdapter(new DevicesUsageListsAdapter(lookDeviceActivity,listDevicesUsage,R.layout.base_list_item));
                        setListListener(listDevicesUsage);
                    }
                    break;
                default:
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            lookDeviceActivity = (LookDevice) context;
        }else {
            lookDeviceActivity = (LookDevice) getActivity();
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle();
        list_gis = (ListView) view.findViewById(R.id.list_gis);
        String data = lookDeviceActivity.read("schoolList");
        if (data != null && !data.equals("")) {
            listSchool = mParseJson.SchoolPoint(data);
            HashMap<String,String> hashMap;
            String devicesusageUrl;
//            if(listSchool!=null) {
//                list_gis.setAdapter(new SchoolListAdapter(lookDeviceActivity,
//                        listSchool, R.layout.base_list_item));
//                setListListener(listSchool);
//            }
//        }else {
//            OkHttp okHttp = new OkHttp(lookDeviceActivity, mHandler);
//            RequestBody requestBody = new FormBody.Builder().add("type", "1").build();
//            okHttp.postDataFromInternet(applyUrl, requestBody);
//        }
           listDevicesUsage.clear();
           if (listSchool != null) {
               for (int i = 0;i < listSchool.size();i++) {
                   String schoolid = listSchool.get(i).getId();
                   hashMap = AddingUrl.createHashMap1("schoolid",schoolid);
                   devicesusageUrl = AddingUrl.getUrl(applyUrl,hashMap);
                   OkHttp okHttp = new OkHttp(lookDeviceActivity,mHandler);
                   okHttp.getDataFromInternet(devicesusageUrl);
                   System.out.println("devicesusageUrl is " + devicesusageUrl);
               }
           }
        }
    }

//    public void setListListener(final List<School> listSchool){
//        list_gis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                String schoolid=listSchool.get(position).getId();
//                Bundle bundle = new Bundle();
//                bundle.putString("schoolid",schoolid);
//                BuildingListFg buildingListFg=new BuildingListFg();
//                buildingListFg.setArguments(bundle);
//                FragmentManager fragmentManager = lookDeviceActivity.getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.add(R.id.fragment_list, buildingListFg);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//    }
    public void setListListener(final List<DevicesUsageLists> listDevicesUsage) {
        list_gis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int position,long id) {
                String schoolid = listDevicesUsage.get(position).getSchoolid();
                Bundle bundle = new Bundle();
                bundle.putString("schoolid",schoolid);
                BuildingListFg buildingListFg = new BuildingListFg();
                buildingListFg.setArguments(bundle);
                FragmentManager fragmentManager = lookDeviceActivity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_list, buildingListFg);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

//    public void getDataFromInternet(final String path) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    SharedPreferences pref = lookDeviceActivity.getSharedPreferences("mycookie",Context.MODE_PRIVATE);
//                    String sessionid = pref.getString("sessionid","");
//                    //用post提交键值对格式的数据
//                    Request request = new Request.Builder()
//                            .addHeader("cookie",sessionid)
//                            .url(path)
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    String responseData = response.body().string();
//                    listDevicesUsage = mParseJson.DevicesUsagePoint(responseData);
//                    System.out.println(listDevicesUsage.size());
//                    System.out.println(listSchool.size());
//                    if (response.isSuccessful() && listDevicesUsage.size() == listSchool.size()) {
//                        Message msg = new Message();
//                        msg.what = GETOK;
//                        msg.obj = responseData;
//                        mHandler.sendMessage(msg);
//                        System.out.println("Connected");
//                    } else {
//                        //TODO 错误报告
//                        Message msg = new Message();
//                        msg.what = WRANG;
//                        mHandler.sendMessage(msg);
//                        System.out.println("Not response");
//                    }
//                } catch (IOException e) {
//                    Message msg = new Message();
//                    msg.what = EXCEPTION;
//                    mHandler.sendMessage(msg);
//                    e.printStackTrace();
//                    System.out.println("Error");
//                }
//            }
//        }).start();
//    }

//    public List<DevicesUsageLists> DevicesUsagePoint(String json) {
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            JSONObject devicesUsageObject = jsonObject.getJSONObject("device_usage");
//
//            String schoolid = devicesUsageObject.getString("schoolid");
//            String schoolname = devicesUsageObject.getString("schoolname");
//            int total_device = devicesUsageObject.getInt("total_device");
//            int using_device = devicesUsageObject.getInt("using_device");
//            int rate;
//            if (total_device != 0) {
//                double Rate = new BigDecimal((float)using_device/total_device).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//                rate = (int) (Rate * 100);
//                System.out.println("Rate is: " + Rate);
//                System.out.println("usingdevice is " + using_device);
//                System.out.println("totaldevice is " + total_device);
//            } else {
//                rate = 0;
//            }
//            listDevicesUsage.add(new DevicesUsageLists(schoolid,schoolname,total_device,using_device,rate));
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listDevicesUsage;
//    }
    private void setTitle() {
        textView1 = (TextView) lookDeviceActivity.findViewById(R.id.text1);
        textView2 = (TextView) lookDeviceActivity.findViewById(R.id.text2);
        textView3 = (TextView) lookDeviceActivity.findViewById(R.id.text3);
        textView4 = (TextView) lookDeviceActivity.findViewById(R.id.text4);
        textView1.setText("名称");
        textView2.setText("设备总数");
        textView3.setText("正在使用");
        textView4.setText("使用率");
}

}
