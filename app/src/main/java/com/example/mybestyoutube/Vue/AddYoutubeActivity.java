package com.example.mybestyoutube.Vue;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mybestyoutube.Dao.YoutubeDao;
import com.example.mybestyoutube.Model.YoutubeVideo;
import com.example.mybestyoutube.R;

import java.util.ArrayList;
import java.util.List;

public class AddYoutubeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Context context;

    private EditText edtTitle;
    private EditText edtDesc;
    private EditText edtUrl;
    private Button btAnnuler;
    private Button btnAjouter;
    private Spinner spCategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youtube);

        context = getApplicationContext();

        edtTitle = findViewById(R.id.edtTitle);
        edtDesc = findViewById(R.id.edtDesc);
        edtUrl = findViewById(R.id.edtUrl);

        btAnnuler = findViewById(R.id.btAnnuler);
        btnAjouter = findViewById(R.id.btnAjouter);


        spCategorie = findViewById(R.id.spinner);


        createSpinner();

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String titre = edtTitle.getText().toString();

                String description = edtDesc.getText().toString();

                String url = edtUrl.getText().toString();

                String categorie = spCategorie.getSelectedItem().toString();



                if (titre.length() > 0 && description.length() > 0 && url.length() > 0){


                    YoutubeDao youtubeDao = new YoutubeDao(context);
                    //creee
                    YoutubeVideo youtubeVideo = new YoutubeVideo();
                    youtubeVideo.setTitre(titre);
                    youtubeVideo.setDescription(description);
                    youtubeVideo.setUrl(url);
                    youtubeVideo.setCategorie(categorie);
                    youtubeDao.add(youtubeVideo);

                    //termine cette activity
                    finish();

                }else {


                    Toast toast = Toast.makeText(context, "le Titre,Description et Url ne doit pas etre vide",Toast.LENGTH_SHORT);
                    toast.show();

                }




            }
        });

        btAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

    }

    private void createSpinner(){


        Spinner spinner =  findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);


        List<String> categories = new ArrayList<String>();
        categories.add("Sport");
        categories.add("Music");
        categories.add("Comedy");
        categories.add("Cuisine");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String item = adapterView.getItemAtPosition(i).toString();

        Toast.makeText(adapterView.getContext(), "sélectionner: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
