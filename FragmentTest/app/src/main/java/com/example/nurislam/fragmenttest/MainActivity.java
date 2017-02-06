package com.example.nurislam.fragmenttest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String FRAGMENT_KEY = "FSDKJFDSFDS;FJDS";
    public static final String FRAGMENT_KEY2 = "FSDKJFdsfsdfsdfsdfDSFDS;FJDS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListFragment fragment = new ListFragment();
        Bundle myBandle = new Bundle();
        myBandle.putString("text", "Welcome to KAzana");
        fragment.setArguments(myBandle);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().
                add(R.id.fragment_cont, fragment, FRAGMENT_KEY).
                commit();

        Button btn = (Button) findViewById(R.id.btnMain);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sh();
            }
        });

        if(findViewById(R.id.fragment_cont_2) != null){
            ListFragment fragment2 = new ListFragment();
            Bundle myBandle2 = new Bundle();
            myBandle2.putString("text", "Welcome to AndroidLAb");
            fragment2.setArguments(myBandle2);
            FragmentManager fm2 = getFragmentManager();
            fm2.beginTransaction().
                    add(R.id.fragment_cont_2, fragment2, FRAGMENT_KEY2).
                    commit();
        }
    }

    public void showToast(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void sh(){
        Fragment newFragment = getFragmentManager().findFragmentByTag(FRAGMENT_KEY);

        if(newFragment != null && newFragment.isAdded()){
            if(newFragment instanceof ListFragment){
                ((ListFragment) newFragment).changeText("Lolek bolek");
            }
        }
    }

}
