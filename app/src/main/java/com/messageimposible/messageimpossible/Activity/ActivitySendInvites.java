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


public class ActivitySendInvites extends AppCompatActivity{

    private TextView name;
    private Button b_accept;
    private Button b_cancel;
    private String email;
    private String username;
    private String id_target;
    private String user_id;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_popup_send_invites);

        name = findViewById(R.id.tv_name_send);
        b_accept = findViewById(R.id.button_accept_send);
        b_cancel = findViewById(R.id.button_cancel_send);


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

        //coge las medidas de la pantalla
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //ajusta el tamaño de la actividad
        getWindow().setLayout((int)(width * .8), (int)(height * .6));



        Bundle b = getIntent().getExtras();
        name.setText(b.getString("name_target"));
        id_target = b.getString("id_target");
        username = b.getString("username");
        email = b.getString("email");
        user_id = b.getString("id");

        b_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //envie la peticion de amistad
                sendInvite();
            }
        });

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    private void sendInvite(){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        EntityUsers user = snapshot.getValue(EntityUsers.class);

                        if (user.getUsername().equals(name.getText())){

                            EntityInvite inv = new EntityInvite(user_id, username, email);
                            user.addInvites(inv);

                            DatabaseReference userReference = databaseReference.child(id_target);

                            userReference.setValue(user);

                            finish();

                        }

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
