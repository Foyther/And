package com.example.nurislam.fragmentsproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nurislam on 18.11.2016.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    private List<String> list;

    public ListAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }


    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0: view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_activity_for_list, parent, false);
                break;
            case 1: view = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_activity_for_list, parent, false);
                break;
            default: view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_activity_for_list, parent, false);
                break;
        }

        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ListHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        View holderView;
        TextView tv;

        public ListHolder(View itemView) {
            super(itemView);
            holderView = itemView;
            tv = (TextView) itemView.findViewById(R.id.textForList);
        }
    }
}
