package com.example.nurislam.twowindow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView tv_Text;
    private TextView tv_Number;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv_Number = (TextView) findViewById(R.id.tv_num);
        tv_Text = (TextView) findViewById(R.id.tv_text);
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        String number = intent.getStringExtra("number");
        tv_Text.setText(text);
        tv_Number.setText(number);
    }
}

