package com.example.modul2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapater extends RecyclerView.Adapter<CustomAdapater.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList id, judul, tahun, umur, rating, genre;


    CustomAdapater(Activity activity,
                   Context context,
                   ArrayList id,
                   ArrayList judul,
                   ArrayList tahun,
                   ArrayList umur,
                   ArrayList rating,
                   ArrayList genre){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.judul = judul;
        this.tahun = tahun;
        this.umur = umur;
        this.rating = rating;
        this.genre = genre;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(String.valueOf(id.get(position)));
        holder.judul.setText(String.valueOf(judul.get(position)));
        holder.tahun.setText(String.valueOf(tahun.get(position)));
        holder.umur.setText(String.valueOf(umur.get(position)));
        holder.rating.setText(String.valueOf(rating.get(position)));
        holder.genre.setText(String.valueOf(genre.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id1", String.valueOf(id.get(position)));
                intent.putExtra("judul1", String.valueOf(judul.get(position)));
                intent.putExtra("tahun1", String.valueOf(tahun.get(position)));
                intent.putExtra("umur1", String.valueOf(umur.get(position)));
                intent.putExtra("rating1", String.valueOf(rating.get(position)));
                intent.putExtra("genre1", String.valueOf(genre.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {

        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id, judul, tahun, umur, rating, genre;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            judul = itemView.findViewById(R.id.Judulrow);
            tahun = itemView.findViewById(R.id.Tahunrow);
            umur = itemView.findViewById(R.id.Umurrow);
            rating = itemView.findViewById(R.id.Ratingrow);
            genre = itemView.findViewById(R.id.Genrerow);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
