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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityRegister extends AppCompatActivity{

    private EditText et_username;
    private EditText et_email;
    private EditText et_pass;
    private EditText et_pass_conf;
    private TextView login_text;
    private Button register_button;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference userReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        et_username = findViewById(R.id.et_username_register);
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.password);
        et_pass_conf = findViewById(R.id.confirm_password);
        login_text = findViewById(R.id.login_txt);
        register_button = findViewById(R.id.register_button);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userReference = database.getReference("users");

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

                final String name = et_username.getText().toString();
                final String email = et_email.getText().toString();

                if (validateEmail(email) && validatePassword() && validateUserName(name)){

                    String password = et_pass.getText().toString();


                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(ActivityRegister.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information



                                        Toast.makeText(ActivityRegister.this, "WELLCOME " + name, Toast.LENGTH_LONG).show();

                                        EntityUsers user = new EntityUsers();
                                        user.setEmail(email);
                                        user.setUsername(name);
                                        userReference.push().setValue(user);

                                        Intent i = new Intent(ActivityRegister.this, ActivityTabs.class);
                                        startActivity(i);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(ActivityRegister.this, "Error al registrarse", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });


                }else{

                    Toast.makeText(ActivityRegister.this, "Wrong email or password. Please, try it again.", Toast.LENGTH_LONG).show();

                }



            }
        });

    }

    public final static boolean validateEmail(CharSequence target){

        //devuelve true si no esta vacio y es un correo.
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();

    }

    public boolean validatePassword(){

        String pass, pass_conf;

        pass = et_pass.getText().toString();
        pass_conf = et_pass_conf.getText().toString();

        if (pass.equals(pass_conf)){

            if (pass.length() >= 6 && pass.length()<= 16){

                return true;

            }else return false;

        }else return false;

    }

    public boolean validateUserName(String name){

        return !name.isEmpty();

    }

}
