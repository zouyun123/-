package com.pengllrn.tegm.Aoao;

import android.content.Context;

import com.pengllrn.tegm.R;
import com.pengllrn.tegm.base.ListViewAdapter;
import com.pengllrn.tegm.base.ViewHolder;

import java.util.List;

/**
 * Created by Aoao Bot on 2018/11/27.
 */

public class DamageApplicationDetailListsAdapter extends ListViewAdapter<DamageApplicationDetailLists> {

    public DamageApplicationDetailListsAdapter(Context context, List<DamageApplicationDetailLists> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, DamageApplicationDetailLists damageApplicationDetailLists) {
        holder.setText(R.id.item1,damageApplicationDetailLists.getDeviceid());
        holder.setText(R.id.item2,damageApplicationDetailLists.getDamagedepict());
        holder.setText(R.id.item3,damageApplicationDetailLists.getApplier());
        holder.setText(R.id.item4,String.valueOf(damageApplicationDetailLists.getAppliertel()));
    }
}
