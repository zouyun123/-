package com.pengllrn.tegm.Aoao;

import android.content.Context;

import com.pengllrn.tegm.R;
import com.pengllrn.tegm.base.ListViewAdapter;
import com.pengllrn.tegm.base.ViewHolder;

import java.util.List;

/**
 * Created by Aoao Bot on 2018/11/27.
 */

public class DamageApplicationDetailListsAdapter extends ListViewAdapter<DamageApplicationLists> {

    public DamageApplicationDetailListsAdapter(Context context, List<DamageApplicationLists> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, DamageApplicationLists damageApplicationLists) {
        holder.setText(R.id.item1,damageApplicationLists.getDeviceid());
        holder.setText(R.id.item2,damageApplicationLists.getName());
//        holder.setText(R.id.item3,"--");
//        holder.setText(R.id.item4,"--");
    }
}
