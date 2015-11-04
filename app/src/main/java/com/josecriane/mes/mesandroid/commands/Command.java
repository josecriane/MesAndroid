package com.josecriane.mes.mesandroid.commands;

import android.content.Context;

import com.josecriane.mes.mesandroid.utils.ICallback;
import com.josecriane.mes.mesandroid.utils.VolleyHelper;

/**
 * Created by sito on 4/11/15.
 */
public abstract class Command {

    public String TAG;
    public Context mContext;

    public abstract void execute();

    public void cancel(){
        VolleyHelper.getInstance(mContext).cancelRequest(TAG);
    }


}
