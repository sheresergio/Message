package com.messageimposible.messageimpossible.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messageimposible.messageimpossible.Entity.EntityInvite;
import com.messageimposible.messageimpossible.Entity.EntityUsers;
import com.messageimposible.messageimpossible.R;

public class ActivityAcceptInvites extends AppCompatActivity{

    private TextView name;
    private Button b_accept;
    private Button b_cancel;
    private String email;
    private String username;
    private String id_target;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_popup_accept_invites);

        name = findViewById(R.id.tv_name_invite);
        b_accept = findViewById(R.id.button_accept_invite);
        b_cancel = findViewById(R.id.button_cancel_invite);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

        //coge las medidas de la pantalla
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //ajusta el tamaño de la actividad
        getWindow().setLayout((int)(width * .8), (int)(height * .6));


        //datos del usuario que ha mandado la invitacion
        Bundle b = getIntent().getExtras();

        name.setText(b.getString("username"));

        b_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //acepta la peticion de amistad, se borra de invites y se añade a amigos
                AcceptInvite();
                finish();
            }
        });

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //no acepta la peticion de amistad y se borra de invites
                DenyInvite();
                finish();
            }
        });

    }



    private void AcceptInvite(){

        //TODO aceptar invitaciones

    }

    private void DenyInvite(){

        //TODO rechazar invitaciones

    }

}
