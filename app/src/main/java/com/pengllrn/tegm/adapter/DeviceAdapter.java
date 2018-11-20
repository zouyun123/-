package com.pengllrn.tegm.adapter;

import android.content.Context;

import com.pengllrn.tegm.R;
import com.pengllrn.tegm.bean.Device;
import com.pengllrn.tegm.utils.ImageLoader;
import com.pengllrn.tegm.base.ListViewAdapter;
import com.pengllrn.tegm.base.ViewHolder;

import java.util.List;
/*
* 功能已廢除
*/
public class DeviceAdapter extends ListViewAdapter<Device> {
    private ImageLoader mImageLoader;
    public DeviceAdapter(Context context, List<Device> datas, int layoutId) {
        super(context, datas, layoutId);
//        mImageLoader = new ImageLoader(context);
    }

    @Override
    public void convert(ViewHolder holder,Device device) {
            holder.setText(R.id.tv_devicename, device.getDeviceType());
            holder.setText(R.id.tv_useflag,"[" + device.getUseFlag() + "]");
            if(device.getUseFlag().equals("正在使用")){
                holder.setTextColor(R.id.tv_useflag,"#18d60a");
                holder.setTextColor(R.id.tv_devicename, "#50dcef");
            }else {
                holder.setTextColor(R.id.tv_useflag,"#a39f9f");
                holder.setTextColor(R.id.tv_devicename, "#a39f9f");
            }
            holder.setText(R.id.tv_roomname,"房间 "+device.getBuildName()+" "+device.getRoomName()+"  序号 "+device.getOrderNum());
            holder.setText(R.id.tv_devicenum,"编号 "+device.getDeviceNum());
//            holder.setImageURI(R.id.img_pic,device.getImgUrl(),mImageLoader);
    }
}
