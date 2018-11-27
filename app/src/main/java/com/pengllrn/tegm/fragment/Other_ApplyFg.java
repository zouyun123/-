package com.pengllrn.tegm.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pengllrn.tegm.Aoao.AddingUrl;
import com.pengllrn.tegm.Aoao.DamageApplicationDetailLists;
import com.pengllrn.tegm.Aoao.DamageApplicationDetailListsAdapter;
import com.pengllrn.tegm.Aoao.DamageApplicationLists;
import com.pengllrn.tegm.R;
import com.pengllrn.tegm.activity.ApplyCenter;
import com.pengllrn.tegm.activity.LookDamageDevice;
import com.pengllrn.tegm.adapter.MyApplyListAdapter;
import com.pengllrn.tegm.adapter.OtherApplyListAdapter;
import com.pengllrn.tegm.bean.ApplyCenterBean;
import com.pengllrn.tegm.constant.Constant;
import com.pengllrn.tegm.gson.ParseJson;
import com.pengllrn.tegm.internet.OkHttp;
import com.pengllrn.tegm.utils.SharedHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by pengl on 2018/1/21.
 */

public class Other_ApplyFg extends Fragment {

    private String applyUrl = Constant.URL_DAMAGE_APPLICATION_DETAIL ;
    private ApplyCenter applyCenter;
    private ListView other_apply_list;
    private ParseJson mParseJson = new ParseJson();
    private List<DamageApplicationDetailLists> listDamageApplicationDetail = new ArrayList<DamageApplicationDetailLists>();
    private List<DamageApplicationLists> listDamageApplication;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 0x2020:
                    String responseData = (msg.obj).toString();
                    listDamageApplicationDetail = mParseJson.DamageApplicationDetailPoint(responseData);
                    if (listDamageApplicationDetail != null) {
                        other_apply_list.setAdapter(new DamageApplicationDetailListsAdapter(applyCenter,listDamageApplicationDetail,R.layout.item_apply_list));
                    }
//                    final List<ApplyCenterBean> applyList = mParseJson.ApplyList(responseData);
//                    if(applyList.size()>0) {
//                        other_apply_list.setAdapter(new OtherApplyListAdapter(applyCenter, applyList, R.layout.item_apply_list));
//                        other_apply_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                String deviceid = applyList.get(i).getDeviceid();
//                                Intent intent = new Intent(applyCenter, LookDamageDevice.class);
//                                intent.putExtra("deviceid",deviceid);
//                                startActivity(intent);
//                            }
//                        });
//                    }
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
            applyCenter = (ApplyCenter) context;
        }else
            applyCenter = (ApplyCenter) getActivity();

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_apply, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        other_apply_list = (ListView) view.findViewById(R.id.other_apply_list);
//        SharedHelper sharedHelper = new SharedHelper(applyCenter);
//        String school = sharedHelper.readbykey("school");
//        if(!school.equals("")){
//            OkHttp okHttp = new OkHttp(applyCenter, mHandler);
//            RequestBody requestBody = new FormBody.Builder().add("school", school).add("type","2").build();
//            okHttp.postDataFromInternet(applyUrl, requestBody);
//        }
        String data = applyCenter.read("applicationList");
        if (data != null && !data.equals("")) {
            listDamageApplication = mParseJson.DamageApplicationListsPoint(data);
            HashMap<String,String> hashMap;
            String damageapplicationdetailurl;
            if (listDamageApplication != null) {
                for (int i = 0;i < listDamageApplication.size();i++) {
                    String applicationid = String.valueOf(listDamageApplication.get(i).getApplicationid());
                    hashMap = AddingUrl.createHashMap1("applicationid",applicationid);
                    OkHttp okHttp = new OkHttp(applyCenter,mHandler);
                    damageapplicationdetailurl = AddingUrl.getUrl(applyUrl,hashMap);
                    okHttp.getDataFromInternet(damageapplicationdetailurl);
                    System.out.println("damageapplicationdetailUrl is " + damageapplicationdetailurl);
                }
            }
        }
    }
}
