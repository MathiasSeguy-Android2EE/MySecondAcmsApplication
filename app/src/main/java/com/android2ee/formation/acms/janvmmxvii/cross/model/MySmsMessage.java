/**
 * <ul>
 * <li>MySmsMessage</li>
 * <li>com.android2ee.formation.acms.janvmmxvii.cross.model</li>
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

package com.android2ee.formation.acms.janvmmxvii.cross.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.android2ee.formation.acms.janvmmxvii.MyApplication;
import com.android2ee.formation.acms.janvmmxvii.R;

/**
 * Created by Mathias Seguy - Android2EE on 31/01/2017.
 */
public class MySmsMessage implements Parcelable {
    public static final String TOTO = "Toto";
    public static final String TATA = "Tata";
    /***********************************************************
     *  Attributes
     **********************************************************/

    private String name;
    private String message;
    private String from;
    private int pictureId;
    boolean fromOwner=false;
    /***********************************************************
     *  Constructors
     **********************************************************/
    public MySmsMessage(String mess,int position){
        this.message=mess;
        if(position%2==0){
            name= TOTO;
            fromOwner=true;
            pictureId= R.drawable.ic_face_toto;
        }else{

            name= TATA;
            fromOwner=true;
            pictureId= R.drawable.ic_face_tata;
        }
        from=MyApplication.ins().getString(R.string.notset);
    }
    /***********************************************************
     *  Getters/Setters
     **********************************************************/
    public boolean isFromOwner() {
        return fromOwner;
    }

    public void setFromOwner(boolean fromOwner) {
        this.fromOwner = fromOwner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    /***********************************************************
     * Parcelabiilisation
     **********************************************************/

    protected MySmsMessage(Parcel in) {
        name = in.readString();
        message = in.readString();
        from = in.readString();
        pictureId = in.readInt();
        fromOwner = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(message);
        dest.writeString(from);
        dest.writeInt(pictureId);
        dest.writeByte((byte) (fromOwner ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MySmsMessage> CREATOR = new Parcelable.Creator<MySmsMessage>() {
        @Override
        public MySmsMessage createFromParcel(Parcel in) {
            return new MySmsMessage(in);
        }

        @Override
        public MySmsMessage[] newArray(int size) {
            return new MySmsMessage[size];
        }
    };
}