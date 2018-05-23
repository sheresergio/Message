package com.messageimposible.messageimpossible;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);

        final ImageView iv = findViewById(R.id.image_loading);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);

        iv.startAnimation(an);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(ActivitySplashScreen.this, ActivityLogin.class);

                startActivity(i);

                finish();

            }
        }, 4000);

    }
}
