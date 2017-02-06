package com.example.nurislam.asynctasksecond;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Nurislam on 10.12.2016.
 */

public class MyAsync extends AsyncTask<String, String, Integer> {//1 войд в он дуинбэкграунд, 2 войд в онПрогрессАпдейт, 3 войд в онПостЭксекут и возвращает в дуИнБэкграунд
    TaskListenner mTas;

    public MyAsync(TaskListenner mTas) {
        this.mTas = mTas;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        mTas.onTastFinish(integer == 0);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mTas.onTastStart();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

}
