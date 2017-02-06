package com.example.nurislam.asyncexample;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Nurislam on 29.10.2016.
 */

public class AsyncTaskClass extends AsyncTask<Void, Void, String> {
    private Context context;

    public AsyncTaskClass (Context context){
        this.context = context;
    }

    public void onPreExecute(){

        super.onPreExecute();
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

    @Override
    protected void onPostExecute(String s){
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        super.onPostExecute(s);
    }
}
