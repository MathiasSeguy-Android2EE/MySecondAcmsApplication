/**
 * <ul>
 * <li>Injector</li>
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

package com.android2ee.formation.acms.janvmmxvii;

import com.android2ee.formation.acms.janvmmxvii.dao.MySmsMessageDaoIntf;
import com.android2ee.formation.acms.janvmmxvii.dao.MySmsMessageDaoMock;
import com.android2ee.formation.acms.janvmmxvii.service.MySmsMessageServiceIntf;
import com.android2ee.formation.acms.janvmmxvii.service.MySmsMessageServiceMock;

/**
 * Created by Mathias Seguy - Android2EE on 03/02/2017.
 */
public class Injector {

    public static  MySmsMessageServiceIntf getMySmsMessageService(MyApplication app){
         return new MySmsMessageServiceMock(app);
    }
    public static  MySmsMessageDaoIntf getMySmsMessageDao(MyApplication app){
            return new MySmsMessageDaoMock(app);
    }
}
