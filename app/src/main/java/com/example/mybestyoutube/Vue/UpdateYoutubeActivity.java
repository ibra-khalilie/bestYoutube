package com.example.mybestyoutube.Vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mybestyoutube.Dao.YoutubeDao;
import com.example.mybestyoutube.Model.YoutubeVideo;
import com.example.mybestyoutube.R;

import java.util.ArrayList;
import java.util.List;

public class UpdateYoutubeActivity extends AppCompatActivity {


    private Context context;

    private EditText edtTitle;
    private EditText edtDesc;
    private EditText edtUrl;
    private Button btAnnuler;
    private Button btnAjouter;
    private Spinner spCategorie;

    private  Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_youtube);

        context = getApplicationContext();

        edtTitle = findViewById(R.id.addTitle);
        edtDesc = findViewById(R.id.addDescription);
        edtUrl = findViewById(R.id.addUrl);

        spCategorie = findViewById(R.id.addCategorie);

        btAnnuler = findViewById(R.id.updBtAnnuler);
        btnAjouter = findViewById(R.id.updBtnAjouter);



        List<String> categories = new ArrayList<String>();
        categories.add("Sport");
        categories.add("Music");
        categories.add("Comedy");
        categories.add("Cuisine");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spCategorie.setAdapter(dataAdapter);

        YoutubeVideo  youtubeVideo = (YoutubeVideo) getIntent().getExtras().getSerializable("editYoutubeVideo");

        id = youtubeVideo.getId();
        edtTitle.setText(youtubeVideo.getTitre());
        edtDesc.setText(youtubeVideo.getDescription());
        edtUrl.setText(youtubeVideo.getUrl());

        //spinner
        String categorieVideo = youtubeVideo.getCategorie();
        int position = dataAdapter.getPosition(categorieVideo);
        spCategorie.setSelection(position);


        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                update(view);

                Toast.makeText(context, youtubeVideo.getTitre() + " Well be updated", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        btAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

    }

    public void update(View view){

        String titre = edtTitle.getText().toString();
        String description = edtDesc.getText().toString();
        String url = edtUrl.getText().toString();
        String categorie = spCategorie.getSelectedItem().toString();

        YoutubeDao youtubeDao = new YoutubeDao(context);

        YoutubeVideo youtubeVideo = new YoutubeVideo();
        youtubeVideo.setId(id);
        youtubeVideo.setTitre(titre);
        youtubeVideo.setDescription(description);
        youtubeVideo.setUrl(url);
        youtubeVideo.setCategorie(categorie);

        int result =  youtubeDao.update(youtubeVideo);

        if (result > 0)
        {
            Toast.makeText(context,"Updated " + result,Toast.LENGTH_SHORT);
        }
        else {

            Toast.makeText(context,"Failed " + result,Toast.LENGTH_SHORT);
        }
    }
}