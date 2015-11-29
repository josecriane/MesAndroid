package com.josecriane.mes.mesandroid.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by sito on 3/11/15.
 */
public class VolleyHelper {

    public static String BASE_URL="http://192.168.0.27:8000/";
    private static VolleyHelper instance;
    private RequestQueue mQueue;

    private VolleyHelper(Context context){
        mQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static VolleyHelper getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyHelper(context);
        }
        return instance;
    }

    public void addRequest(Request request, String TAG) {
        request.setTag(TAG);
        mQueue.add(request);
    }

    public void cancelRequest(String TAG) {
        mQueue.cancelAll(TAG);
    }
}
