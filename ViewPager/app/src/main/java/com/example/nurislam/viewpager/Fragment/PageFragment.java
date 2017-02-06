package com.example.nurislam.viewpager.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nurislam.viewpager.R;

import java.util.Random;

/**
 * Created by Nurislam on 06.02.2017.
 */

public class PageFragment extends Fragment {
    private static final String PAGE_NUMBER = "page_number";
    private int pageNum;
    private int backColor;

    public static PageFragment newInstance(int page){
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageNum = getArguments().getInt(PAGE_NUMBER);
        Random random = new Random();
        backColor = Color.argb(40, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, null);
        TextView tv = (TextView) view.findViewById(R.id.txt);
        tv.setText("Page is " + pageNum);
        tv.setBackgroundColor(backColor);
        return view;
    }


}
