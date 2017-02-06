package com.example.nurislam.fragmentprojects;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> dogs;

    public void completeList() {
        dogs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dogs.add("Dogs " + i);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        completeList();
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_new_adapter, parent, false);
        return new DogViewHolder(item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DogViewHolder) {
            ((DogViewHolder) holder).dogName.setText(dogs.get(position));
        }
    }

    class DogViewHolder extends RecyclerView.ViewHolder {
        TextView dogName;

        public DogViewHolder(View itemView) {
            super(itemView);
            dogName = (TextView) itemView.findViewById(R.id.dog_name);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

}
