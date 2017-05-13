package com.example.nurislam.norrisprojrxjava;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;

import java.io.IOException;
import java.util.Observer;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private Button refreshJoke;
    private String FRAGMENT_KEY = "SFDSDGGFGFGDG";
    private MainFragment jokeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        if(getFragmentManager().findFragmentByTag(FRAGMENT_KEY) == null) {
            jokeFragment = new MainFragment();
            fm.beginTransaction().
                    replace(R.id.fragmentFirst, jokeFragment, FRAGMENT_KEY).
                    commit();
        }
        else jokeFragment = (MainFragment) getFragmentManager().findFragmentByTag(FRAGMENT_KEY);
        refreshJoke = (Button) findViewById(R.id.button);

        Observable<Void> clicker = RxView.clicks(refreshJoke);
        clicker.subscribe(aVoid -> {
                    start();
                });
    }

    public void start(){
        if(jokeFragment != null && jokeFragment.isAdded()){
            if(jokeFragment instanceof MainFragment) {
                try {
                    ((MainFragment) jokeFragment).getRandomJoke();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
