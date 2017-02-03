/**
 * <ul>
 * <li>MyApplication</li>
 * <li>com.android2ee.formation.acms.janvmmxvii</li>
 * <li>01/02/2017</li>
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

package com.android2ee.formation.acms.janvmmxvii;

import android.app.Application;
import android.util.Log;

import com.android2ee.formation.acms.janvmmxvii.dao.MySmsMessageDaoIntf;
import com.android2ee.formation.acms.janvmmxvii.service.MySmsMessageServiceIntf;
import com.orm.SugarContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mathias Seguy - Android2EE on 01/02/2017.
 */
public class MyApplication extends Application {
    /***********************************************************
     * Singleton Pattern
     **********************************************************/
    private static MyApplication instance;
    public static MyApplication ins(){
        return instance;
    }
    /***********************************************************
    *  Attributes
    **********************************************************/
    private  static boolean postICS;
    /***********************************************************
    *  Managing LifeCycle
    **********************************************************/
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        instance=this;
        postICS=getResources().getBoolean(R.bool.postICS);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    public static boolean isPostICS(){
        return postICS;
    }

    /***********************************************************
     *  Almost the 1 second pattern
     **********************************************************/
    /**
     * To be called when you think the application life is over
     */
    public void unbindAndDie(){
        //free your services and others classes
        mySmsMessageDao=null;
        mySmsMessageService=null;
        //kill the threads
        killKeepAliveThreadExecutor();

    }

    /***********************************************************
     *  Manage my "Business" services and Dao
     **********************************************************/
    private MySmsMessageDaoIntf mySmsMessageDao=null;
    private MySmsMessageServiceIntf mySmsMessageService=null;
    public MySmsMessageServiceIntf getMySmsMessageService(){
        if(mySmsMessageService==null){
            mySmsMessageService=Injector.getMySmsMessageService(this);
        }
        return mySmsMessageService;
    }
    public MySmsMessageDaoIntf getMySmsMessageDao(){
        if(mySmsMessageDao==null){
            mySmsMessageDao=Injector.getMySmsMessageDao(this);
        }
        return mySmsMessageDao;
    }
    /***********************************************************
     *  Managing Threads
     **********************************************************/
    /******************************************************************************************/
    /** Pool Executor for Threads that has to finish they threatment when the application shutdown**/
    /******************************************************************************************/
    /**
     * The pool executor to use for all cancellable thread and Threads that has to cancelled when the application shutdown
     */
    private ExecutorService keepAliveThreadsExecutor = null;

    /**
     * @return the cancelableThreadsExceutor
     */
    public final ExecutorService getKeepAliveThreadsExecutor() {
        if (keepAliveThreadsExecutor == null) {
            keepAliveThreadsExecutor = Executors.newFixedThreadPool(12, new BackgroundThreadFactory());
        }
        return keepAliveThreadsExecutor;
    }

    /**
     * And its associated factory
     */
    private class BackgroundThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("KeepAlive" + ((int) (Math.random() * 1000)));
            return t;
        }
    }

    /**
     * Kill all running Thread and destroy then all
     * Kill the cancelableThreadsExceutor
     */
    private void killKeepAliveThreadExecutor() {
        if (keepAliveThreadsExecutor != null) {
            keepAliveThreadsExecutor.shutdown(); // Disable new tasks from being submitted
            try {// as long as your threads hasn't finished
                while (!keepAliveThreadsExecutor.isTerminated()) {
                    // Wait a while for existing tasks to terminate
                    if (!keepAliveThreadsExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                        // Cancel currently executing tasks
                        keepAliveThreadsExecutor.shutdown();
                        Log.e("MyApp", "Probably a memory leak here");
                    }
                }
            } catch (InterruptedException ie) {
                // (Re-)Cancel if current thread also interrupted
                keepAliveThreadsExecutor.shutdownNow();
                keepAliveThreadsExecutor = null;
                Log.e("MyApp", "Probably a memory leak here too");
            }
        }
        keepAliveThreadsExecutor = null;
    }
}
