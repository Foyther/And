package com.example.nurislam.twowindow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_jump;
    EditText et_enter_text;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    String num = "0";

    protected void onRestart() {
        super.onRestart();
        num="0";
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_1 = (Button) findViewById(R.id.btn_first);
        btn_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                num = "1";
            }
        });
        btn_2 = (Button) findViewById(R.id.btn_second);
        btn_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                num = "2";
            }
        });
        btn_3 = (Button) findViewById(R.id.btn_third);
        btn_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                num = "3";
            }
        });

        et_enter_text= (EditText) findViewById(R.id.et_enter_text);
        btn_jump = (Button) findViewById(R.id.et_name);
        btn_jump.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String text = et_enter_text.getText().toString();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("text", text);
                intent.putExtra("number", num);
                startActivity(intent);
            }
        });
    }
}
