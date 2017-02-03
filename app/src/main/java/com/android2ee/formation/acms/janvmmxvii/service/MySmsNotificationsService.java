package com.android2ee.formation.acms.janvmmxvii.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;

import com.android2ee.formation.acms.janvmmxvii.MyApplication;
import com.android2ee.formation.acms.janvmmxvii.R;
import com.android2ee.formation.acms.janvmmxvii.cross.model.MySmsMessage;
import com.android2ee.formation.acms.janvmmxvii.view.main.MainActivity;

public class MySmsNotificationsService extends Service {
    private static final String TAG = "MySmsNotifService";
    private static final String SMS_RECEIVE_INTENT_NAME = "android.provider.Telephony.SMS_RECEIVED";
    private final int UniqueNotificationId=130274;
    private PendingIntent pdIntent=null;
    public MySmsNotificationsService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"Ouhaha JE suis dans le service !!!");
        if(pdIntent==null){
            pdIntent=PendingIntent.getActivity(this,0,new Intent(this, MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        }
        analyseSms(intent);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }



    /**
     * Analyse the Sms included in the Intent
     * And show notification
     * @param intent
     */
    private void analyseSms(Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVE_INTENT_NAME)) {
            //Retrieve the bundle that handles the Messages
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                //Retrieve the data store in the SMS
                Object[] pdus = (Object[]) bundle.get("pdus");
                    //Declare the associated SMS Messages
                SmsMessage[] smsMessages = new SmsMessage[pdus.length];
                //Rebuild your SMS Messages
                for (int i = 0; i < pdus.length; i++) {
                    smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                //Parse your SMS Message
                SmsMessage currentMessage;
                String body, from;
                long when;
                for (int i = 0; i < smsMessages.length; i++) {
                    currentMessage = smsMessages[i];
                    body = currentMessage.getDisplayMessageBody();
                    from = currentMessage.getDisplayOriginatingAddress();
                    when = currentMessage.getTimestampMillis();
                    displayNotif(from,body,when,i);
                    MyApplication.ins().getMySmsMessageService().saveAsynch(new MySmsMessage(body,from));
                    //store it
//                    try {
//                        MySmsDao.getIns().save(new MySms(ContactHelper.findName(from),body,from));
//                    } catch (SQLException e) {
//                        Log.e(TAG,"You should centralize exception management");
//                    }
                }
            }
        }
    }

    /**
     * Displays the notification associated with the Sms content
     * @param from
     * @param body
     * @param when
     * @param i
     */
    private void displayNotif(String from, String body,long when,int i) {
        //display the notif
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setAutoCancel(true)
                .setContentIntent(pdIntent)
                .setContentText(body)
                .setContentTitle("New SMS de :" + from)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setLights(0x99FF0000, 0, 1000)//don't work
                .setNumber(41108)
                .setOngoing(false)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_notif)
                .setSubText("SubText")
                .setTicker("You received a new SMS from " + from)
                .setVibrate(new long[]{100, 200, 100, 200, 100}) //need permission
                .setWhen(when);

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(this);
        notifManager.notify(UniqueNotificationId+i, builder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        //not used
        return null;
    }
}
