package com.example.mybestyoutube.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mybestyoutube.Model.YoutubeVideo;
import com.example.mybestyoutube.R;


import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeAdapterViewHolder>{



    private List<YoutubeVideo> youtubeVideos;


    public class YoutubeAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitre;
        public  TextView tvDescription;


        public YoutubeAdapterViewHolder(View itemView){
            super(itemView);

            tvTitre = itemView.findViewById(R.id.tvTitre);
            tvDescription = itemView.findViewById(R.id.tvDescription);

        }

    }

    public YoutubeAdapter(List<YoutubeVideo> youtubeVideos){
        this.youtubeVideos = youtubeVideos;


    }

    @Override
    public YoutubeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_item,parent,false);
        return new YoutubeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeAdapterViewHolder holder, int position) {

        YoutubeVideo youtubeVideo = youtubeVideos.get(position);
        holder.tvTitre.setText(youtubeVideo.getTitre());
        holder.tvDescription.setText(youtubeVideo.getDescription());

    }

    @Override
    public int getItemCount() {
        return youtubeVideos.size();
    }

}
