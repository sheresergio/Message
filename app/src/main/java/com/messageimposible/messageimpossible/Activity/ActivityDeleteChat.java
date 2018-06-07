package com.messageimposible.messageimpossible.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messageimposible.messageimpossible.R;

public class ActivityDeleteChat extends AppCompatActivity {

    private TextView name;
    private Button b_accept;
    private Button b_cancel;

    private String name_target;
    private String id_target;
    private String id_owner;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_popup_delete_chat);

        database = FirebaseDatabase.getInstance();

        name = findViewById(R.id.tv_name_delete);
        b_accept = findViewById(R.id.button_accept_delete);
        b_cancel = findViewById(R.id.button_cancel_delete);

        //coge las medidas de la pantalla
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //ajusta el tamaño de la actividad
        getWindow().setLayout((int)(width * .8), (int)(height * .6));

        Bundle b = getIntent().getExtras();
        id_owner = b.getString("id_owner");
        id_target = b.getString("id_target");
        name_target = b.getString("name_target");

        name.setText(name_target);

        if (id_owner.compareTo(id_target) < 0) {

            databaseReference = database.getReference("chats/" + id_owner + "-" + id_target);

        } else if (id_owner.compareTo(id_target) > 0) {

            databaseReference = database.getReference("chats/" + id_target + "-" + id_owner);

        }

        b_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //acepta que se borre el chat
                AcceptDelete();
                finish();
            }
        });

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    public void AcceptDelete(){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    snapshot.getRef().removeValue();  //Elimina valor!

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

}
