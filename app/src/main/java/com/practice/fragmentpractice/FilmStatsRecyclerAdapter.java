package com.practice.fragmentpractice;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FilmStatsRecyclerAdapter extends RecyclerView.Adapter<FilmStatsRecyclerAdapter.ViewHolder> {


    private ArrayList<FilmStats> list;

    public FilmStatsRecyclerAdapter(ArrayList<FilmStats> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.film_stats_view, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FilmStats stats = list.get(i);
        viewHolder.name.setText(stats.getName());
        //viewHolder.poster.setImageURI(Uri.parse(list.get(i).getPosterImgUrl()));
        viewHolder.genres.setText("Genres:");
        for(int g=0;g<stats.getGenres().length;g++){
            if(g == stats.getGenres().length-1){
                viewHolder.genres.append(" " + stats.getGenres()[g].toString());
                continue;
            }
            viewHolder.genres.append(" " + stats.getGenres()[g].toString() + " |");
        }

        viewHolder.runTime.setText("Run time: " + stats.getRunTime());
        viewHolder.ageRating.setText("Age rating: " + stats.getAgeRating());

        new BitmapLoader(viewHolder.poster).execute(stats.getPosterImgUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView runTime;
        TextView ageRating;
        TextView genres;
        ImageView poster;
        AppCompatButton btn_more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.filmName);
            runTime = itemView.findViewById(R.id.tv_RunTime);
            ageRating = itemView.findViewById(R.id.tv_AgeRating);
            genres = itemView.findViewById(R.id.tv_Genres);

            poster = itemView.findViewById(R.id.posterView);
            btn_more = itemView.findViewById(R.id.moreInfo);


        }
    }
}
