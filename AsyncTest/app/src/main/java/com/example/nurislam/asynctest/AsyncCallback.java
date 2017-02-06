package com.example.nurislam.asynctest;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Nurislam on 12.11.2016.
 */
public class AsyncCallback extends AsyncTask<Void, Void, String>{
    private Context context;

    public AsyncCallback(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "that's ok";
    }


}
