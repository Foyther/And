package com.example.nurislam.mvvm2;

import android.databinding.ObservableInt;
import android.os.Handler;
import android.view.View;

/**
 * Created by Nurislam on 04.03.2017.
 */

public class MainViewModel {
    public ObservableInt progressVisibility;
    public ObservableInt cententVisibility;

    public MainViewModel() {
        progressVisibility = new ObservableInt(View.GONE);
        cententVisibility = new ObservableInt(View.VISIBLE);
    }

    private void startLoading(){
        progressVisibility = new ObservableInt(View.VISIBLE);
        cententVisibility = new ObservableInt(View.GONE);
    }
    private void stopLoading(){
        progressVisibility = new ObservableInt(View.GONE);
        cententVisibility = new ObservableInt(View.VISIBLE);
    }

    public void generateClicked(){
        download();
    }

    private void download() {
        startLoading();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopLoading();
            }
        };
        (new Thread(run)).start();
    }
}
