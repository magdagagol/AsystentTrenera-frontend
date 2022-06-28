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
        Long a = 1L;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            text = extras.getString("text");
            phone = extras.getString("phone");
            a = extras.getLong("time");

        }
        Calendar calender = Calendar.getInstance();
        Calendar calender2 = Calendar.getInstance();
        calender2.setTimeInMillis(a);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, text, null, null);
    }
}