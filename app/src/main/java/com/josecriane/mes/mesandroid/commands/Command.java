package com.josecriane.mes.mesandroid.commands;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.josecriane.mes.mesandroid.utils.PersistenceManager;
import com.josecriane.mes.mesandroid.utils.VolleyHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sito on 4/11/15.
 */
public abstract class Command {

    public String TAG;
    public Context mContext;

    protected String mUrl;
    protected int mMethod;
    protected JSONObject mJson;

    public void execute() {
        Log.d("CommandUrl", mUrl);
        Log.d("CommandJson", mJson.toString());
        JsonObjectRequest request = new JsonObjectRequest(mMethod, mUrl, mJson
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("CommandResponse", response.toString());
                onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map headers = super.getHeaders();
                if (headers.isEmpty())
                    headers = new HashMap();
                headers.putAll(getCommandHeaders());

                return headers;
            }
        };
        VolleyHelper.getInstance(mContext).addRequest(request, TAG);
    }

    public void cancel(){
        VolleyHelper.getInstance(mContext).cancelRequest(TAG);
    }

    public abstract void onSuccess(JSONObject response);
    public abstract void onError(VolleyError error);

    public Map<String, String> getCommandHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        String deviceToken = PersistenceManager.getInstance().getString(PersistenceManager.TOKEN);
        if (deviceToken != null && !deviceToken.isEmpty())
            headers.put("X-TOKEN", deviceToken);

        return headers;
    }
}
