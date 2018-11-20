package com.pengllrn.tegm.Aoao;

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
import android.widget.TextView;

import com.pengllrn.tegm.R;
import com.pengllrn.tegm.activity.DeviceInRoom;
import com.pengllrn.tegm.activity.LookDevice;
import com.pengllrn.tegm.constant.Constant;
import com.pengllrn.tegm.gson.ParseJson;
import com.pengllrn.tegm.internet.OkHttp;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Aoao Bot on 2018/11/20.
 */

public class DeviceInRoomFg extends Fragment {

    private String applyUrl = Constant.URL_DEVICES_IN_ROOM;
    private ParseJson mParseJson = new ParseJson();
    private LookDevice lookDevice;
    private ListView list_gis;
    private String roomid;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x2020:
                    String responseData = (msg.obj).toString();
                    final List<DevicesInRoom> listDeviceInRoom = mParseJson.DevicesInRoomPoint(responseData);
                    if (listDeviceInRoom != null) {
                        list_gis.setAdapter(new DevicesInRoomAdapter(lookDevice, listDeviceInRoom, R.layout.base_list_item));
                        list_gis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                            }
                        });
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
            lookDevice = (LookDevice) context;
        }
        lookDevice = (LookDevice) getActivity();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle();
        String getdevicesinroomUrl;
        HashMap<String,String> hashMap;
        list_gis = (ListView) view.findViewById(R.id.list_gis);
        roomid = getArguments().getString("roomid");
        OkHttp okHttp = new OkHttp(lookDevice, mHandler);
        hashMap = AddingUrl.createHashMap1("roomid",roomid);
        getdevicesinroomUrl = AddingUrl.getUrl(applyUrl,hashMap);
        okHttp.getDataFromInternet(getdevicesinroomUrl);
    }

    public void setTitle() {
        textView1 = (TextView) lookDevice.findViewById(R.id.text1);
        textView2 = (TextView) lookDevice.findViewById(R.id.text2);
        textView3 = (TextView) lookDevice.findViewById(R.id.text3);
        textView4 = (TextView) lookDevice.findViewById(R.id.text4);
        textView1.setText("设备编号");
        textView2.setText("设备种类");
        textView3.setText("是否使用");
        textView4.setText("所属种类");
    }


}
