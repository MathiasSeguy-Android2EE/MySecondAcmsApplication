/**
 * <ul>
 * <li>MySmsMessageDao</li>
 * <li>com.android2ee.formation.acms.janvmmxvii</li>
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

package com.android2ee.formation.acms.janvmmxvii.dao;

import android.util.Log;

import com.android2ee.formation.acms.janvmmxvii.MyApplication;
import com.android2ee.formation.acms.janvmmxvii.cross.events.MySmsMessageAddedEvent;
import com.android2ee.formation.acms.janvmmxvii.cross.events.MySmsMessageDeletedEvent;
import com.android2ee.formation.acms.janvmmxvii.cross.events.MySmsMessageLoadedEvent;
import com.android2ee.formation.acms.janvmmxvii.cross.model.MySmsMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 03/02/2017.
 */
public class MySmsMessageDao implements MySmsMessageDaoIntf {
    private static final String TAG = "MySmsMessageDao";
    /***********************************************************
    *  Constructors
    **********************************************************/
    private MySmsMessageDao(){}
    public MySmsMessageDao(MyApplication app){
        if(app==null){
            throw new IllegalArgumentException("You should not try to instantiate this object directlly use MyApplication.ins().getMySmsMessageDao instead");
        }
    }
    /***********************************************************
     *  Business Methods
     **********************************************************/
    /**
     * Ask to load all the MySmsMessage stored in database
     * --------------------------------------------------------------------------------------
     * /!\ The return is void because you never returns any data from a service's method /!\
     * ---------------------------------------------------------------------------------------
     * The return is done using event bus
     */
    @Override
    public void loadAll(){

        Log.e(TAG,"list size = "+MySmsMessage.listAll(MySmsMessage.class).size());
        EventBus.getDefault().post(new MySmsMessageLoadedEvent( (ArrayList<MySmsMessage>) MySmsMessage.listAll(MySmsMessage.class)));
    }

    /**
     * Ask to save the MySmsMessage in database
     * --------------------------------------------------------------------------------------
     * /!\ The return is void because you never returns any data from a service's method /!\
     * ---------------------------------------------------------------------------------------
     */
    @Override
    public void save(MySmsMessage messageToSave){
        messageToSave.save();
        EventBus.getDefault().post(new MySmsMessageAddedEvent(messageToSave));
    }

    /**
     * Ask to delete the MySmsMessage in database
     * --------------------------------------------------------------------------------------
     * /!\ The return is void because you never returns any data from a service's method /!\
     * ---------------------------------------------------------------------------------------
     */
    @Override
    public void delete(MySmsMessage messageToDelete){
        messageToDelete.delete();
        EventBus.getDefault().post(new MySmsMessageDeletedEvent(messageToDelete));
    }
    /**
     * Ask to delete all MySmsMessage in database
     * --------------------------------------------------------------------------------------
     * /!\ The return is void because you never returns any data from a service's method /!\
     * ---------------------------------------------------------------------------------------
     */
    @Override
    public void clear(){
        MySmsMessage.deleteAll(MySmsMessage.class);
    }
}
