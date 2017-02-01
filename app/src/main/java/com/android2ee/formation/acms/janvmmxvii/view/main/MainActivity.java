package com.android2ee.formation.acms.janvmmxvii.view.main;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android2ee.formation.acms.janvmmxvii.R;
import com.android2ee.formation.acms.janvmmxvii.cross.model.MySmsMessage;
import com.android2ee.formation.acms.janvmmxvii.view.main.adapter.MySmsMessageAdapter;
import com.android2ee.formation.acms.janvmmxvii.view.mother.MotherActivity;

import java.util.ArrayList;

public class MainActivity extends MotherActivity {
    /***********************************************************
    *  Constants
    **********************************************************/
    private static final String TAG = "MainActivity";
    /**
     * EndOfLine
     */
    private static final String eol="\r\n";
    /**
     * Constant for the SaveAndRestore bundle
     */
    public static final String RESULT = "RESULT";
    public static final String MESSAGE = "MESSAGE";
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
    private ListView lsvResult;
    /**
     * The arrayAdapter (the brain) of the listView
     */
    private MySmsMessageAdapter arrayAdapter;
    /**
     * The dataset of the ListView/ArrayAdapter
     */
    private ArrayList<MySmsMessage> messages;
    /**
     * The button to add the content of the edtMessage into the result area
     */
    private Button btnAdd;
    /***********************************************************
     * Temp Attributes
     **********************************************************/
    private  String messageTemp;
    /***********************************************************
    *  Managing LifeCycle
    **********************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instantiate the view
        setContentView(R.layout.activity_main);
        //findViewBy id
        edtMessage= (EditText) findViewById(R.id.edtMessage);
        btnAdd= (Button) findViewById(R.id.btnAdd);
        lsvResult= (ListView) findViewById(R.id.lsvResult);
        //manage a list view
        messages=new ArrayList<>();
        arrayAdapter=new MySmsMessageAdapter(this,messages);
        lsvResult.setAdapter(arrayAdapter);
        //Add Listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMessage();
            }
        });
        lsvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                copyItemInEdtMessage(position);
            }
        });
        //Add animations

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putStringArrayList(RESULT,messages);
        outState.putString(MESSAGE,edtMessage.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        messages.clear();
//        for (String s : savedInstanceState.getStringArrayList(RESULT)) {
//            messages.add(s);
//        }
//        arrayAdapter.notifyDataSetChanged();
        edtMessage.setText(savedInstanceState.getString(MESSAGE));
    }

    /***********************************************************
    *  Business Methods
    **********************************************************/


    /**
     * Add the content of the EditText to the result area
     */
    private void addMessage(){
        //find the message of the edtMessage
        messageTemp=edtMessage.getText().toString();
        //second way
        arrayAdapter.add(new MySmsMessage(messageTemp,messages.size()));
        edtMessage.setText("");
        //then you can hide the keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtMessage.getWindowToken(), 0);

    }

    /**
     * Copy the value of the Item in the EdtMessage
     * @param position
     */
    private void copyItemInEdtMessage(int position){
        messageTemp=arrayAdapter.getItem(position).getMessage();
        edtMessage.setText(messageTemp);
    }
}
