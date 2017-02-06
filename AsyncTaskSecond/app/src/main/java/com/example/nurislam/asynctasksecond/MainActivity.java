package com.example.nurislam.asynctasksecond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TaskListenner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewFragment fragment = getAsyncContainer();
        Button button = (Button) findViewById(R.id.btnAsync);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fragment.startAsync();
    }

    private  NewFragment getAsyncContainer(){
        if(getFragmentManager().
                findFragmentByTag(NewFragment.class.getName()) == null){
            getFragmentManager().beginTransaction().
                    add(new NewFragment(),
                            NewFragment.class.getName())
                    .commit();
        }

        NewFragment fragment = (NewFragment) getFragmentManager().
                findFragmentByTag(NewFragment.class.getName());
        return fragment;
    }

    @Override
    public void onTastStart() {

    }

    @Override
    public void onTastFinish(boolean success) {
        Toast.makeText(this,  success ? "EEEEEEE" : "Ssadada", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTastProgress() {

    }
}
