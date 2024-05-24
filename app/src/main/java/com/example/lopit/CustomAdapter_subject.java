package com.example.lopit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.SubjectViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList subject_name, subject_teacher, subject_color, subject_note;

    CustomAdapter(Activity activity, Context context,  ArrayList subject_name, ArrayList subject_teacher,
                   ArrayList subject_color, ArrayList subject_note) {
        this.activity = activity;
        this.context = context;
        this.subject_name = subject_name;
        this.subject_teacher = subject_teacher;
        this.subject_color = subject_color;
        this.subject_note = subject_note;
    }
        @NonNull
        @Override
        public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.item, parent, false);
            return new SubjectViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull SubjectViewHolder holder, final int position) {
            holder.subject_name_txt.setText(String.valueOf(subject_name.get(position)));
            holder.subject_teacher_txt.setText(String.valueOf(subject_teacher.get(position)));

            holder.mainLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Intent i = new Intent(context, update.class);
                   i.putExtra("name", String.valueOf(subject_name.get(position)));
                  i.putExtra("teacher", String.valueOf(subject_teacher.get(position)));
                 i.putExtra("color", String.valueOf(subject_color.get(position)));
                 i.putExtra("note", String.valueOf(subject_note.get(position)));
                 activity.startActivityForResult(i,1);
                }
            });
        }

        @Override
       public int getItemCount() {
          return subject_name.size();
        }

        class SubjectViewHolder extends RecyclerView.ViewHolder {

            TextView subject_name_txt, subject_teacher_txt;
            CardView mainLayout;

            SubjectViewHolder(@NonNull View itemView) {
                super(itemView);
                subject_name_txt = itemView.findViewById(R.id.subject_name_txt);
                mainLayout = itemView.findViewById(R.id.mainLayout);
            }
        }}



