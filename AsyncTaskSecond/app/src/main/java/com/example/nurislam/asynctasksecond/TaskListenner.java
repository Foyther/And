package com.example.nurislam.asynctasksecond;

/**
 * Created by Nurislam on 10.12.2016.
 */

public interface TaskListenner {
    public void onTastStart();

    public void onTastFinish(boolean success);

    public void onTastProgress();


}
