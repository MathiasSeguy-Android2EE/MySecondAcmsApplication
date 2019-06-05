package com.android2ee.formation.acms.janvmmxvii.view.main;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android2ee.formation.acms.janvmmxvii.MyApplication;
import com.android2ee.formation.acms.janvmmxvii.R;
import com.android2ee.formation.acms.janvmmxvii.cross.events.MySmsMessageAddedEvent;
import com.android2ee.formation.acms.janvmmxvii.cross.events.MySmsMessageDeletedEvent;
import com.android2ee.formation.acms.janvmmxvii.cross.events.MySmsMessageLoadedEvent;
import com.android2ee.formation.acms.janvmmxvii.cross.model.MySmsMessage;
import com.android2ee.formation.acms.janvmmxvii.view.main.adapter.MySimpleRCAnimator;
import com.android2ee.formation.acms.janvmmxvii.view.main.adapter.MySmsMessAdapterCallBack;
import com.android2ee.formation.acms.janvmmxvii.view.main.adapter.MySmsMessRecyclerAdapter;
import com.android2ee.formation.acms.janvmmxvii.view.mother.MotherActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends MotherActivity implements MySmsMessAdapterCallBack {
    /***********************************************************
     * Constants
     **********************************************************/
    private static final String TAG = "MainActivity";
    /**
     * EndOfLine
     */
    private static final String eol = "\r\n";
    /**
     * Constant for the SaveAndRestore bundle
     */
    public static final String RESULT = "RESULT";
    public static final String MESSAGE = "MESSAGE";
    public static final int ALERT_DIALOG_UNIC_ID = 110274;
    /***********************************************************
     * Attributes
     **********************************************************/
    /**
     * The edit text to define the message
     */
    private EditText edtMessage;
    /**
     * The result area
     */
    private RecyclerView rcvResult;
    /**
     * The arrayAdapter (the brain) of the listView
     */
    private MySmsMessRecyclerAdapter arrayAdapter;
    /**
     * The dataset of the ListView/ArrayAdapter
     */
    private ArrayList<MySmsMessage> messages;
    /**
     * The button to add the content of the edtMessage into the result area
     */
    private Button btnAdd;
    /**
     * The tween animation to bump the BtnAdd widget
     */
    private Animation bumpBtnAdd;
    /**
     * AnimatorSet to run ObjectAnimator based animations
     */
    private AnimatorSet animatorRotationX;
    /***********************************************************
     * Temp Attributes
     **********************************************************/
    private String messageTemp;
    private MySmsMessage itemTemp;
    private int positionTemp;

    /***********************************************************
     * Managing LifeCycle
     **********************************************************/
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instantiate the view
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("Using ToolBar");
        //findViewBy id
        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        rcvResult = (RecyclerView) findViewById(R.id.rcvResult);
        //instanciate elements
        bumpBtnAdd = AnimationUtils.loadAnimation(this, R.anim.bump_animation);
        if (MyApplication.isPostICS()) {
            animatorRotationX = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.widget_rotate_x);
            animatorRotationX.setTarget(edtMessage);
        }
        //manage a list view
        messages = new ArrayList<>();

        arrayAdapter = new MySmsMessRecyclerAdapter(this, messages);
        rcvResult.setLayoutManager(new LinearLayoutManager(this));
        rcvResult.setItemAnimator(new MySimpleRCAnimator());
        rcvResult.setAdapter(arrayAdapter);
        //Add Listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMessage();
            }
        });
