package com.example.nurislam.asyncexample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);
        txt = (TextView) findViewById(R.id.txt);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                AsyncTaskClass task = new AsyncTaskClass(MainActivity.this);
                task.execute();
            }
        });

        Button btnHan = (Button) findViewById(R.id.btn_han);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        txt.setText("THis Handler");
                    }
                },5000);
                AsyncTaskClass task = new AsyncTaskClass(MainActivity.this);
                task.execute();
            }
        });


    }
}
