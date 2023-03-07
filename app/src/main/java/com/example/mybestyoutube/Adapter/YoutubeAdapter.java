package com.example.mybestyoutube.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybestyoutube.Model.YoutubeVideo;
import com.example.mybestyoutube.R;
import com.example.mybestyoutube.Vue.DetailActivity;


import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeAdapterViewHolder>{


    Context context;
    private List<YoutubeVideo> youtubeVideos;


    public YoutubeAdapter(List<YoutubeVideo> youtubeVideos, Context context){
        this.youtubeVideos = youtubeVideos;
        this.context = context;


    }


    public class YoutubeAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitre;
        public  TextView tvDescription;
        public CardView cardView;
        public YoutubeAdapterViewHolder(View itemView){
            super(itemView);

            tvTitre = itemView.findViewById(R.id.tvTitre);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            cardView = itemView.findViewById(R.id.carderView);

        }

    }



    @Override
    public YoutubeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.youtube_item,parent,false);
        YoutubeAdapterViewHolder youtubeAdapterViewHolder = new YoutubeAdapterViewHolder(view);

        return youtubeAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(YoutubeAdapterViewHolder holder, int position) {

        YoutubeVideo youtubeVideo = youtubeVideos.get(position);
        holder.tvTitre.setText(youtubeVideo.getTitre());
        holder.tvDescription.setText(youtubeVideo.getDescription());



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,"clicked " + position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("titre", youtubeVideos.get(position).getTitre());
                intent.putExtra("description", youtubeVideos.get(position).getDescription());
                intent.putExtra("url",youtubeVideos.get(position).getUrl());
                intent.putExtra("categorie", youtubeVideos.get(position).getCategorie());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return youtubeVideos.size();
    }

}