//        rcvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                copyItemInEdtMessage(position);
//            }
//        });
        //Add animations

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        MyApplication.ins().getMySmsMessageService().loadAllAsynch();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RESULT, messages);
        outState.putString(MESSAGE, edtMessage.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        messages.clear();
        for (Parcelable parcelable : savedInstanceState.getParcelableArrayList(RESULT)) {
            messages.add((MySmsMessage) parcelable);
        }
        arrayAdapter.notifyDataSetChanged();
        edtMessage.setText(savedInstanceState.getString(MESSAGE));
    }

    /***********************************************************
     *  EventBus's events reception
     **********************************************************/
    /**
     * This method is called by eventbus when MySmsMessage are loaded from database
     * @param evt
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mySmsLoaded(MySmsMessageLoadedEvent evt){
        arrayAdapter.rebuild(evt.getMySmsMessageArrayList());
    }
    /**
     * This method is called by eventbus when a MySmsMessage has been saved in database
     * @param evt
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mySmsAdded(MySmsMessageAddedEvent evt){
        arrayAdapter.add(evt.getAddedMessage());
        edtMessage.setText("");
        //then you can hide the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtMessage.getWindowToken(), 0);
    }
    /**
     * This method is called by eventbus when a MySmsMessage has been deleted in database
     * @param evt
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mySmsDeleted(MySmsMessageDeletedEvent evt){
        arrayAdapter.deleteItem(evt.getDeletedMessage());
    }
    /***********************************************************
     *  Business Methods
     **********************************************************/


    /**
     * Add the content of the EditText to the result area
     */
    @SuppressLint("NewApi")
    private void addMessage() {
        //launch tween animation
        btnAdd.startAnimation(bumpBtnAdd);
        if (MyApplication.isPostICS()) {
            animatorRotationX.start();
        }
        //find the message of the edtMessage
        messageTemp = edtMessage.getText().toString();
        //second way
        MyApplication.ins().getMySmsMessageService().saveAsynch(new MySmsMessage(messageTemp, messages.size()));


    }

    /**
     * Callback to be wake up when an item is selected
     *
     * @param item
     */
    @Override
    public void itemSelected(MySmsMessage item) {
        copyItemInEdtMessage(item);
    }

    /**
     * Copy the value of the Item in the EdtMessage
     *
     * @param item
     */
    private void copyItemInEdtMessage(MySmsMessage item) {
        messageTemp = item.getMessage();
        edtMessage.setText(messageTemp);
    }


    /**
     * Ask if the item has to be deleted
     *
     * @param item
     */
    @Override
    public void askforItemDeletion(MySmsMessage item, int position) {
        itemTemp = item;
        positionTemp = position;
        showDialog(ALERT_DIALOG_UNIC_ID);
    }

    /**
     * Really delete the item
     */
    private void okDeleteITem() {
        //ok delete
        arrayAdapter.deleteItem(itemTemp, positionTemp);
        //display snackbar
        Snackbar.make(findViewById(R.id.activity_main),
                R.string.snackbar_message, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        undoItemDeletion();
                    }
                })
                .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        if(event!=BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_ACTION){
                            deleteITemForReal();
                        }
                    }
                }).show();

    }

    private void deleteITemForReal(){
        //Here you can delete the item, the undo reflexion time has expired
        MyApplication.ins().getMySmsMessageService().deleteAsynch(itemTemp);
    }
    /**
     * Undo item deletion (not obvious?)
     */
    private void undoItemDeletion() {
        arrayAdapter.addItemAt(itemTemp, positionTemp);
    }

    private void dontDeleteItem() {
        Toast.makeText(this,
                getString(R.string.toast_message),
                Toast.LENGTH_LONG).show();
    }

    private void killThemAll() {
        arrayAdapter.clean();
        MyApplication.ins().getMySmsMessageService().clearAsynch();
    }

    /***********************************************************
     * Managing AlertDialog
     **********************************************************/
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case ALERT_DIALOG_UNIC_ID:
                return createMyDialog();
        }
        return super.onCreateDialog(id);
    }

    /**
     * Create the dialog
     *
     * @return
     */
    private Dialog createMyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.alertdialog_message, itemTemp.getMessage()));
        builder.setPositiveButton(R.string.alertdialog_ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                okDeleteITem();
            }
        });
        builder.setNegativeButton(R.string.alertdialog_nok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dontDeleteItem();
            }
        });
        return builder.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        if (id == ALERT_DIALOG_UNIC_ID) {
            ((AlertDialog) dialog).setMessage(getString(R.string.alertdialog_message, itemTemp.getMessage()));
        }
        super.onPrepareDialog(id, dialog);
    }

    /***********************************************************
     * ToolBar
     **********************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_killthemall:
                killThemAll();
        }
        return super.onOptionsItemSelected(item);
    }
}
