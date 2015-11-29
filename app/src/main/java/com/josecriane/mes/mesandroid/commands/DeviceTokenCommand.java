package com.josecriane.mes.mesandroid.commands;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.josecriane.mes.mesandroid.models.Device;
import com.josecriane.mes.mesandroid.utils.PersistenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sito on 4/11/15.
 */
public class DeviceTokenCommand extends Command {

    private Device mDevice;

    public DeviceTokenCommand(Context context, Device device){
        mContext = context.getApplicationContext();
        mDevice = device;
        mUrl = device.getDeviceUrl() + "/token/";
        TAG = "tokenCommand";
        mMethod = Request.Method.GET;

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
        try {
            PersistenceManager.getInstance().storeString(PersistenceManager.TOKEN, response.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
