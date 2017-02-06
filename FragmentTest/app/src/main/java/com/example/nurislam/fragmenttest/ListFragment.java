package com.example.nurislam.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Nurislam on 03.12.2016.
 */

public class ListFragment extends Fragment {
    TextView tv;
    Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        tv = (TextView) view.findViewById(R.id.tv);
        Bundle bundlefromActivity = getArguments();
        String text = bundlefromActivity.getString("text");
        changeText(text);
        ((MainActivity) getActivity()).showToast(("aahhah bd"));
    }

    public void changeText(String txt){
        if(tv != null){
            tv.setText(txt);
        }
    }
}
