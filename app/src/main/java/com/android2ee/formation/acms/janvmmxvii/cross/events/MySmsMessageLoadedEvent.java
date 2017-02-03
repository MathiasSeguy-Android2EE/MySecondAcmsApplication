/**
 * <ul>
 * <li>MySmsMessageLoadedEvent</li>
 * <li>com.android2ee.formation.acms.janvmmxvii.cross.events</li>
 * <li>03/02/2017</li>
 * <p>
 * <li>======================================================</li>
 * <p>
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 * <p>
 * /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br>
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 * Belongs to <strong>Mathias Seguy</strong></br>
 * ***************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * <p>
 * *****************************************************************************************************************</br>
 * Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 * Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br>
 * Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */

package com.android2ee.formation.acms.janvmmxvii.cross.events;

import android.util.Log;

import com.android2ee.formation.acms.janvmmxvii.cross.model.MySmsMessage;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 03/02/2017.
 */
public class MySmsMessageLoadedEvent {
    private static final String TAG = "MySmsMessageLoadedEvent";
    ArrayList<MySmsMessage> mySmsMessageArrayList;

    public MySmsMessageLoadedEvent(ArrayList<MySmsMessage> mySmsMessageArrayList) {
        this.mySmsMessageArrayList = mySmsMessageArrayList;
        Log.e(TAG,"list size = "+mySmsMessageArrayList.size());
    }

    public ArrayList<MySmsMessage> getMySmsMessageArrayList() {
        return mySmsMessageArrayList;
    }

    public void setMySmsMessageArrayList(ArrayList<MySmsMessage> mySmsMessageArrayList) {
        this.mySmsMessageArrayList = mySmsMessageArrayList;
    }
}
