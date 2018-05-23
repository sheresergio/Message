package com.messageimposible.messageimpossible;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityRegister extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        final EditText username = findViewById(R.id.et_username_register);
        final EditText email = findViewById(R.id.et_email);
        final EditText pass = findViewById(R.id.password);
        final EditText pass_conf = findViewById(R.id.confirm_password);


        TextView login_text = findViewById(R.id.login_txt);
        Button register_button = findViewById(R.id.register_button);

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(i);
                finish();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                USERNAME NOT IN DB
                EMAIL NOT IN DB
                PASS != PASS_CONF
                 */
                Intent i = new Intent(ActivityRegister.this, ActivityTabs.class);
                startActivity(i);
                finish();

            }
        });

    }

}
