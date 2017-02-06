package com.therishka.androidlab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class KittenActivity extends AppCompatActivity {
    Button btn_name;
    TextView tv_name;
    EditText et_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitten);

        btn_name = (Button) findViewById(R.id.button);
        tv_name = (TextView) findViewById(R.id.cat_name);
        et_text = (EditText) findViewById(R.id.cat_name_input);
        btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_name.setText(et_text.getText().toString());
            }
        });
    }
}
