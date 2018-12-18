package com.pengllrn.tegm.adapter;

import android.content.Context;

import com.pengllrn.tegm.Aoao.AlarmDevice;
import com.pengllrn.tegm.R;
import com.pengllrn.tegm.base.ListViewAdapter;
import com.pengllrn.tegm.base.ViewHolder;

import java.util.List;

/**
 * Author：Pengllrn
 * Date: 2018/6/12
 * Contact 897198177@qq.com
 * https://github.com/pengllrn
 */

public class AlarmAdaper extends ListViewAdapter<AlarmDevice> {
    public AlarmAdaper(Context context, List<AlarmDevice> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, AlarmDevice alarmDevice) {
        holder.setText(R.id.dev_order,String.valueOf(alarmDevice.getOrdernum()));
        holder.setText(R.id.tv_alarm,alarmDevice.getAlarmtype());
        holder.setText(R.id.tv_room,alarmDevice.getRoomid());
        holder.setText(R.id.tv_name,alarmDevice.getAlarmtype());
        holder.setText(R.id.last_update_time,String.valueOf(alarmDevice.getAlarmstart()));
        holder.setText(R.id.tv_schoolname,alarmDevice.getSchoolid());
        holder.setText(R.id.tv_num,alarmDevice.getDevicenum());
        holder.setItemListener(R.id.cb_more,R.id.lv_detail);
    }
}
