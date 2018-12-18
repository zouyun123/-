package com.pengllrn.tegm.activity;

/**
* 功能已經廢除
*/
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.pengllrn.tegm.R;
import com.pengllrn.tegm.adapter.DeviceAdapter;
import com.pengllrn.tegm.bean.Device;
import com.pengllrn.tegm.constant.Constant;
import com.pengllrn.tegm.gson.ParseJson;
import com.pengllrn.tegm.internet.OkHttp;
import com.pengllrn.tegm.utils.SharedHelper;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class SearchActivity extends AppCompatActivity {

    private EditText input_search;
    private TextView cancle;
    private ListView search_list;
    private TextView information;

    private String input;
    private String applyUrl = Constant.URL_SEARCH;

    private ParseJson mParseJson = new ParseJson();
    private SharedHelper sharedHelper;

    private boolean flag = true;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 0x2017:
                    String responseData = (msg.obj).toString();
                    final List<Device> listDevice = mParseJson.JsonToAll(responseData).getDevice();
                    if(listDevice != null){
//                        search_list.setAdapter(new DeviceAdapter(getApplicationContext(),listDevice,
//                                R.layout.item_layout));
                        search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), DevDetailActivity.class);
                                intent.putExtra("device_id", listDevice.get(position).getDeviceId());
                                startActivity(intent);
                            }
                        });
                        flag = true;
                    }
                    information.setText("");
                    break;
                default:
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        input_search = (EditText) findViewById(R.id.input_search);
        cancle = (TextView) findViewById(R.id.cancel_search);
        search_list = (ListView) findViewById(R.id.search_list);
        information = (TextView) findViewById(R.id.infomation);

        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedHelper = new SharedHelper(getApplicationContext());
    }

    private void setListener() {
        input_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                input = input_search.getText().toString().trim();
                if(!input.equals("") && flag){
                    String schoolid = sharedHelper.readbykey("school");
                    OkHttp okHttp = new OkHttp(getApplicationContext(), mHandler);
                    RequestBody requestBody = new FormBody.Builder().add("schoolid",schoolid).add("condition", input).build();
                    okHttp.postDataFromInternet(applyUrl, requestBody);
                    information.setText("正在搜索 请稍等...");
                    flag = false;
                }
                return false;
            }
        });
        cancle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
