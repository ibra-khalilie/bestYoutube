package com.example.mybestyoutube.Adapter;

<<<<<<< Updated upstream
=======
import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
=======
import android.database.sqlite.SQLiteDatabase;
>>>>>>> parent of 2266c2a (Revert "DetailActivity fonctionne")
>>>>>>> Stashed changes
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mybestyoutube.Model.YoutubeVideo;
import com.example.mybestyoutube.R;
<<<<<<< Updated upstream
=======
import com.example.mybestyoutube.Vue.DetailActivity;
<<<<<<< HEAD
=======
import com.example.mybestyoutube.Vue.UpdateYoutubeActivity;
>>>>>>> parent of 2266c2a (Revert "DetailActivity fonctionne")
>>>>>>> Stashed changes


import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeAdapterViewHolder>{


<<<<<<< HEAD
=======

<<<<<<< Updated upstream
=======
>>>>>>> parent of 2266c2a (Revert "DetailActivity fonctionne")
    Context context;
>>>>>>> Stashed changes
    private List<YoutubeVideo> youtubeVideos;


    public YoutubeAdapter(List<YoutubeVideo> youtubeVideos, Context context){
        this.youtubeVideos = youtubeVideos;
        this.context = context;


    }


    public class YoutubeAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitre;
        public  TextView tvDescription;
<<<<<<< Updated upstream


=======
        public CardView cardView;
<<<<<<< HEAD
=======
        public ImageView mMenus;
>>>>>>> parent of 2266c2a (Revert "DetailActivity fonctionne")
>>>>>>> Stashed changes
        public YoutubeAdapterViewHolder(View itemView){
            super(itemView);

            tvTitre = itemView.findViewById(R.id.tvTitre);
            tvDescription = itemView.findViewById(R.id.tvDescription);
<<<<<<< Updated upstream
=======
            cardView = itemView.findViewById(R.id.carderView);
<<<<<<< HEAD
=======
            mMenus = itemView.findViewById(R.id.mMenus);

>>>>>>> parent of 2266c2a (Revert "DetailActivity fonctionne")
>>>>>>> Stashed changes

        }

    }

    public YoutubeAdapter(List<YoutubeVideo> youtubeVideos){
        this.youtubeVideos = youtubeVideos;


    }

<<<<<<< HEAD

=======
>>>>>>> parent of 2266c2a (Revert "DetailActivity fonctionne")
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

<<<<<<< Updated upstream
=======


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

<<<<<<< HEAD
                Toast.makeText(context,"clicked " + position,Toast.LENGTH_SHORT).show();
=======
>>>>>>> parent of 2266c2a (Revert "DetailActivity fonctionne")
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("titre", youtubeVideos.get(position).getTitre());
                intent.putExtra("description", youtubeVideos.get(position).getDescription());
                intent.putExtra("url",youtubeVideos.get(position).getUrl());
                intent.putExtra("categorie", youtubeVideos.get(position).getCategorie());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


<<<<<<< HEAD
=======

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
                                Toast.makeText(context, youtubeVideo.getTitre() + " Well be updated", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(context, UpdateYoutubeActivity.class);
                                intent.putExtra("editYoutubeVideo",youtubeVideo);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                return true;
                            case R.id.delete:
                                Toast.makeText(context, youtubeVideo.getTitre() + " Well be delete", Toast.LENGTH_SHORT).show();

                                /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("confirmation !!!");
                                builder.setMessage("Are you sure to delete " + youtubeVideo.getTitre() + " ?");
                                builder.setIcon(R.drawable.ic_warning);
                                builder.setPositiveButton("Yes",null);
                                builder.setNegativeButton("No",null);

                                builder.create().show();*/

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


>>>>>>> parent of 2266c2a (Revert "DetailActivity fonctionne")
>>>>>>> Stashed changes
    }

    @Override
    public int getItemCount() {
        return youtubeVideos.size();
    }

}
