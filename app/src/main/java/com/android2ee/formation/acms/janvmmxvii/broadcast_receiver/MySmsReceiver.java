package com.android2ee.formation.acms.janvmmxvii.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android2ee.formation.acms.janvmmxvii.service.MySmsNotificationsService;

public class MySmsReceiver extends BroadcastReceiver {
    private static final String TAG = "MySmsReceiver";
    public MySmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"Ohaha sms received");
        Intent startMySmsService=new Intent(context, MySmsNotificationsService.class);
        startMySmsService.setAction(intent.getAction());
        startMySmsService.putExtras(intent);
        context.startService(startMySmsService);
    }
}
