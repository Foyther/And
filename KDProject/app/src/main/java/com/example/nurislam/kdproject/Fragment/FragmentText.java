package com.example.nurislam.kdproject.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nurislam.kdproject.Date;
import com.example.nurislam.kdproject.ListAdapter;
import com.example.nurislam.kdproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nurislam on 19.11.2016.
 */

public class FragmentText extends Fragment {
    List<Date> dates = new ArrayList<>();
    RecyclerView rv;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        for (int i = 0; i < 15; i++) {
            dates.add(new Date("Name" + i, "Text news, please rewrite this! This number text is " + i));
        }

        rv = (RecyclerView) view.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        ListAdapter adapter = new ListAdapter(dates);
        rv.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        return view;
    }
}
