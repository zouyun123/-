package com.pengllrn.tegm.Aoao;

import android.content.Context;

import com.pengllrn.tegm.R;
import com.pengllrn.tegm.base.ListViewAdapter;
import com.pengllrn.tegm.base.ViewHolder;
import com.pengllrn.tegm.bean.Room;

import java.util.List;

/**
 * Created by Aoao Bot on 2018/11/20.
 */

public class RoomListsAdapter extends ListViewAdapter<Room> {

    public RoomListsAdapter(Context context, List<Room> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Room room) {
        holder.setText(R.id.item1,room.getBuildingname());
        holder.setText(R.id.item2,room.getRoomname());
        holder.setText(R.id.item3,room.getRoomid());
        holder.setText(R.id.item4,"--");
    }
}
