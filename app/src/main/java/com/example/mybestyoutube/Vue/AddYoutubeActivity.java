package com.example.mybestyoutube.Vue;

import static java.lang.ref.Cleaner.create;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mybestyoutube.Dao.YoutubeDao;
import com.example.mybestyoutube.Model.YoutubeVideo;
import com.example.mybestyoutube.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        getSupportActionBar().hide();

        context = getApplicationContext();

        edtTitle = findViewById(R.id.addTitle);
        edtDesc = findViewById(R.id.addDescription);
        edtUrl = findViewById(R.id.addUrl);

        btAnnuler = findViewById(R.id.updBtAnnuler);
        btnAjouter = findViewById(R.id.updBtnAjouter);


        spCategorie = findViewById(R.id.addCategorie);


        createSpinner();

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String titre = edtTitle.getText().toString();

                String description = edtDesc.getText().toString();

                String url = edtUrl.getText().toString();

                String categorie = spCategorie.getSelectedItem().toString();



                if (titre.length() > 0 && description.length() > 0 && isUrlValid(url)){


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

                    if (titre.length() == 0) {
                        edtTitle.setError("Title must not be empty");
                    }
                    if (description.length() == 0) {
                        edtDesc.setError("Description must not be empty");
                    }
                    if (!isUrlValid(url)) {
                        edtUrl.setError("Invalid URL");
                    }

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

    //Regex pour Verifie Url
    public boolean isUrlValid(String url) {
        String regex = "^((http[s]?|ftp):\\/\\/)?([a-zA-Z0-9]+\\.)?[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,6}(\\/[a-zA-Z0-9\\-._\\?,'+&amp;%$#=~]*)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }



    //Creer un spinner
    private void createSpinner(){

        Spinner spinner =  findViewById(R.id.addCategorie);

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

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
