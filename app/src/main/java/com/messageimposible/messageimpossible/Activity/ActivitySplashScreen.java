package com.messageimposible.messageimpossible.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.messageimposible.messageimpossible.R;


public class ActivitySplashScreen extends AppCompatActivity {

    private ImageView iv;
    private Animation an;

    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);

        iv = findViewById(R.id.image_loading);
        an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        mAuth = FirebaseAuth.getInstance();

        iv.startAnimation(an);

    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){

            Intent i = new Intent(ActivitySplashScreen.this, ActivityTabs.class);
            startActivity(i);
            finish();

        }else{

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

}
