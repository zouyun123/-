package com.pengllrn.tegm.Aoao;

import android.content.Context;

import com.pengllrn.tegm.R;
import com.pengllrn.tegm.base.ListViewAdapter;
import com.pengllrn.tegm.base.ViewHolder;

import java.util.List;

/**
 * Created by Aoao Bot on 2018/11/20.
 */

public class DevicesInRoomAdapter extends ListViewAdapter<DevicesInRoom> {

    public DevicesInRoomAdapter(Context context, List<DevicesInRoom> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, DevicesInRoom devicesInRoom) {
        holder.setText(R.id.item1,devicesInRoom.getDeviceNum());
        holder.setText(R.id.item2,devicesInRoom.getTypename());
        if(devicesInRoom.isUseFlag()){
            holder.setText(R.id.item3, "使用中");
            holder.setTextColor(R.id.item3,"#32CD32");
        }else{
            holder.setText(R.id.item3, "未使用");
            holder.setTextColor(R.id.item3,"#787878");
        }

        holder.setText(R.id.item4, devicesInRoom.getDevicekind());
    }

}
