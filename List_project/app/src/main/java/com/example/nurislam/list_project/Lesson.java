package com.example.nurislam.list_project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nurislam on 17.08.2016.
 */
public class Lesson {
    private String mRoom, mTime,mName, mPrepod;

    public Lesson(String mRoom, String mTime, String mName, String mPrepod) {
        this.mRoom = mRoom;
        this.mTime = mTime;
        this.mName = mName;
        this.mPrepod = mPrepod;
    }

    public String getmRoom() {
        return mRoom;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmName() {
        return mName;
    }

    public String getmPrepod() {
        return mPrepod;
    }

    public void setmRoom(String mRoom) {
        this.mRoom = mRoom;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmPrepod(String mPrepod) {
        this.mName = mPrepod;
    }
}
