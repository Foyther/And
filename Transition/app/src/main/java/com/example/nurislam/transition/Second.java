package com.example.nurislam.transition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Second extends AppCompatActivity {
    private TextView txt_num,txt_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txt_num = (TextView) findViewById(R.id.num);
        txt_text = (TextView) findViewById(R.id.copy_text);
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        String num = intent.getStringExtra("number");
        txt_num.setText(num);
        txt_text.setText(text);
    }
}
