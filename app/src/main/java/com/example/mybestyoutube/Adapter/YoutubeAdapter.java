package com.example.mybestyoutube.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybestyoutube.Dao.YoutubeDao;
import com.example.mybestyoutube.Model.YoutubeVideo;
import com.example.mybestyoutube.R;
import com.example.mybestyoutube.Vue.DetailActivity;
import com.example.mybestyoutube.Vue.MainActivity;
import com.example.mybestyoutube.Vue.UpdateYoutubeActivity;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeAdapterViewHolder> implements Filterable {


    Context context;
    private List<YoutubeVideo> youtubeVideos;
    
    private List<YoutubeVideo> getYoutubeVideosListFiltered;


    public YoutubeAdapter(List<YoutubeVideo> youtubeVideos, Context context){
        this.youtubeVideos = youtubeVideos;
        this.context = context;
        this.getYoutubeVideosListFiltered = youtubeVideos;


    }


    public class YoutubeAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitre;
        public  TextView tvDescription;
        public CardView cardView;
        public ImageView mMenus;
        public YoutubeAdapterViewHolder(View itemView){
            super(itemView);

            tvTitre = itemView.findViewById(R.id.tvTitre);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            cardView = itemView.findViewById(R.id.carderView);
            mMenus = itemView.findViewById(R.id.mMenus);


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

        int itemPosition = holder.getAbsoluteAdapterPosition();

        YoutubeVideo youtubeVideo = youtubeVideos.get(itemPosition);
        holder.tvTitre.setText(youtubeVideo.getTitre());
        holder.tvDescription.setText(youtubeVideo.getDescription());



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("titre", youtubeVideos.get(itemPosition).getTitre());
                intent.putExtra("description", youtubeVideos.get(itemPosition).getDescription());
                intent.putExtra("url",youtubeVideos.get(itemPosition).getUrl());
                intent.putExtra("categorie", youtubeVideos.get(itemPosition).getCategorie());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });



        // suppression et modification
        holder.mMenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenus = new PopupMenu(context, view);
                popupMenus.inflate(R.menu.show_menu);
                popupMenus.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edtiText:


                                Intent intent = new Intent(context, UpdateYoutubeActivity.class);
                                intent.putExtra("editYoutubeVideo",youtubeVideo);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                return true;
                            case R.id.delete:

                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                                builder.setTitle("Confirmation !!!");
                                builder.setMessage("Are you sure to deleted " + youtubeVideo.getTitre() + " ?");
                                builder.setIcon(R.drawable.ic_warning);
                                builder.setCancelable(false);
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {


                                        YoutubeDao youtubeDao = new YoutubeDao(context);

                                        int result =  youtubeDao.delete(youtubeVideo);

                                        if (result > 0)
                                        {
                                            Toast.makeText(context,"deleted " + result,Toast.LENGTH_SHORT);
                                            youtubeVideos.remove(youtubeVideo);
                                            notifyDataSetChanged();
                                        }
                                        else {

                                            Toast.makeText(context,"Failed " + result,Toast.LENGTH_SHORT);
                                        }

                                    }
                                });
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        dialog.cancel();
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();


                                return true;
                            default:
                                return true;
                        }
                    }
                });
                popupMenus.show();
                try {
                    Field popup = PopupMenu.class.getDeclaredField("mPopup");
                    popup.setAccessible(true);
                    Object menu = popup.get(popupMenus);
                    menu.getClass().getDeclaredMethod("setForceShowIcon", boolean.class).invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return youtubeVideos.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults filterResults = new FilterResults();

                if (charSequence == null | charSequence.length() == 0){
                    filterResults.count = getYoutubeVideosListFiltered.size();
                    filterResults.values = getYoutubeVideosListFiltered;
                }else {

                    String searchChr = charSequence.toString().toLowerCase();

                    List<YoutubeVideo> resultData = new ArrayList<>();

                    for (YoutubeVideo youtubeVideo : getYoutubeVideosListFiltered){
                        if (youtubeVideo.getTitre().toLowerCase().contains(searchChr)){
                            resultData.add(youtubeVideo);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;

                }
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                youtubeVideos = (List<YoutubeVideo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

}
