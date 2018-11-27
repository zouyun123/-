package com.pengllrn.tegm.Aoao;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by Aoao Bot on 2018/11/14.
 */

public class AddingUrl {

    public static HashMap<String, String> createHashMap1(String key, String value) {
        HashMap hashmap = new HashMap();
        hashmap.put(key, value);
        return hashmap;
    }

    public static HashMap<String, String> createHashMap2(String key1, String value1, String key2, String value2) {
        HashMap hashmap = new HashMap();
        hashmap.put(key1, value1);
        hashmap.put(key2, value2);
        return hashmap;
    }

//    public static HashMap<String,Integer> createHashMap3(String key,int value) {
//        HashMap hashMap = new HashMap();
//        hashMap.put(key,value);
//        return hashMap;
//    }

    public static String getUrl(String actionUrl, HashMap<String, String> paramsMap) {
        StringBuilder myBuilder = new StringBuilder();
        String addedUrl = null;
        try {
            //處理參數
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    myBuilder.append("&");
                }
                //對參數進行URLEncoder
                myBuilder.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //補全請求地址
            String requestUrl = String.format("%s?%s", actionUrl, myBuilder.toString());
            addedUrl = requestUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addedUrl;
    }

}
