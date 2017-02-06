package com.example.nurislam.kdproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nurislam on 19.11.2016.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    List<Date> dates;

    public ListAdapter(List<Date> dates) {
        this.dates = dates;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_text_view, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        holder.name.setText(dates.get(position).getName());
        holder.text.setText(dates.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    class ListHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView text;


        public ListHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.full_txt);
            name = (TextView) itemView.findViewById(R.id.name_txt);
        }
    }
}
