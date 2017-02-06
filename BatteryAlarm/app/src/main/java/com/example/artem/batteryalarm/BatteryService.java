package com.example.artem.batteryalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BatteryService extends Service{

    private BatteryReceiver receiver;
    private Intent intent;
    private MyUpdater updater;
    private Thread updaterThread;
    private PendingIntent pi;
    private SharedPreferences sp;
    MediaPlayer mediaPlayer;

    public class MyUpdater implements Runnable{
        @Override
        public void run() {
            Log.d(MainActivity.LOG_TAG, "Battery service: updater: updater of battery sevice started");
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    if (!receiver.isCharging()){
                        stopSelf();
                    }

                    float level = receiver.getLevel();
                    float alarmLevel = sp.getInt(MainActivity.SAVED_ALARM_LEVEL, -1);
                    boolean isSwitch = sp.getBoolean(MainActivity.SAVED_SWITCH, false);

                    if (isSwitch){
                        if (Math.floor(level) >= Math.floor(alarmLevel)) {
                            //TimeUnit.SECONDS.sleep(15);
                            Log.d(MainActivity.LOG_TAG, "Battery service: updater: level = " + level + "; alarmLevel = " + alarmLevel);
                            Log.d(MainActivity.LOG_TAG, "Battery service: level >= alarmLevel");

                            SharedPreferences.Editor edit = sp.edit();
                            edit.putBoolean(MainActivity.SAVED_SWITCH, false);
                            edit.commit();

                            Intent tempIntent = new Intent("com.example.artem.batteryalarm.wakeactivity");
                            tempIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            tempIntent.putExtra(MainActivity.PARAM_BATTERY_LEVEL, (int)Math.round(level));

                            PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                            PowerManager.WakeLock wl = manager.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "Your Tag");
                            wl.setReferenceCounted(true);
                            wl.acquire();

                            playSound();
                            startActivity(tempIntent);

                            wl.release();

                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BatteryService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (receiver == null) {
            receiver = new BatteryReceiver();
            Log.d(MainActivity.LOG_TAG, "Battery service: receiver initilizated");
            registerReceiver(receiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            Log.d(MainActivity.LOG_TAG, "Battery service: receiver registered");

        }
        sp = getApplicationContext().getSharedPreferences(MainActivity.SHARED_PREFERENCES, MODE_PRIVATE);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        this.intent = intent;
        if (updater == null) {
            updater = new MyUpdater();
            updaterThread = new Thread(updater);
            updaterThread.start();
        }
        if(intent != null){
            if (intent.getIntExtra(MainActivity.PARAM_SOUND_STOP, 0) == MainActivity.VALUE_SOUND_STOP){
                intent.putExtra(MainActivity.PARAM_SOUND_STOP, 0);
                stopSound();
                stopSelf();
            }
        }
        return START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        Log.d(MainActivity.LOG_TAG, "Battery service: receiver unregistered");
        Log.d(MainActivity.LOG_TAG, "Battery service: DESTROYED");
        stopSound();
        realeaseMP();
        super.onDestroy();

    }

    @Override
    public void onTaskRemoved(Intent rootIntent){
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());

        PendingIntent restartServicePendingIntent = PendingIntent.getService(getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmService.set(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 1000,
                restartServicePendingIntent);

        super.onTaskRemoved(rootIntent);
    }
    // S O U N D
    private void playSound(){
        try{
            Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getApplicationContext(), ringtoneUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Log.d(MainActivity.LOG_TAG, "Battery Service: Sound playing");
        }
        catch(Exception e){
            Log.d(MainActivity.LOG_TAG, "Battery Service: Error: " + e.getMessage());
            e.printStackTrace();
        }

    }
    private void stopSound(){
        if (mediaPlayer != null){
            if (mediaPlayer.isPlaying() == true){
                mediaPlayer.stop();
                Log.d(MainActivity.LOG_TAG, "Battery Service: Sound stoped");
            }
        }
    }
    private void realeaseMP(){
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
