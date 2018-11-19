package com.pengllrn.tegm.adapter;

import android.content.Context;

import com.pengllrn.tegm.Aoao.Devices;
import com.pengllrn.tegm.Aoao.Rooms;
import com.pengllrn.tegm.R;
import com.pengllrn.tegm.bean.Device;
import com.pengllrn.tegm.utils.ImageLoader;
import com.pengllrn.tegm.base.ListViewAdapter;
import com.pengllrn.tegm.base.ViewHolder;

import java.util.List;

public class DeviceAdapter extends ListViewAdapter<Devices> {
    private ImageLoader mImageLoader;
    public DeviceAdapter(Context context, List<Devices> datas, int layoutId) {
        super(context, datas, layoutId);
//        mImageLoader = new ImageLoader(context);
    }

    @Override
    public void convert(ViewHolder holder,Devices devices) {
            holder.setText(R.id.tv_devicename, devices.getDevicekind());
            holder.setText(R.id.tv_useflag,"[" + devices.getUseflag() + "]");
            if(devices.getUseflag()){
                holder.setTextColor(R.id.tv_useflag,"#18d60a");
                holder.setTextColor(R.id.tv_devicename, "#50dcef");
            }else {
                holder.setTextColor(R.id.tv_useflag,"#a39f9f");
                holder.setTextColor(R.id.tv_devicename, "#a39f9f");
            }
            holder.setText(R.id.tv_roomname,"学校"+devices.getSchoolname()+"  序号 "+devices.getOrderNum());
            holder.setText(R.id.tv_devicenum,"编号 "+devices.getDeviceNum());
//            holder.setImageURI(R.id.img_pic,device.getImgUrl(),mImageLoader);
    }
}
