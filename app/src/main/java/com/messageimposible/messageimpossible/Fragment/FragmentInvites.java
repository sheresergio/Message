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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messageimposible.messageimpossible.Activity.ActivityAcceptInvites;
import com.messageimposible.messageimpossible.Adapter.AdapterListViewInvites;
import com.messageimposible.messageimpossible.Entity.EntityInvite;
import com.messageimposible.messageimpossible.Entity.EntityUsers;
import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;

public class FragmentInvites extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;

    private String USER_NAME;
    private String USER_EMAIL;

    private AdapterListViewInvites adapter;
    ArrayList<EntityInvite> listInvites;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_invites, container, false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        currentUser = mAuth.getCurrentUser();
        databaseReference = database.getReference("users");

        listInvites = GetlistInvites();
        ListView lv = view.findViewById(R.id.listView_invites);
        adapter = new AdapterListViewInvites(this.getActivity(), listInvites);

        if (currentUser!=null){

            DatabaseReference reference = database.getReference("users/"+currentUser.getUid());

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    EntityUsers user = dataSnapshot.getValue(EntityUsers.class);
                    USER_NAME = user.getUsername();
                    USER_EMAIL = user.getEmail();
                    //Toast.makeText(ActivityTabs.this, USER_NAME , Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getContext(), ActivityAcceptInvites.class);
                i.putExtra("id_target", listInvites.get(position).getId());
                i.putExtra("name_target", listInvites.get(position).getUsername());
                i.putExtra("email_target", listInvites.get(position).getEmail());
                i.putExtra("username", USER_NAME);
                startActivity(i);

            }
        });

        return view;

    }

    private ArrayList<EntityInvite> GetlistInvites(){
        final ArrayList<EntityInvite> contactlist = new ArrayList<>();

        //TODO que actualice nada mas tener una nueva invitacion

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contactlist.clear();
                if (dataSnapshot.exists()){

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        EntityUsers user = snapshot.getValue(EntityUsers.class);

                        if (user.getId().equals(currentUser.getUid())){

                            if (!user.getInvites().isEmpty()){

                                for(EntityInvite invite: user.getInvites()){

                                    EntityInvite contact = new EntityInvite();

                                    contact.setUsername(invite.getUsername());
                                    contact.setEmail(invite.getEmail());
                                    contact.setId(invite.getId());

                                    contactlist.add(contact);

                                }

                            }

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
