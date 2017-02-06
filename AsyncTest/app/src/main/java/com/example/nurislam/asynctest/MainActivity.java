package com.example.nurislam.asynctest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);
        text = (TextView) findViewById(R.id.txt);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                text.setText("Yeahhh!");
                AsyncCallback callback = new AsyncCallback(MainActivity.this);
                callback.execute();
            }
        });

    }
}
