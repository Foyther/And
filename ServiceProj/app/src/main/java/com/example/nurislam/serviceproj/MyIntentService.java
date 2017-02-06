package com.example.nurislam.serviceproj;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Nurislam on 03.12.2016.
 */

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("Artem");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("Bla bla", "On handle intent");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent workResult = new Intent();
    }
}
