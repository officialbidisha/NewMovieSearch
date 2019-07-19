package com.example.moviesearchingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    ArrayList<MovieShortDetails> movie= new ArrayList<>();
    private Context mContext;

    public SearchAdapter(ArrayList<MovieShortDetails> movie, Context context) {
        this.movie = movie;
        this.mContext= mContext;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem1,parent,false);
        return new SearchAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        MovieShortDetails imovie = movie.get(position);
      holder.tv.setText(imovie.getTitle()+'\n'+imovie.getDescription());
      try{
      holder.imageView.setImageBitmap(imovie.getPoster());
      }catch(Exception e){
          e.printStackTrace();
      }
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv= itemView.findViewById(R.id.movie_details);
            imageView=itemView.findViewById(R.id.posterImage);

        }
    }
}
