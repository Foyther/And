package com.example.nurislam.asynctasksecond;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nurislam on 10.12.2016.
 */

public class NewFragment extends Fragment {

    TaskListenner taskListenner;
    MyAsync async;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        taskListenner = (TaskListenner) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        taskListenner = (TaskListenner) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate()
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void startAsync(){
        async = new MyAsync(taskListenner);
        async.execute();
    }
}
