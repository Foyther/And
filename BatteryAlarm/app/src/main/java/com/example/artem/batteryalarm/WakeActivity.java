package com.example.artem.batteryalarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WakeActivity extends AppCompatActivity {
    private Button btnWakeStop;
    private TextView tvWakeBattery;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake);

        btnWakeStop = (Button) findViewById(R.id.btn_wake_stop);
        tvWakeBattery = (TextView) findViewById(R.id.tv_wake_battery);
        tvWakeBattery.setText(R.string.the_battery_has_reached);

        Intent intent = getIntent();
        int level = intent.getIntExtra(MainActivity.PARAM_BATTERY_LEVEL, 0);

        tvWakeBattery.append(" " + Integer.toString(level) + "%");

        btnWakeStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WakeActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        serviceIntent = new Intent(this, BatteryService.class);
        serviceIntent.putExtra(MainActivity.PARAM_SOUND_STOP, MainActivity.VALUE_SOUND_STOP);
        startService(serviceIntent);
    }
}
