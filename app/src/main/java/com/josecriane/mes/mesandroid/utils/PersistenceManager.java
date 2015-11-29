package com.josecriane.mes.mesandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PersistenceManager {

    public static String TOKEN = "TOKEN";

    private final String PREFS = "conf";
    private Context mContext;

    private static PersistenceManager mInstance;

    private PersistenceManager(Context context) {
        this.mContext = context;
    }

    public static void init(Context context) {
        mInstance = new PersistenceManager(context);
    }

    public static PersistenceManager getInstance() {
        return  mInstance;
    }

    public void storeString(String key, String value) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String value = settings.getString(key, null);

        return value;
    }

    public void delete(String key) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        if (settings.contains(key)) {
            Editor editor = settings.edit();
            editor.remove(key);
            editor.commit();
        } else {
            mContext.deleteFile(key);
        }
    }

    public void deleteAll() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        Editor editor = settings.edit();
        editor.clear();
        editor.commit();

        String[] fileList = mContext.fileList();
        for (int i = 0; i < fileList.length; i++) {
            String formName = fileList[i];
            mContext.deleteFile(formName);
        }
    }

    public void deleteAllButResources() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }
}
