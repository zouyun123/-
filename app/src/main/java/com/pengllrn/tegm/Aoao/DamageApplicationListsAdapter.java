package com.pengllrn.tegm.Aoao;

import android.content.Context;

import com.pengllrn.tegm.R;
import com.pengllrn.tegm.base.ListViewAdapter;
import com.pengllrn.tegm.base.ViewHolder;

import java.util.List;

/**
 * Created by Aoao Bot on 2018/11/21.
 */


public class DamageApplicationListsAdapter extends ListViewAdapter<DamageApplicationLists> {

    public DamageApplicationListsAdapter(Context context, List<DamageApplicationLists> datas, int layoutId) {
            super(context, datas, layoutId);
    }

        @Override
        public void convert(ViewHolder holder, DamageApplicationLists damageApplicationLists) {
            holder.setText(R.id.item1,damageApplicationLists.getType());
            holder.setText(R.id.item2,damageApplicationLists.getDevicenum());
            holder.setText(R.id.item3,damageApplicationLists.getDatetime());
            holder.setText(R.id.item4,String.valueOf(damageApplicationLists.getDeal_status()));
        }
    }

