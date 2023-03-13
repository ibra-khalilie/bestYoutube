package com.example.mybestyoutube.Vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.mybestyoutube.Vue.MainActivity;

import com.example.mybestyoutube.R;

public class SplashActivity extends AppCompatActivity {

    private TextView textAnimWelcome;
    private TextView textAnimYoutube;

    private static int SPLASH_TIMEOUT = 5000;
    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.sound);
        mediaPlayer.start();
        getSupportActionBar().hide();

        textAnimWelcome = findViewById(R.id.textAnimWelcome);
        textAnimYoutube = findViewById(R.id.textAnimYoutube);

        final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },SPLASH_TIMEOUT);
        Animation animation1 = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.animation2);
        textAnimWelcome.startAnimation(animation1);

        Animation animation2 = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.animation1);
        textAnimYoutube.startAnimation(animation2);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

}