package com.josecriane.mes.mesandroid.commands;

import android.content.Context;
import android.os.Build;
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
import com.josecriane.mes.mesandroid.utils.PersistenceManager;
import com.josecriane.mes.mesandroid.utils.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sito on 4/11/15.
 */
public class DeviceSetupCommand extends Command {

    private Device mDevice;

    public DeviceSetupCommand(Context context, Device device){
        mContext = context.getApplicationContext();
        mDevice = device;
        mDevice.setConfigured(true);
        mUrl = device.getDeviceUrl() + "/setup/";
        TAG = "DeviceSetupCommand";
        mMethod = Request.Method.PATCH;

        Gson gson = new Gson();
        mJson = new JSONObject();
        try {
            mJson = new JSONObject(gson.toJson(mDevice));
        } catch (JSONException e) {
            Log.e("JSON", e.toString());
        }
    }


    @Override
    public void onSuccess(JSONObject response) {
        DeviceTokenCommand command = new DeviceTokenCommand(mContext, mDevice);
        command.execute();
    }

    @Override
    public void onError(VolleyError error) {

    }

    @Override
    public Map<String, String> getCommandHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("X-TOKEN", Build.SERIAL);

        return headers;
    }
}
