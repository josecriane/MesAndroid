package com.josecriane.mes.mesandroid.models;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.josecriane.mes.mesandroid.utils.ICallback;
import com.josecriane.mes.mesandroid.utils.VolleyHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by sito on 1/11/15.
 */
public class Device {

    private int mId;
    private Date mCreatedDate;
    private String mName;
    private String mPhone;
    private String mUid;
    private int mAdministrator;
    private boolean mConfigured;
    private List mCommands;
    private String mToken;
    private String mTokenGCM;

    private String mDeviceUrl = VolleyHelper.BASE_URL+"device/";

    public Device(int id, String uid, String tokenGCM){
        mId = id;
        mUid = uid;
        mTokenGCM = tokenGCM;
    }

    public int getId() {
        return mId;
    }

    public Date getCreatedDate() {
        return mCreatedDate;
    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getUid() {
        return mUid;
    }

    public int getAdministrator() {
        return mAdministrator;
    }

    public boolean isConfigured() {
        return mConfigured;
    }

    public List getCommands() {
        return mCommands;
    }

    public String getToken() {
        return mToken;
    }

    public String getTokenGCM() {
        return mTokenGCM;
    }

    public String getDeviceUrl() {
        return mDeviceUrl;
    }
}
