package com.example.artem.batteryalarm;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String SAVED_SWITCH = "switch";
    public static final String SHARED_PREFERENCES = "savedata";
    public static final String LOG_TAG = "myDebug";
    public static final String LOG_TAG_SHARED = "SP";
    public static final String PARAM_SOUND_STOP = "soundstop";
        public static final int VALUE_SOUND_STOP = 666;
    public static final int REQUEST_ALARM_START = 1;
    public static final String PARAM_PINTENT = "PendingIntent";
    public static final String PARAM_SWITCH_SOUND = "Switch";
    public static final String PARAM_ALARM_LEVEL = "AlarmLevel";
    public static final String PARAM_WAKE_ACTIVITY_RESUME = "WakeActivityResume";
    public static final String PARAM_BATTERY_LEVEL = "BatteryLevel";
    public static final String PARAM_IS_SOUND_PLAY = "IsSoundPlay";
    public static final String SAVED_ALARM_LEVEL = "AlarmLevel";
    private final int MENU_EXIT = 0;
    private final int MENU_ABOUT = 1;


    private TextView tvBattery;
    private TextView tvBatteryLevelAlarm;
    private TextView tvWarning;
    private TextView tvTime;
    private ProgressBar pbBattery;
    private Switch swSound;
    private SeekBar sbBattery;
    private BroadcastReceiver receiver;
    private Intent intent;
    private boolean isCharging;
    private SharedPreferences spBatteryLevelAlarm;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_ABOUT, 1, R.string.about);
        menu.add(0, MENU_EXIT, 2, R.string.exit);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_EXIT:
                finish();
                System.exit(0);
                break;
            case MENU_ABOUT:
                Intent tempIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(tempIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // A U D I O    S E T T I N G S
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setSpeakerphoneOn(true);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        //find view by id
        pbBattery = (ProgressBar) findViewById(R.id.pb_battery);
        tvBattery = (TextView) findViewById(R.id.tv_battery);
        swSound = (Switch) findViewById(R.id.sw_sound);
        sbBattery = (SeekBar) findViewById(R.id.sb_battery);
        tvBatteryLevelAlarm = (TextView) findViewById(R.id.tv_battery_level_alarm);
        tvWarning = (TextView) findViewById(R.id.tv_warning);


        spBatteryLevelAlarm = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        sbBattery.setProgress(spBatteryLevelAlarm.getInt(SAVED_ALARM_LEVEL, 100));
        swSound.setChecked(readBoolean(SHARED_PREFERENCES, SAVED_SWITCH));

        // intent initilization

        intent = new Intent(this, BatteryService.class);
        intent.putExtra(PARAM_ALARM_LEVEL, sbBattery.getProgress());
        intent.putExtra(PARAM_SWITCH_SOUND, swSound.isChecked());

        //T E X T   V I E W
        tvBatteryLevelAlarm.setText(R.string.battery_level_alarm);
        tvBatteryLevelAlarm.append(" " + sbBattery.getProgress() + "%");

        tvBattery.setText(R.string.battery_level);


        //R E C I E V E R for text view and progress bar
        isCharging = false;
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent i) {
                float level = 100 * (((float) i.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) / ((float) i.getIntExtra(BatteryManager.EXTRA_SCALE, -1)));

                tvBattery.setText(R.string.battery_level);
                tvBattery.append(" " + (int)Math.round(level) + "%");
                pbBattery.setProgress(Math.round(level));
                int status = i.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                if ((status == BatteryManager.BATTERY_STATUS_CHARGING) || (status == BatteryManager.BATTERY_STATUS_FULL)){
                    tvWarning.setVisibility(View.INVISIBLE);
                    MainActivity.this.isCharging = true;
                    startService(MainActivity.this.intent);
                }
                else{
                    MainActivity.this.isCharging = false;
                    tvWarning.setVisibility(View.VISIBLE);
                }

            }
        };
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        // L I S T E N E R S
        swSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeBoolean(SHARED_PREFERENCES, SAVED_SWITCH, swSound.isChecked());
                startService(intent);
            }
        });
        sbBattery.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                tvBatteryLevelAlarm.setText(R.string.battery_level_alarm);
                tvBatteryLevelAlarm.append(" " + i + "%");
                SharedPreferences.Editor edit = spBatteryLevelAlarm.edit();
                edit.putInt(SAVED_ALARM_LEVEL, i);
                edit.commit();
                startService(intent);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // S T A R T   S E R V I C E
        startService(intent);
        Log.d(LOG_TAG, "Main activity: Battery service started");

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        swSound.setChecked(readBoolean(SHARED_PREFERENCES, SAVED_SWITCH));
        Log.d(LOG_TAG_SHARED, "Main activity: onResume: swSound.isChecked = " + swSound.isChecked());
        startService(intent);
    }

    private boolean readBoolean(String preferencesName, String key){
        SharedPreferences sp = getSharedPreferences(preferencesName, MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
    private boolean writeBoolean(String preferencesName, String key, boolean value){
        SharedPreferences sp = getSharedPreferences(preferencesName, MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean(key, value);
        e.commit();
        return true;
    }

}
