package com.messageimposible.messageimpossible.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.messageimposible.messageimpossible.Adapter.AdapterMessage;
import com.messageimposible.messageimpossible.Entity.EntityMessageOwner;
import com.messageimposible.messageimpossible.Entity.EntityMessageTarget;
import com.messageimposible.messageimpossible.R;

public class ActivityInchat extends AppCompatActivity {

    private Button b_send;
    private Button b_bomb;
    private ImageView target_img;
    private TextView target_name;
    private EditText txt_message;
    private RecyclerView rv_message;

    private AdapterMessage adapter;

    //firebase
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inchat);

        b_send = findViewById(R.id.btn_send);
        b_bomb = findViewById(R.id.btn_bomb);
        target_img = findViewById(R.id.iv_target_img);
        target_name = findViewById(R.id.tv_targetName);
        txt_message = findViewById(R.id.et_inchat);
        rv_message = findViewById(R.id.rv_inchat);

        //firebase
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");

        adapter = new AdapterMessage(this);

        LinearLayoutManager l = new LinearLayoutManager(this);
        rv_message.setLayoutManager(l);
        rv_message.setAdapter(adapter);


        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!txt_message.getText().toString().equals("")) {

                    databaseReference.push().setValue(
                            new EntityMessageOwner(
                                    "", txt_message.getText().toString(), ServerValue.TIMESTAMP
                            )
                    );

                    //serverValue.TIMESTAMP devuelve la hora en forma de map

                }
            }
        });


        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
                txt_message.setText("");
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                EntityMessageTarget m = dataSnapshot.getValue(EntityMessageTarget.class);

                adapter.addMessage(m);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void setScrollbar(){

        rv_message.scrollToPosition(adapter.getItemCount()-1);

    }

}