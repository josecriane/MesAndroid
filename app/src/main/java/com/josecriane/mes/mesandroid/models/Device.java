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

    private Integer id;
    private Date createdDate;
    private String name;
    private String phone;
    private String uid;
    private Integer administrator;
    private Boolean configured;
    private List commands;
    private String token;
    private String tokenGCM;

    private transient String mDeviceUrl = VolleyHelper.BASE_URL+"devices/";

    public Device(int id, String uid, String tokenGCM){
        this.id = id;
        this.uid = uid;
        this.tokenGCM = tokenGCM;
    }

    public Integer getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUid() {
        return uid;
    }

    public Integer getAdministrator() {
        return administrator;
    }

    public Boolean isConfigured() {
        return configured;
    }

    public List getCommands() {
        return commands;
    }

    public String getToken() {
        return token;
    }

    public String getTokenGCM() {
        return tokenGCM;
    }

    public String getDeviceUrl() {
        return mDeviceUrl + String.valueOf(this.id);
    }

    public void setConfigured(Boolean configured) { this.configured = configured; }
}
