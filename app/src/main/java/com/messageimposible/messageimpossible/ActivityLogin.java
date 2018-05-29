package com.messageimposible.messageimpossible;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URISyntaxException;

import io.deepstream.DeepstreamClient;
import io.deepstream.DeepstreamFactory;

public class ActivityLogin extends AppCompatActivity{

    private EditText et_email;
    private EditText et_password;
    private TextView register_text;
    private TextView forgot_text;
    private Button login_button;

    //Firebase
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        register_text = findViewById(R.id.register_txt);
        forgot_text = findViewById(R.id.forgot_text);
        login_button = findViewById(R.id.login_button);
        et_email = findViewById(R.id.et_username_login);
        et_password = findViewById(R.id.et_password_login);

        mAuth = FirebaseAuth.getInstance();

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

                String email = et_email.getText().toString();

                if(validateEmail(email) && validatePassword()){

                    String password = et_password.getText().toString();

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(ActivityLogin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        Toast.makeText(ActivityLogin.this, "Welcome", Toast.LENGTH_LONG).show();

                                        Intent i = new Intent(ActivityLogin.this, ActivityTabs.class);
                                        startActivity(i);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(ActivityLogin.this, "Error, wrong email or password. Please, try it again", Toast.LENGTH_LONG).show();

                                    }

                                }
                            });

                }else{



                }

            }
        });

    }

    private boolean validateEmail(CharSequence target){

        //devuelve true si no esta vacio y es un correo.
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();

    }

    private boolean validatePassword(){

        String pass, pass_conf;

        pass = et_password.getText().toString();

        if (pass.length() >= 6 && pass.length()<= 16){

            return true;

        }else return false;

    }

}
