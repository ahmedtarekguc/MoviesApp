package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie>movies = new ArrayList<>();
    private OnItemClickListener listener;
    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new MovieHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie currentmovie = movies.get(position);
        holder.TextViewtitle.setText(currentmovie.getName());
        holder.TextViewProductionYear.setText(String.valueOf(currentmovie.getProductionyear()));
        holder.TextViewDescription.setText(currentmovie.getDescription());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovie(List<Movie>movie){
        this.movies=movie;
        notifyDataSetChanged();

    }

    public Movie getMovieAt(int position){
        return movies.get(position);
    }

    class MovieHolder extends RecyclerView.ViewHolder{
        private TextView TextViewtitle;
        private TextView TextViewProductionYear;
        private TextView TextViewDescription;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            TextViewtitle = itemView.findViewById(R.id.name_text_view);
            TextViewDescription = itemView.findViewById(R.id.description_text_view);
            TextViewProductionYear = itemView.findViewById(R.id.productionyear_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(listener!=null && pos !=RecyclerView.NO_POSITION){
                        listener.onItemClick(movies.get(pos));
                    }
                }
            });

        }


    }

    public interface OnItemClickListener{
        void onItemClick(Movie movie);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
