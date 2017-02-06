package com.example.nurislam.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_plus, btn_minus, btn_multi, btn_div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multi = (Button) findViewById(R.id.btn_multi);
        btn_div = (Button) findViewById(R.id.btn_div);

        btn_plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

            }
        });

        btn_multi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

            }
        });
    }
}
