package com.asystenttrenera_frontend.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String text = "test message";
        String phone = "123";
        Bundle extras = intent.getExtras();
        if (extras != null) {
            text = extras.getString("text");
            phone = extras.getString("phone");
        }
        Calendar calender = Calendar.getInstance();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, text + " " + calender.getTime(), null, null);
        Toast.makeText(context, "Message is sent", Toast.LENGTH_SHORT).show();

        Log.i("alarm", "phone: " + phone + " text: " + text);
    }
}
