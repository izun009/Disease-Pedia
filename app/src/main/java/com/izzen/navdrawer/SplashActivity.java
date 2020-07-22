package com.izzen.navdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 25 June 2020, 20:19:00 WIB
 * Modified      : 25 June 2020, 22:47:00 WIB
 * */

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            Intent MainIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(MainIntent);
        }, SPLASH_TIME_OUT);

    }
}