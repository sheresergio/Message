package com.messageimposible.messageimpossible;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;

import io.deepstream.DeepstreamClient;
import io.deepstream.DeepstreamFactory;

public class ActivityLogin extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        TextView register_text = findViewById(R.id.register_txt);
        Button login_button = findViewById(R.id.login_button);
        TextView forgot_text = findViewById(R.id.forgot_text);

        forgot_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityLogin.this, ActivityForgotPass.class);
                startActivity(i);
            }
        });

        register_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(i);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(true){/*db access correct*/

                    Intent i = new Intent(ActivityLogin.this, ActivityTabs.class);
                    startActivity(i);
                    finish();

                }else{

                    Toast.makeText(ActivityLogin.this, "Error, wrong username or password.", Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}
