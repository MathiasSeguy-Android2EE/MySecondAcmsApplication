package com.android2ee.formation.acms.janvmmxvii;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android2ee.formation.acms.janvmmxvii.view.mother.MotherActivity;

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
    private  TextView txvResult;
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
        txvResult= (TextView) findViewById(R.id.txvResult);
        //Add Listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMessage();
            }
        });
        //Add animations

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(RESULT,txvResult.getText().toString());
        outState.putString(MESSAGE,edtMessage.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txvResult.setText(savedInstanceState.getString(RESULT));
        edtMessage.setText(savedInstanceState.getString(MESSAGE));
    }

    /***********************************************************
    *  Business Methods
    **********************************************************/


    private void addMessage(){
        //find the message of the edtMessage
        messageTemp=edtMessage.getText().toString();
        txvResult.append(eol);
        txvResult.append(messageTemp);
        edtMessage.setText("");
    }
}
