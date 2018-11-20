package com.pengllrn.tegm.Aoao;

import android.content.Context;

import com.pengllrn.tegm.R;
import com.pengllrn.tegm.base.ListViewAdapter;
import com.pengllrn.tegm.base.ViewHolder;

import java.util.List;

/**
 * Created by Aoao Bot on 2018/11/20.
 */

public class DevicesUsageListsAdapter extends ListViewAdapter<DevicesUsageLists> {
    public DevicesUsageListsAdapter(Context context, List<DevicesUsageLists> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, DevicesUsageLists devicesUsageLists) {
        holder.setText(R.id.item1,devicesUsageLists.getSchoolname());
        holder.setText(R.id.item2,String.valueOf(devicesUsageLists.getTotal_device()));
        holder.setText(R.id.item3,String.valueOf(devicesUsageLists.getUsing_devicce()));
        holder.setText(R.id.item4,String.valueOf(devicesUsageLists.getRate())+"%");
    }
}

