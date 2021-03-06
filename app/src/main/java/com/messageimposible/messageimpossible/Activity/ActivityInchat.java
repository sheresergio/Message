package com.messageimposible.messageimpossible.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.messageimposible.messageimpossible.Adapter.AdapterMessage;
import com.messageimposible.messageimpossible.Entity.EntityMessageOwner;
import com.messageimposible.messageimpossible.Entity.EntityMessageTarget;
import com.messageimposible.messageimpossible.Entity.EntityUsers;
import com.messageimposible.messageimpossible.R;

public class ActivityInchat extends AppCompatActivity {

    //elementos en el layout
    private Button b_send;
    private Button b_bomb;
    private TextView tv_name;
    private EditText txt_message;
    private RecyclerView rv_message;
    private LinearLayout linearbombone;
    private LinearLayout linearinchatbarbottom;
    private ImageView target_img;

    private String id_owner;
    private String id_target;
    private String name_target;
    private int name_picture;

    private AdapterMessage adapter;

    //firebase
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inchat);

        //bundle que recoge info del usuario actual y del usuario al que quieres hablar
        Bundle b = getIntent().getExtras();
        id_target = b.getString("id_target");
        id_owner = b.getString("id_owner");
        name_target = b.getString("name_target");
        name_picture = b.getInt("name_picture");

        b_send = findViewById(R.id.btn_send);
        b_bomb = findViewById(R.id.btn_bomb);
        tv_name = findViewById(R.id.tv_targetName);
        txt_message = findViewById(R.id.et_inchat);
        rv_message = findViewById(R.id.rv_inchat);
        linearinchatbarbottom = findViewById(R.id.linear_inchat_bar_bottom);
        linearbombone = findViewById(R.id.linearbombone);
            linearbombone.setVisibility(View.GONE);
        target_img = findViewById(R.id.iv_target_img);

        //le damos el nombre del usuario al que queremos escribir al TextView
        tv_name.setText(name_target);

        //carga la toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hace que no se carge ek nombre de la app cuando se carga la toolbar

        //firebase
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();


        /*
        Usamos un filtro de IDs para que, a la hora de crear/cargar un chat ocn un usuario,
        el ID de ese chat siempre sea el mismo y se pueda ver el mismo chat desde los dos usuarios.
         */

        if (id_owner.compareTo(id_target) < 0) {

            databaseReference = database.getReference("chats/" + id_owner + "-" + id_target);

        } else if (id_owner.compareTo(id_target) > 0) {

            databaseReference = database.getReference("chats/" + id_target + "-" + id_owner);

        } else {

            databaseReference = database.getReference("chats/" + id_owner + "-" + id_target);

        }

        //Inicializamos el adapter y le pasamos el id del usuario actual para usarlo a la hora de
        // recibir los mensajes, para separar lso mensajes recibidos de los mensajes enviados
        adapter = new AdapterMessage(this);
        adapter.setCurrentUserID(id_owner);


        //utilizamos el LinearLayoutManager para controlar los elementos dentro del RecyclerView
        LinearLayoutManager l = new LinearLayoutManager(this);
        rv_message.setLayoutManager(l);
        rv_message.setAdapter(adapter);

        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!txt_message.getText().toString().equals("")) {

                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if (currentUser != null) {

                        if (linearbombone.getVisibility() == View.VISIBLE) {

                            //SEND MESSAGE WITH SELF DESTRUCTION

                        } else {

                            DatabaseReference reference = database.getReference("users/" + currentUser.getUid());

                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    databaseReference.push().setValue(
                                            new EntityMessageOwner(
                                                    id_owner, txt_message.getText().toString(), ServerValue.TIMESTAMP
                                            )
                                    );

                                    //serverValue.TIMESTAMP devuelve la hora en forma de map

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }

                            });

                        }

                    }


                }
            }
        });

        b_bomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (linearbombone.getVisibility() == View.GONE) {
                    linearbombone.setVisibility(View.VISIBLE);
                    b_bomb.setBackgroundResource(R.drawable.ic_bomb2);
                    b_send.setBackgroundResource(R.drawable.ic_send1);
                    linearinchatbarbottom.setBackgroundResource(R.color.orangeChat);

                } else {
                    linearbombone.setVisibility(View.GONE);
                    b_bomb.setBackgroundResource(R.drawable.ic_bomb1);
                    b_send.setBackgroundResource(R.drawable.ic_send2);
                    linearinchatbarbottom.setBackgroundResource(R.color.greyChat);

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


    private void setScrollbar() {

        rv_message.scrollToPosition(adapter.getItemCount() - 1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item_inchat, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int res_id = item.getItemId();

        if (res_id == R.id.action_delete_chat) {

            Intent i = new Intent(ActivityInchat.this, ActivityDeleteChat.class);
            i.putExtra("id_target", id_target);
            i.putExtra("id_owner", id_owner);
            i.putExtra("name_target", name_target);
            startActivity(i);

        }


        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();


    }
}