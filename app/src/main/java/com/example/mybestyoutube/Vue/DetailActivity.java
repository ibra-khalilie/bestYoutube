package com.example.mybestyoutube.Vue;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
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


        context = getApplicationContext();

        detailTitre = findViewById(R.id.detailTitle);
        detailDescription = findViewById(R.id.detailDescription);
        detailUrl = findViewById(R.id.detailUrl);
        detailCategorie = findViewById(R.id.detailCategorie);
        btnVoir = findViewById(R.id.btnVoir);




    }
}