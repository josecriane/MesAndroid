package com.josecriane.mes.mesandroid.commands;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.josecriane.mes.mesandroid.models.Device;
import com.josecriane.mes.mesandroid.utils.ICallback;
import com.josecriane.mes.mesandroid.utils.VolleyHelper;

import org.json.JSONObject;

/**
 * Created by sito on 4/11/15.
 */
public class SetupCommand extends Command {

    private String mUrl;

    public SetupCommand(Context context, Device device){
        mContext = context.getApplicationContext();
        mUrl = VolleyHelper.BASE_URL + "devices/" + String.valueOf(device.getId());
        TAG = "SetupCommand";
    }

    @Override
    public void execute() {
        JsonRequest request = new JsonObjectRequest(Request.Method.PATCH, mUrl, ""
        , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("SetupCommandResponse", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SetupCommandError", error.toString());
            }
        });
        VolleyHelper.getInstance(mContext).addRequest(request, TAG);
    }
}
