package com.example.nurislam.list_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Lesson> lessons =  filllessons();

        rv = (RecyclerView) findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        LessonAdapter adapter = new LessonAdapter(lessons);
        rv.setAdapter(adapter);
    }

    private ArrayList<Lesson> filllessons(){
        ArrayList<Lesson> lessons = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Lesson lesson = new Lesson("room " + i+2, "time " + i + " sek", "lesson " + i , "prepod " + i);
            lessons.add(lesson);
        }
        return lessons;
    }
}
