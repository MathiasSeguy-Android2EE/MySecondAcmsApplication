/**
 * <ul>
 * <li>MySmsMessageAdapter</li>
 * <li>com.android2ee.formation.acms.janvmmxvii.view.main.adapter</li>
 * <li>31/01/2017</li>
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

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.formation.acms.janvmmxvii.R;
import com.android2ee.formation.acms.janvmmxvii.cross.model.MySmsMessage;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 31/01/2017.
 */
public class MySmsMessageAdapter extends ArrayAdapter<MySmsMessage> {
    /***********************************************************
    *  Attributes
    **********************************************************/
    LayoutInflater inflater;
    /***********************************************************
     *  Temp Attributes
     **********************************************************/
    private View rowView;
    private MySmsMessage smsMessage;

    /***********************************************************
    *  Constructors
    **********************************************************/
    public MySmsMessageAdapter(Context context, ArrayList<MySmsMessage> dataset) {
        super(context, R.layout.activity_main_item_even,dataset);
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        rowView =inflater.inflate(R.layout.activity_main_item_even,parent,false);
         smsMessage=getItem(position);
        //update the view
        ((TextView)rowView.findViewById(R.id.txvName)).setText(smsMessage.getName());
        ((TextView)rowView.findViewById(R.id.txvMessage)).setText(smsMessage.getMessage());
        ((TextView)rowView.findViewById(R.id.txvFrom)).setText(smsMessage.getFrom());
        ((ImageView)rowView.findViewById(R.id.imvPicture)).setImageResource(smsMessage.getPictureId());
        return rowView;
    }
}
