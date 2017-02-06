package com.example.nurislam.lessonwork;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.ArrayList;

/**
 * Created by Nurislam on 08.10.2016.
 */

public class NewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<String> temp;

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_design, parent, false);
                return new NewViewHolder(view);
            case 1:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_design2, parent, false);
                return new NewViewHolder(view2);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_design2, parent, false);
        return new NewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class NewViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public NewViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(final int drawableRes) {

            iv.setImageResource(drawableRes);
        }
    }
}
