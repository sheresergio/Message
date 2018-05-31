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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.messageimposible.messageimpossible.Activity.ActivityAcceptInvites;
import com.messageimposible.messageimpossible.Adapter.AdapterListViewInvites;
import com.messageimposible.messageimpossible.Entity.EntityInvite;
import com.messageimposible.messageimpossible.Entity.EntityListItemAddFriend;
import com.messageimposible.messageimpossible.Entity.EntityListItemInvites;
import com.messageimposible.messageimpossible.Entity.EntityUsers;
import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInvites extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private AdapterListViewInvites adapter;
    ArrayList<EntityListItemInvites> listInvites;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_invites, container, false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

        listInvites = GetlistInvites();
        ListView lv = view.findViewById(R.id.listView_invites);
        adapter = new AdapterListViewInvites(this.getActivity(), listInvites);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getContext(), ActivityAcceptInvites.class);
                //TODO bundle att
                i.putExtra("username", listInvites.get(position).getUsername());
                startActivity(i);

            }
        });

        return view;

    }

    private ArrayList<EntityListItemInvites> GetlistInvites(){
        final ArrayList<EntityListItemInvites> contactlist = new ArrayList<EntityListItemInvites>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contactlist.clear();
                if (dataSnapshot.exists()){

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        EntityUsers user = snapshot.getValue(EntityUsers.class);

                        if (!user.getInvites().isEmpty()){

                            for(EntityInvite invite: user.getInvites()){

                                EntityListItemInvites contact = new EntityListItemInvites();

                                contact.setUsername(invite.getUsername());
                                contact.setInviteMessage(invite.getEmail());

                                contactlist.add(contact);

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
