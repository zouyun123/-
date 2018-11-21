package com.pengllrn.tegm.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.pengllrn.tegm.R;
import com.pengllrn.tegm.adapter.MyApplyListAdapter;
import com.pengllrn.tegm.bean.ApplyCenterBean;
import com.pengllrn.tegm.activity.ApplyCenter;
import com.pengllrn.tegm.constant.Constant;
import com.pengllrn.tegm.gson.ParseJson;
import com.pengllrn.tegm.internet.OkHttp;
import com.pengllrn.tegm.utils.SharedHelper;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by pengl on 2018/1/21.
 */

public class My_ApplyFg extends Fragment {

    private String applyUrl = Constant.URL_DAMAGE_APPLICATION;
    private ApplyCenter applyCenter;
    private ListView my_apply_list;
    private ParseJson parseJson = new ParseJson();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 0x2017:
                    String responseData = (msg.obj).toString();
                    List<ApplyCenterBean> applyList = parseJson.ApplyList(responseData);
                    if(applyList.size()>0) {
                        my_apply_list.setAdapter(new MyApplyListAdapter(applyCenter, applyList, R.layout.item_apply_list));
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
        View view = inflater.inflate(R.layout.fragment_my_apply, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        my_apply_list = (ListView) view.findViewById(R.id.my_apply_list);

        SharedHelper sharedHelper = new SharedHelper(applyCenter);
        String userid = sharedHelper.readbykey("userid");
        if(!userid.equals("")){
            OkHttp okHttp = new OkHttp(applyCenter, mHandler);
            RequestBody requestBody = new FormBody.Builder().add("userid", userid).add("type","1").build();
            okHttp.postDataFromInternet(applyUrl, requestBody);
        }
    }
}
