package com.pengllrn.tegm.bean;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${UTODO}
 * @updateAuthor ${Author}$
 * @updateDate2017/9/27.
 */

public class Room {
    private String buildingname = "";
    private String roomname = "";
    private String roomid;

    public Room(String buildingname, String roomname,String roomid) {
        this.buildingname = buildingname;
        this.roomname = roomname;
        this.roomid = roomid;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public String getRoomname() {
        return roomname;
    }

    public String getRoomid() {
        return roomid;
    }
}
