/**
 * <ul>
 * <li>MySmsMessageService</li>
 * <li>com.android2ee.formation.acms.janvmmxvii.service</li>
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

package com.android2ee.formation.acms.janvmmxvii.service;

import com.android2ee.formation.acms.janvmmxvii.MyApplication;
import com.android2ee.formation.acms.janvmmxvii.cross.model.MySmsMessage;

/**
 * Created by Mathias Seguy - Android2EE on 03/02/2017.
 */
public class MySmsMessageServiceMock implements MySmsMessageServiceIntf  {
    /***********************************************************
     *  Constructors
     **********************************************************/
    private MySmsMessageServiceMock(){}
    public MySmsMessageServiceMock(MyApplication app){
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
     */

    // /******************************************************************************************/
    /** Method name: loadAll
     /* Description : Ask to load all the MySmsMessage from DB **********/
    /* Param:
    /******************************************************************************************/

    /**
     * Should only be called from a background thread (So only by another Service's method)
     * Don't ever call this method from the UI Thread
     */
    public void loadAllSync( ) {
        MyApplication.ins().getMySmsMessageDao().loadAll();
        //and returning using an Event (like EventBus or Otto is a good idea)
    }

    /**
     * Should be called by the View
     */
    public void loadAllAsynch( ) {
        // then launch it
        //As a keep alive thread
        //MyApplication.instance.getServiceManager().getKeepAliveThreadsExecutor().submit(new RunnableLoadAll ());

        //Or as a cancelable Thread
        MyApplication.ins().getKeepAliveThreadsExecutor().submit(new RunnableLoadAll());
    }

    /**
     * This is the runnable that will send the work in a background thread
     */
    private class RunnableLoadAll implements Runnable {
        public RunnableLoadAll( ) {
        }

        @Override
        public void run() {
            loadAllSync();
        }
    }
    /**
     * Ask to save the MySmsMessage in database
     * --------------------------------------------------------------------------------------
     * /!\ The return is void because you never returns any data from a service's method /!\
     * ---------------------------------------------------------------------------------------
     */
     // /******************************************************************************************/
    /** Method name: save
     /* Description :  **********/
    /* Param: MySmsMessage messageToSave
    /******************************************************************************************/

    /**
     * Should only be called from a background thread (So only by another Service's method)
     * Don't ever call this method from the UI Thread
     */
    public void saveSync(MySmsMessage messageToSave ) {
        MyApplication.ins().getMySmsMessageDao().save(messageToSave);
    }

    /**
     * Should be called by the View
     */
    public void saveAsynch(MySmsMessage messageToSave ) {
        // then launch it
        //As a keep alive thread
        //MyApplication.instance.getServiceManager().getKeepAliveThreadsExecutor().submit(new RunnableSave ());

        //Or as a cancelable Thread
        MyApplication.ins().getKeepAliveThreadsExecutor().submit(new RunnableSave(messageToSave));
    }

    /**
     * This is the runnable that will send the work in a background thread
     */
    private class RunnableSave implements Runnable {
        MySmsMessage messageToSave;

        public RunnableSave(MySmsMessage messageToSave ) {
            this.messageToSave = messageToSave;
        }

        @Override
        public void run() {
            saveSync(messageToSave);
        }
    }
    /**
     * Ask to delete the MySmsMessage in database
     * --------------------------------------------------------------------------------------
     * /!\ The return is void because you never returns any data from a service's method /!\
     * ---------------------------------------------------------------------------------------
     */
     // /******************************************************************************************/
    /** Method name: delete
     /* Description :  **********/
    /* Param: MySmsMessage mySmsMessage mySmsMessage
    /******************************************************************************************/

    /**
     * Should only be called from a background thread (So only by another Service's method)
     * Don't ever call this method from the UI Thread
     */
    public void deleteSync(MySmsMessage  mySmsMessage) {
        MyApplication.ins().getMySmsMessageDao().delete(mySmsMessage);
        //and returning using an Event (like EventBus or Otto is a good idea)
    }

    /**
     * Should be called by the View
     */
    public void deleteAsynch(MySmsMessage  mySmsMessage) {
        // then launch it
        //As a keep alive thread
        //MyApplication.instance.getServiceManager().getKeepAliveThreadsExecutor().submit(new RunnableDelete ());

        //Or as a cancelable Thread
        MyApplication.ins().getKeepAliveThreadsExecutor().submit(new RunnableDelete(mySmsMessage));
    }

    /**
     * This is the runnable that will send the work in a background thread
     */
    private class RunnableDelete implements Runnable {
        MySmsMessage mySmsMessage;

        public RunnableDelete(MySmsMessage  mySmsMessage) {
            this.mySmsMessage = mySmsMessage;
        }

        @Override
        public void run() {
            deleteSync(mySmsMessage);
        }
    }
//This code has to be in your service class (Android Service or Business service)
/******************************************************************************************/
/** Method name: clear
 * Description :  **********/
/******************************************************************************************/

    /**
     * Should only be called from a background thread (So only by another Service's method)
     * Don't ever call this method from the UI Thread
     */
    public void clearSync() {
        MyApplication.ins().getMySmsMessageDao().clear();

        //and returning using an Event (like EventBus or Otto is a good idea)
    }

    /**
     * Should be called by the View
     */
    public void clearAsynch() {
        // then launch it
        //As a keep alive thread
        //MyApplication.instance.getServiceManager().getKeepAliveThreadsExecutor().submit(new RunnableClear ());

        //Or as a cancelable Thread
        MyApplication.ins().getKeepAliveThreadsExecutor().submit(new RunnableClear());
    }

    /**
     * This is the runnable that will send the work in a background thread
     */
    private class RunnableClear implements Runnable {

        public RunnableClear() {
        }

        @Override
        public void run() {
            clearSync();
        }
    }
}
