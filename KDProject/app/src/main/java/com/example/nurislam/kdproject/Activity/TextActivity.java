package com.example.nurislam.kdproject.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nurislam.kdproject.Date;
import com.example.nurislam.kdproject.Fragment.FragmentShowText;
import com.example.nurislam.kdproject.Fragment.FragmentText;
import com.example.nurislam.kdproject.ListAdapter;
import com.example.nurislam.kdproject.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TextActivity extends AppCompatActivity {

    private static final String FRLISTKEY = "keylistfragment";
    private static final String FRCONTAINERKEY = "keycontainerfragment";
    List<Date> dates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        Intent intent = getIntent();

        FragmentText fragmentText = new FragmentText();
        getFragmentManager().beginTransaction()
                .replace(R.id.list, fragmentText, FRLISTKEY)
                .commit();


        if(findViewById(R.id.container) != null){
            FragmentShowText fragmentShowText = new FragmentShowText();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, fragmentShowText, FRCONTAINERKEY);
        }

    }
}
