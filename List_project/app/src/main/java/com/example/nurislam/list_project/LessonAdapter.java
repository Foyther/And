package com.example.nurislam.list_project;

import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Nurislam on 17.08.2016.
 */
public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder>{
    private ArrayList<Lesson> mLessons;

    public LessonAdapter(ArrayList<Lesson> lessons){
        mLessons = lessons;
    }

    @Override
    public LessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_lessons,
                parent,
                false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LessonViewHolder holder, int position) {
        final Lesson lesson = mLessons.get(position);
        holder.tvName.setText(lesson.getmName());
        holder.tvTime.setText(lesson.getmTime());
        holder.tvRoom.setText(lesson.getmRoom());
        holder.tvPrepod.setText(lesson.getmPrepod());
        holder.ivPicture.setImageResource(R.drawable.tth);

        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(view.getContext(),
                        lesson.getmName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLessons.size();
    }

    public class LessonViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvTime;
        TextView tvRoom;
        TextView tvPrepod;
        ImageView ivPicture;
        View view;

        public LessonViewHolder(View itemView) {
            super(itemView);
            ivPicture = (ImageView) itemView.findViewById(R.id.iv);
            view = itemView;
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvRoom = (TextView) itemView.findViewById(R.id.tv_room);
            tvPrepod = (TextView) itemView.findViewById(R.id.tv_prepod);
        }
    }
}
