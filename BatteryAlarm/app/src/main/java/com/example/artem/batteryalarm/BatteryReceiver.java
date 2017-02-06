package com.example.artem.batteryalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.view.View;

/**
 * Created by Artem on 19.08.2016.
 */
public class BatteryReceiver extends BroadcastReceiver {

    private float level;
    private boolean isCharging;
    private boolean start = false;

    @Override
    public void onReceive(Context c, Intent i) {
        start = true;
        level = 100 * (((float) i.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) / ((float) i.getIntExtra(BatteryManager.EXTRA_SCALE, -1)));
        Log.d(MainActivity.LOG_TAG, "battery level update = " + level);
        int status = i.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        if ((status == BatteryManager.BATTERY_STATUS_CHARGING) || (status == BatteryManager.BATTERY_STATUS_FULL)){
            isCharging = true;
        }
        else{
            isCharging = false;
        }
    }

    public boolean isCharging() {
        //Костыль чтоб работало
        return (isCharging || !start);
    }

    public float getLevel() {
        return level;
    }
}
