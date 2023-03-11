package com.example.mybestyoutube.Vue;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
<<<<<<< Updated upstream
=======
import android.content.Intent;
<<<<<<< HEAD
=======
import android.net.Uri;
import android.os.Build;
>>>>>>> parent of 2266c2a (Revert "DetailActivity fonctionne")
>>>>>>> Stashed changes
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mybestyoutube.R;

public class DetailActivity extends AppCompatActivity {


    private Context context;

    private TextView detailTitre;
    private TextView detailDescription;
    private TextView detailUrl;

    private TextView detailCategorie;
    private Button btnVoir;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
<<<<<<< Updated upstream
=======
        getSupportActionBar().hide();
<<<<<<< HEAD
=======
>>>>>>> Stashed changes

>>>>>>> parent of 2266c2a (Revert "DetailActivity fonctionne")

        context = getApplicationContext();

        detailTitre = findViewById(R.id.detailTitle);
        detailDescription = findViewById(R.id.detailDescription);
        detailUrl = findViewById(R.id.detailUrl);
        detailCategorie = findViewById(R.id.detailCategorie);
        btnVoir = findViewById(R.id.btnVoir);

        Intent intent = getIntent();
        String titre = intent.getExtras().getString("titre");
        String description = intent.getExtras().getString("description");
        String url = intent.getExtras().getString("url");
        String categorie = intent.getExtras().getString("categorie");


        detailTitre.setText(titre);
        detailDescription.setText(description);
        detailUrl.setText(url);
        detailCategorie.setText(categorie);




    }
}