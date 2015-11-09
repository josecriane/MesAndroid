package com.josecriane.mes.mesandroid.SimpleGcm;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.josecriane.mes.mesandroid.utils.ICallback;

import java.io.IOException;

/**
 * Created by sito on 4/11/15.
 */
public class GcmHelper {

    private static String PROJECT_DEV = "907699261208";

    public static void register(final Context context, final ICallback callback){
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
                    String registerId = gcm.register(PROJECT_DEV);

                    if (registerId != null && !registerId.isEmpty()) {
                        Log.d("GCM Helper", "Registration id" + registerId);
                        callback.finished(registerId);
                    } else {
                        callback.finishedWithError("Empty");
                    }
                } catch (IOException e) {
                    callback.finishedWithError("Catch IOException");
                }
                return "";
            }
        }.execute(null, null, null);
    }
}
