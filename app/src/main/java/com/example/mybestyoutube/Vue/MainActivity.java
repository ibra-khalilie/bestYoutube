package com.example.mybestyoutube.Vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mybestyoutube.Adapter.YoutubeAdapter;
import com.example.mybestyoutube.Dao.YoutubeDao;
import com.example.mybestyoutube.Model.YoutubeVideo;
import com.example.mybestyoutube.R;
import com.example.mybestyoutube.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rvMainActivity;
    private Context context;



    private YoutubeAdapter youtubeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setContentView(R.layout.activity_add_youtube);


        //recupere le RecyclerView
        rvMainActivity = findViewById(R.id.rvMainActivity);


        //Crée le layoutManager pour le Recycle
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvMainActivity.setHasFixedSize(true);

        rvMainActivity.setLayoutManager(layoutManager);


        context = getApplicationContext();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //test la valeur de l'id de l'item selectionné

        switch (item.getItemId()){

            case R.id.ajoutYoutube:
                //Creer un intent
                Intent intent = new Intent(getApplicationContext(), AddYoutubeActivity.class);

                //lance l'activity
                startActivity(intent);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        TodoAsyncTasks todoAsyncTasks = new TodoAsyncTasks();
        todoAsyncTasks.execute();

    }

    public class TodoAsyncTasks extends AsyncTask<String, String, List<YoutubeVideo>> {

        @Override
        protected List<YoutubeVideo> doInBackground(String... strings) {
            YoutubeDao youtubeDao = new YoutubeDao(context);

            List<YoutubeVideo> youtubeVideos = new ArrayList<>();

            try {
                youtubeVideos = youtubeDao.list();

            } catch (Exception e) {

                e.printStackTrace();
            }

            return youtubeVideos;
        }

        @Override
        protected void onPostExecute(List<YoutubeVideo> youtubeVideos) {


            YoutubeAdapter youtubeAdapter = new YoutubeAdapter(youtubeVideos,context);

            youtubeAdapter = new YoutubeAdapter(youtubeVideos,context);

            rvMainActivity.setAdapter(youtubeAdapter);
        }

    }


}