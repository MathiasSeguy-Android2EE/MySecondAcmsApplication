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
import androidx.annotation.NonNull;
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
    private ViewHolder vh;
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
        rowView=convertView;
        if(rowView==null){
            if(getItemViewType(position)==0){
                rowView =inflater.inflate(R.layout.activity_main_item_even,parent,false);
            }else{
                rowView =inflater.inflate(R.layout.activity_main_item_odd,parent,false);
            }
            vh=new ViewHolder(rowView);
            rowView.setTag(vh);
        }
         smsMessage=getItem(position);
        vh= (ViewHolder) rowView.getTag();
        //update the view
        vh.getTxvName().setText(smsMessage.getName());
        vh.getTxvMessage().setText(smsMessage.getMessage());
        vh.getTxvFrom().setText(smsMessage.getTelFrom());
        vh.getImvPicture().setImageResource(smsMessage.getPictureId());
        vh.setCurrentPosition(position);
        return rowView;
    }

    /**
     * Delete from the dataset the item at the position below
     * @param position
     */
    private void deleteItem(int position){
        remove(getItem(position));
    }

    /***********************************************************
     * Managing odd and even line
     **********************************************************/

    @Override
    public int getItemViewType(int position) {
        if(getItem(position).getName().equals(MySmsMessage.TOTO)){
            return 0;
        }
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /***********************************************************
     *  ViewHolder
     **********************************************************/
    private class ViewHolder{
        TextView txvName,txvMessage,txvFrom;
        ImageView imvPicture,imvDelete;
        int currentPosition;
        View view;
        public ViewHolder(View rowView){
            view=rowView;
            txvName=(TextView)rowView.findViewById(R.id.txvName);
            txvMessage= (TextView) rowView.findViewById(R.id.txvMessage);
            txvFrom= (TextView) rowView.findViewById(R.id.txvFrom);
            imvPicture= (ImageView) rowView.findViewById(R.id.imvPicture);
            imvDelete= (ImageView) rowView.findViewById(R.id.imvDelete);
            imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteCurrent();
                }
            });
        }
        /***********************************************************
        *  Business Methods
        **********************************************************/
        private void deleteCurrent(){
            (MySmsMessageAdapter.this).deleteItem(currentPosition);
        }


        /***********************************************************
        *  Getters/Setters
        **********************************************************/

        public ImageView getImvPicture() {
            return imvPicture;
        }

        public TextView getTxvFrom() {
            return txvFrom;
        }

        public TextView getTxvMessage() {
            return txvMessage;
        }

        public TextView getTxvName() {
            return txvName;
        }

        public void setCurrentPosition(int currentPosition) {
            this.currentPosition = currentPosition;
        }
    }
}
