/**
 * <ul>
 * <li>MySmsMessAdapterCallBack</li>
 * <li>com.android2ee.formation.acms.janvmmxvii.view.main.adapter</li>
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
package com.android2ee.formation.acms.janvmmxvii.view.main.adapter;

import com.android2ee.formation.acms.janvmmxvii.cross.model.MySmsMessage;

/**
 * Created by Mathias Seguy - Android2EE on 01/02/2017.
 */
public interface MySmsMessAdapterCallBack {

    /**
     * Callback to be wake up when an item is selected
     */
    public void itemSelected(MySmsMessage item);

    /**
     * Ask if the item has to be deleted
     * @param item
     */
    public void askforItemDeletion(MySmsMessage item,int position);
}
