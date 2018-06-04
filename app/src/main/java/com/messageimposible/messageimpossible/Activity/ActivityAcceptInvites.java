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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messageimposible.messageimpossible.Entity.EntityContact;
import com.messageimposible.messageimpossible.Entity.EntityInvite;
import com.messageimposible.messageimpossible.Entity.EntityUsers;
import com.messageimposible.messageimpossible.R;

public class ActivityAcceptInvites extends AppCompatActivity{

    private TextView name;
    private Button b_accept;
    private Button b_cancel;
    private String email_target;
    private String username;
    private String email;
    private String id_target;
    private String name_target;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_popup_accept_invites);

        name = findViewById(R.id.tv_name_invite);
        b_accept = findViewById(R.id.button_accept_invite);
        b_cancel = findViewById(R.id.button_cancel_invite);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");
        mAuth = FirebaseAuth.getInstance();

        //coge las medidas de la pantalla
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //ajusta el tamaño de la actividad
        getWindow().setLayout((int)(width * .8), (int)(height * .6));


        //datos del usuario que ha mandado la invitacion
        Bundle b = getIntent().getExtras();

        id_target = b.getString("id_target");
        name_target = b.getString("name_target");
        email_target=b.getString("email_target");
        name.setText(name_target);
        username = b.getString("username");

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
                //DenyInvite();
                finish();
            }
        });

    }



    private void AcceptInvite(){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        EntityUsers user = snapshot.getValue(EntityUsers.class);
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        EntityUsers userMe = snapshot.getValue(EntityUsers.class);

                        if (userMe.getId().equals(currentUser.getUid())){

                            //Todo no funciona este email, QUITAR EMAIL DE CONTACTO???
                            email = userMe.getEmail();

                            EntityContact myFriend = new EntityContact();
                            myFriend.setEmail(email_target);
                            myFriend.setId(id_target);
                            myFriend.setUsername(name_target);
                            userMe.addFriends(myFriend);

                            userMe.deleteInvite(email_target);

                            DatabaseReference userReference = databaseReference.child(currentUser.getUid());

                            userReference.setValue(userMe);

                        }

                        if (user.getUsername().equals(name.getText())){

                            EntityContact contact = new EntityContact();
                            contact.setEmail(email);
                            contact.setUsername(username);
                            contact.setId(currentUser.getUid());
                            user.addFriends(contact);

                            user.deleteInvite(email);

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

    private void DenyInvite(){

        /*
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        EntityUsers user = snapshot.getValue(EntityUsers.class);

                        if (user.getUsername().equals(name.getText())){

                            EntityContact contact = new EntityContact();
                            contact.setEmail(email_target);
                            contact.setUsername(username);
                            user.addFriends(contact);

                            user.deleteInvite(email_target);

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
*/
    }

}
