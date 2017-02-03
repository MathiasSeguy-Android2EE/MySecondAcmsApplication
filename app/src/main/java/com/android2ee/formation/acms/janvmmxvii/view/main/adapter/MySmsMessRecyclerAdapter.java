/**
 * <ul>
 * <li>MySmsMessRecyclerAdapter</li>
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

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.formation.acms.janvmmxvii.MyApplication;
import com.android2ee.formation.acms.janvmmxvii.R;
import com.android2ee.formation.acms.janvmmxvii.cross.model.MySmsMessage;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 01/02/2017.
 */
public class MySmsMessRecyclerAdapter extends RecyclerView.Adapter<MySmsMessRecyclerAdapter.ViewHolder> {
    private static final String TAG = "MySmsMessRecyclerAdapte";
    /***********************************************************
     *  Attributes
     **********************************************************/
    LayoutInflater inflater;
    ArrayList<MySmsMessage> dataset;
    MySmsMessAdapterCallBack mySmsMessAdapterCallBack;
    /***********************************************************
     *  Temp Attributes
     **********************************************************/
    private View rowView;
    private MySmsMessage smsMessage;
    private MySmsMessRecyclerAdapter.ViewHolder vh;


    public MySmsMessRecyclerAdapter(Context ctx, ArrayList<MySmsMessage> dataset){
        inflater=LayoutInflater.from(ctx);
        this.dataset=dataset;
        mySmsMessAdapterCallBack = (MySmsMessAdapterCallBack) ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            rowView =inflater.inflate(R.layout.activity_main_item_even,parent,false);
        }else{
            rowView =inflater.inflate(R.layout.activity_main_item_odd,parent,false);
        }
        vh=new MySmsMessRecyclerAdapter.ViewHolder(rowView);
        rowView.setTag(vh);

        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        smsMessage=dataset.get(position);
        //update the view
        holder.getTxvName().setText(smsMessage.getName());
        holder.getTxvMessage().setText(smsMessage.getMessage());
        holder.getTxvFrom().setText(smsMessage.getTelFrom());
        holder.getImvPicture().setImageResource(smsMessage.getPictureId());
    }
    @Override
    public int getItemCount() {
        return dataset.size();
    }

    /***********************************************************
     *  Dataset management
     **********************************************************/
    /**
     * Delete from the dataset the item at the position below
     * @param position
     */
    private void deleteItem(int position){
       deleteItem(dataset.get(position),position);
    }

    /**
     * Delete from the dataset the item
     * @param item
     */
    public void deleteItem(MySmsMessage item){
        if(dataset.contains(item)){
            int pos=dataset.indexOf(item);
            dataset.remove(pos);
            notifyItemRemoved(pos);
        }
    }
    /**
     * Delete from the dataset the item at the position below
     * @param item
     */
    public void deleteItem(MySmsMessage item,int position){
        dataset.remove(item);
        notifyItemRemoved(position);
    }

    public void clean(){
        int size=dataset.size();
        dataset.clear();
        notifyItemRangeRemoved(0,size);
    }

    /**
     * Add the item at the specific position
     * @param itemTemp
     * @param positionTemp
     */
    public void addItemAt(MySmsMessage itemTemp,int positionTemp){
        dataset.add(positionTemp,itemTemp);
        notifyItemInserted(positionTemp);
    }
    /**
     * Ask the container if the item has to be deleted
     * @param position
     */
    private void askforItemDeletion(int position){
       mySmsMessAdapterCallBack.askforItemDeletion(dataset.get(position),position);
    }

    public void add(MySmsMessage itemToAdd){
        dataset.add(itemToAdd);
        notifyItemInserted(dataset.size());
    }

    public void rebuild(ArrayList<MySmsMessage> mySmsMessageArrayList){
        int size=dataset.size();
        dataset.clear();
        notifyItemRangeRemoved(0,size);
        Log.e(TAG,"list size = "+mySmsMessageArrayList.size());
        dataset=mySmsMessageArrayList;
        notifyItemRangeInserted(0,dataset.size());
    }

    public MySmsMessage getItem(int position){
        return dataset.get(position);
    }

    public void itemSelected(int position){
         mySmsMessAdapterCallBack.itemSelected(dataset.get(position));
    }

    /***********************************************************
     * Managing odd and even line
     **********************************************************/
    @Override
    public int getItemViewType(int position) {
        if(dataset.get(position).getName().equals(MySmsMessage.TOTO)){
            return 0;
        }
        return 1;
    }

    /***********************************************************
     * ViewHolder pattern
     **********************************************************/

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txvName,txvMessage,txvFrom;
        ImageView imvPicture,imvDelete;
        View view;
        /**
         * AnimatorSet to run ObjectAnimator based animations
         */
        private AnimatorSet animatorRotationX;
        @SuppressLint("NewApi")
        public ViewHolder(View itemView) {
            super(itemView);
            view=rowView;

            txvName=(TextView)rowView.findViewById(R.id.txvName);
            txvMessage= (TextView) rowView.findViewById(R.id.txvMessage);
            txvFrom= (TextView) rowView.findViewById(R.id.txvFrom);
            imvPicture= (ImageView) rowView.findViewById(R.id.imvPicture);
            imvDelete= (ImageView) rowView.findViewById(R.id.imvDelete);
            if(MyApplication.isPostICS()){
                animatorRotationX = (AnimatorSet) AnimatorInflater.loadAnimator(
                        MyApplication.ins(),
                        R.animator.widget_rotate_x);
                animatorRotationX.setTarget(view);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemSelected();
                }
            });
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
            (MySmsMessRecyclerAdapter.this).askforItemDeletion(getAdapterPosition());
        }

        private void itemSelected(){
            (MySmsMessRecyclerAdapter.this).itemSelected(getAdapterPosition());
        }
        @SuppressLint("NewApi")
        public void animateAddedItem(){
            if(MyApplication.isPostICS()){
                animatorRotationX.start();
            }
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

    }
}
