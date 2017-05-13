package com.example.nurislam.norrisprojrxjava;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nurislam.norrisprojrxjava.API.POJO.ChuckNorrisApi;
import com.example.nurislam.norrisprojrxjava.API.POJO.Value;
import com.example.nurislam.norrisprojrxjava.API.Service.ChuckService;

import java.io.IOException;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nurislam on 13.05.2017.
 */

public class MainFragment extends Fragment implements FragmentListenner{
    private TextView tv;
    private Subscription sub;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.joke, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        tv = (TextView) view.findViewById(R.id.textJoke);
        if (savedInstanceState != null) {
            tv.setText(savedInstanceState.getString("joke"));
        } else
            changeText("This is working");
    }

    public void getRandomJoke() throws IOException {
        if(sub!= null && !sub.isUnsubscribed()){
            sub.unsubscribe();
        }
        this.sub = ChuckService.getValue()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ChuckNorrisApi>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ChuckNorrisApi chuckNorrisApi) {
                        changeText(chuckNorrisApi.getValue().getJoke());
                    }
                });
    }

    public void changeText(String txt) {
        if (tv != null) {
            if (txt == null) {
                tv.setText("ERROR");
            } else
                tv.setText(txt);
        }
    }

    public TextView getTv(){
        return this.tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("joke", (String) tv.getText());
    }

    @Override
    public void onTaskFinish() {

    }

    @Override
    public boolean onTaskStart(String start) {
        changeText(start);
        return true;
    }
}
