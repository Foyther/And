package com.therishka.androidlab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * @author Rishad Mustafaev
 */

public class ColorActivity extends AppCompatActivity {

    RelativeLayout rootLayout;
    EditText et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        rootLayout = (RelativeLayout) findViewById(R.id.root_layout);
        et = (EditText) findViewById(R.id.texttakoy);
        Button btn = (Button) findViewById(R.id.apply);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int clr = Color.parseColor(et.getText().toString());
                    rootLayout.setBackgroundColor(clr);
                }
                catch(Exception ex){
                    Toast.makeText(ColorActivity.this, "Введи нормально!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
    TODO:
    find and set click listeners to EditText and Button. When
    Button clicked - call changeRootColor() method and pass there
    text from EditText widget. Be careful: text must be valid!
    Not empty and in #FFFFFF format. If u pass non-valid text
    the app will crash.
    Don't forget to call showError() method in case of non-valid text.

     */

    private void changeRootColor(@NonNull String color) {
        int colorInt = Color.parseColor(color);
        rootLayout.setBackgroundColor(colorInt);
    }

    private void showError(@Nullable String errorMesage) {
        Toast.makeText(this, errorMesage, Toast.LENGTH_SHORT).show();
    }
}
