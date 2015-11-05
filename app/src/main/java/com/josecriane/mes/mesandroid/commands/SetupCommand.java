package com.josecriane.mes.mesandroid.commands;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.josecriane.mes.mesandroid.models.Device;
import com.josecriane.mes.mesandroid.utils.ICallback;
import com.josecriane.mes.mesandroid.utils.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sito on 4/11/15.
 */
public class SetupCommand extends Command {

    private String mUrl;
    private Device mDevice;

    public SetupCommand(Context context, Device device){
        mContext = context.getApplicationContext();
        mDevice = device;
        mDevice.setConfigured(true);
        mUrl = device.getDeviceUrl() + "/setup/";
        TAG = "SetupCommand";
    }

    @Override
    public void execute() {
        Gson gson = new Gson();

        JSONObject json = new JSONObject();
        try {
            json = new JSONObject(gson.toJson(mDevice));
        } catch (JSONException e) {
            Log.e("JSON", e.toString());
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PATCH, mUrl, json
        , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("SetupCommandResponse", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SetupCommandError", new String(error.networkResponse.data));
            }
        });
        VolleyHelper.getInstance(mContext).addRequest(request, TAG);
    }
}
