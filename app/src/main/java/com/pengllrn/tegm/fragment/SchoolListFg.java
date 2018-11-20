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

import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

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

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 0x2020:
                    String responseData = (msg.obj).toString();
//                    List<School> listSchool = mParseJson.Json2Gis(responseData).getSchoolLists();
//                    if(listSchool!=null) {
//                        list_gis.setAdapter(new SchoolListAdapter(lookDeviceActivity,
//                                listSchool, R.layout.base_list_item));
//                        setListListener(listSchool);
//                    }
                    List<DevicesUsageLists> listDevicesUsage = mParseJson.DevicesUsagePoint(responseData);
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
        list_gis = (ListView) view.findViewById(R.id.list_gis);
        String data = lookDeviceActivity.read("schoolList");
        if (data != null && !data.equals("")) {
            List<School> listSchool = mParseJson.SchoolPoint(data);
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
           if (listSchool != null) {
               for (int i = 0;i < listSchool.size();i++) {
                   String schoolid = listSchool.get(i).getId();
                   OkHttp okHttp = new OkHttp(lookDeviceActivity, mHandler);
                   hashMap = AddingUrl.createHashMap1("schoolid",schoolid);
                   devicesusageUrl = AddingUrl.getUrl(applyUrl,hashMap);
                   okHttp.getDataFromInternet(devicesusageUrl);
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
                transaction.add(R.id.fragment_list, buildingListFg);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
