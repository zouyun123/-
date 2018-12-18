package com.pengllrn.tegm.internet;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;


/**
 * @author Administrator
 * @version $Rev$
 * @des ${UTODO}
 * @updateAuthor ${Author}$
 * @updateDate2017/10/5.
 */

public class OkHttp {
    public final int POSTOK = 0x2017;
    public final int GETOK = 0x2020;
    public final int GETIMGOK = 0x2030;
    public final int WRANG = 0x22;
    public final int EXCEPTION = 0x30;
    private final int SUC = 0x40;
    private Handler handler;
    private Context mContext;

    public OkHttp(Context context, Handler handler) {
        this.mContext = context;
        this.handler = handler;
    }



    public void postDataWithCookie(final String path, final RequestBody requestBody) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    client = new OkHttpClient.Builder().cookieJar(new CookieJar() {
                        private final HashMap<String,List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();
                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                            cookieStore.put(url.host(), cookies);
                            SharedPreferences.Editor editor = mContext.getSharedPreferences("mycookie",Context.MODE_PRIVATE).edit();
                            editor.putString("sessionid",String.valueOf(cookieStore.get(url.host()).get(0)));
                            editor.apply();
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            List<Cookie> cookies = cookieStore.get(url.host());
                            return cookies != null ? cookies : new ArrayList<Cookie>();
                        }
                    }).build();
                    Request request = new Request.Builder()
                            .url(path)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        Message msg = new Message();
                        msg.what = POSTOK;
                        msg.obj = responseData;
                        handler.sendMessage(msg);
                        System.out.println("Connected");
                    } else {
                        Message msg = new Message();
                        msg.what = WRANG;
                        handler.sendMessage(msg);
                        System.out.println("Not response");
                    }
                }catch (IOException e) {
                    Message msg = new Message();
                    msg.what = EXCEPTION;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                    System.out.println("Error");
                }
            }
        }).start();
    }

    public void postDataFromInternet(final String path, final RequestBody requestBody) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    //用post提交键值对格式的数据
                    SharedPreferences pref = mContext.getSharedPreferences("mycookie",Context.MODE_PRIVATE);
                    String sessionid = pref.getString("sessionid","");
                    Request request = new Request.Builder()
                            .addHeader("cookie",sessionid)
                            .url(path)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        Message msg = new Message();
                        msg.what = POSTOK;
                        msg.obj = responseData;
                        handler.sendMessage(msg);
                        System.out.println("Connected");
                    } else {
                        //TODO 错误报告
                        Message msg = new Message();
                        msg.what = WRANG;
                        handler.sendMessage(msg);
                        System.out.println("Not response");
                    }
                } catch (IOException e) {
                    Message msg = new Message();
                    msg.what = EXCEPTION;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                    System.out.println("Error");
                }
            }
        }).start();
    }

    public void postData2Internet(final String path, final RequestBody requestBody) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    //用post提交键值对格式的数据
                    SharedPreferences pref = mContext.getSharedPreferences("mycookie",Context.MODE_PRIVATE);
                    String sessionid = pref.getString("sessionid","");
                    Request request = new Request.Builder()
                            .addHeader("cookie",sessionid)
                            .url(path)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Message msg = new Message();
                        msg.obj = response.body().string();
                        msg.what = SUC;
                        handler.sendMessage(msg);
                    } else {
                        //TODO 错误报告
                        Message msg = new Message();
                        msg.what = WRANG;
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    Message msg = new Message();
                    msg.what = EXCEPTION;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getDataFromInternet(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    SharedPreferences pref = mContext.getSharedPreferences("mycookie",Context.MODE_PRIVATE);
                    String sessionid = pref.getString("sessionid","");
                    //用post提交键值对格式的数据
                    Request request = new Request.Builder()
                            .addHeader("cookie",sessionid)
                            .url(path)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        Message msg = new Message();
                        msg.what = GETOK;
                        msg.obj = responseData;
                        handler.sendMessage(msg);
                        System.out.println("Connected");
                    } else {
                        //TODO 错误报告
                        Message msg = new Message();
                        msg.what = WRANG;
                        handler.sendMessage(msg);
                        System.out.println("Not response");
                    }
                } catch (IOException e) {
                    Message msg = new Message();
                    msg.what = EXCEPTION;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                    System.out.println("Error");
                }
            }
        }).start();
    }

    public void getFromInternet(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    //用post提交键值对格式的数据
                    Request request = new Request.Builder()
                            .url(path)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        Message msg = new Message();
                        msg.what = GETOK;
                        msg.obj = responseData;
                        handler.sendMessage(msg);
                        System.out.println("Connected");
                    } else {
                        //TODO 错误报告
                        Message msg = new Message();
                        msg.what = WRANG;
                        handler.sendMessage(msg);
                        System.out.println("Not response");
                    }
                } catch (IOException e) {
                    Message msg = new Message();
                    msg.what = EXCEPTION;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                    System.out.println("Error");
                }
            }
        }).start();
    }

    public void getImageFromInternet(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    SharedPreferences pref = mContext.getSharedPreferences("mycookie",Context.MODE_PRIVATE);
                    String sessionid = pref.getString("sessionid","");
                    //用post提交键值对格式的数据
                    Request request = new Request.Builder()
                            .addHeader("cookie",sessionid)
                            .url(path)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
//                        InputStream is = null;
//                        try {
//                            is = response.body().byteStream();
//                            Bitmap bitmap = BitmapFactory.decodeStream(is);
//                            Message msg = new Message();
//                            msg.what = GETIMGOK;
//                            msg.obj = bitmap;
//                            handler.sendMessage(msg);
//                            System.out.println("Successfully get images");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        } finally {
//                            try {
//                                if (is != null) {
//                                    is.close();
//                                }
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
                        byte[] bt = response.body().bytes();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bt,0,bt.length);
                        Message msg = new Message();
                        msg.what = GETIMGOK;
                        msg.obj = bitmap;
                        handler.sendMessage(msg);
                        System.out.println("Successfully get images");
                    } else {
                        //TODO 错误报告
                        Message msg = new Message();
                        msg.what = WRANG;
                        handler.sendMessage(msg);
                        System.out.println("Not response");
                    }
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = EXCEPTION;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                    System.out.println("Exception");
                }
            }
        }).start();
    }

    public void uploadMultiFile(final String requesturl,final String fileName,final String deviceid,
                                final String apperid,final String appername,final String damagedepict,
                                final  String datetime,final File ...files) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //File file = new File(fileDir);
                //application/octet-stream
                //RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//                RequestBody requestBody = new MultipartBody.Builder()
//                        .setType(MultipartBody.FORM)
//                        .addFormDataPart("image", fileName, fileBody)
//                        .build();
                MultipartBody.Builder builder = new MultipartBody.Builder();
                for(int i=0;i<files.length;i++){
                    RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), files[i]);
                    builder.addFormDataPart("image"+i,fileName+"_"+i+".jpg",fileBody1);
                }
                RequestBody requestBody=builder
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("deviceid",deviceid)
                        .addFormDataPart("applierid",apperid)
                        .addFormDataPart("appliername",appername)
                        .addFormDataPart("damagedepict",damagedepict)
                        .addFormDataPart("datetime",datetime)
                        .build();
                Request request = new Request.Builder()
                        .url(requesturl)
                        .post(requestBody)
                        .build();

                final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
                OkHttpClient okHttpClient  = httpBuilder
                        //设置超时
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "uploadMultiFile() e=" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.i(TAG, "uploadMultiFile() response=" + responseData);
                        Message msg = new Message();
                        msg.what = 0x2018;
                        msg.obj = responseData;
                        handler.sendMessage(msg);
                    }
                });
            }
        }).start();
    }
}
