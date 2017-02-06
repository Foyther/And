package com.example.nurislam.fragmentsproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstFragment ff = new FirstFragment();
        SecondFragment sf = new SecondFragment();
        FragmentTransaction ftrans;


        list = new LinkedList<>();
        list.add("Cat");
        list.add("Dog");
        list.add("User");

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        ListAdapter la = new ListAdapter(list);
        rv.setAdapter(la);

    }


}
