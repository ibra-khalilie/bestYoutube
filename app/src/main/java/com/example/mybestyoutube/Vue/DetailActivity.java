package com.example.mybestyoutube.Vue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mybestyoutube.R;

public class DetailActivity extends AppCompatActivity {


    private Context context;

    private TextView detailTitre;
    private TextView detailDescription;
    private TextView detailUrl;

    private TextView detailCategorie;
    private Button btnVoir;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        context = getApplicationContext();

        detailTitre = findViewById(R.id.addTitle);
        detailDescription = findViewById(R.id.addDescription);
        detailUrl = findViewById(R.id.addUrl);
        detailCategorie = findViewById(R.id.addCategorie);
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


        btnVoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(url));
                try {
                    DetailActivity.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });


    }
}