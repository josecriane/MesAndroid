package com.josecriane.mes.mesandroid.fragments;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.josecriane.mes.mesandroid.R;
import com.josecriane.mes.mesandroid.commands.DeviceSetupCommand;
import com.josecriane.mes.mesandroid.models.Device;
import com.josecriane.mes.mesandroid.SimpleGcm.GcmHelper;
import com.josecriane.mes.mesandroid.utils.ICallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {

    private EditText mDeviceIdEdt;

    public HomeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView deviceUidTxt = (TextView) view.findViewById(R.id.device_uid_txt);
        mDeviceIdEdt = (EditText) view.findViewById(R.id.device_id_edit);
        Button acceptBtn = (Button) view.findViewById(R.id.accept_btn);

        deviceUidTxt.setText(Build.SERIAL);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mDeviceIdEdt.getText().toString().isEmpty()) {
                    GcmHelper.register(getActivity(), new ICallback() {
                        @Override
                        public void finished(Object obj) {
                            //Add (string)obj to persistance as GCM token
                            String tokenGCM = (String)obj;
                            Device device = new Device(Integer.valueOf(mDeviceIdEdt.getText().toString()),Build.SERIAL, tokenGCM);
                            DeviceSetupCommand command = new DeviceSetupCommand(getActivity(), device);
                            command.execute();
                        }

                        @Override
                        public void finishedWithError(Object obj) {
                            Log.e("GCM Register", "Error");
                        }
                    });
                }
            }
        });

        return view;
    }
}
