package com.pengllrn.tegm.bean;

/**
 * Created by pengl on 2018/1/28.
 */

public class Statistics {
    private String typename;
    private float value;

    public Statistics(String typename, float value){
        this.typename = typename;
        this.value = value;
    }

    public String getType() {
        return typename;
    }

    public float getValue() {
        return value;
    }
}
