package com.messageimposible.messageimpossible.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messageimposible.messageimpossible.Activity.ActivityInchat;
import com.messageimposible.messageimpossible.Adapter.AdapterListViewChat;
import com.messageimposible.messageimpossible.Entity.EntityContact;
import com.messageimposible.messageimpossible.Entity.EntityListItemChat;
import com.messageimposible.messageimpossible.Entity.EntityUsers;
import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChats extends Fragment {

    private ListView lv;
    private AdapterListViewChat adapter;
    private ArrayList<EntityListItemChat> listContact;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        currentUser = mAuth.getCurrentUser();
        databaseReference = database.getReference("chats");


        listContact = GetlistChat();
        lv = view.findViewById(R.id.listView_chats);
        adapter = new AdapterListViewChat(this.getActivity(), listContact);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                EntityListItemChat contact = (EntityListItemChat) parent.getAdapter().getItem(position);

                Intent i = new Intent(getActivity(), ActivityInchat.class);
                i.putExtra("id_target", contact.getId());
                i.putExtra("id_owner", currentUser.getUid());
                i.putExtra("name_target", contact.getUsername());
                i.putExtra("img_target", contact.getImg());
                startActivity(i);


            }
        });

        return view;
    }

    private ArrayList<EntityListItemChat> GetlistChat() {
        final ArrayList<EntityListItemChat> contactlist = new ArrayList<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contactlist.clear();
                if (dataSnapshot.exists()){

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        if (snapshot.toString().contains(currentUser.getUid())) {

                            String full = snapshot.getKey();
                            String mid = full.replace(currentUser.getUid(), "");
                            final String end = mid.replace("-", "");

                            DatabaseReference userReference = database.getReference("users");

                            userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    if (dataSnapshot.exists()) {

                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                                            EntityUsers user = snapshot.getValue(EntityUsers.class);

                                            if (user.getId().equals(end)) {

                                                EntityListItemChat chatContact = new EntityListItemChat();

                                                chatContact.setImg(user.getImg());
                                                chatContact.setId(user.getId());
                                                chatContact.setUsername(user.getUsername());
                                                chatContact.setLastMessage("Hola");

                                                contactlist.add(chatContact);

                                            }

                                        }

                                        adapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }

                    }

                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return contactlist;
    }

}

