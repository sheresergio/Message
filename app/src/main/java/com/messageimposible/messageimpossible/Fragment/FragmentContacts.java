package com.messageimposible.messageimpossible.Fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.messageimposible.messageimpossible.Adapter.AdapterListViewContact;
import com.messageimposible.messageimpossible.Entity.EntityContact;
import com.messageimposible.messageimpossible.Entity.EntityInvite;
import com.messageimposible.messageimpossible.Entity.EntityListItemContact;
import com.messageimposible.messageimpossible.Entity.EntityUsers;
import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContacts extends Fragment {

    private ArrayList<EntityContact> listContact;
    private ListView lv;
    private AdapterListViewContact adapter;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        currentUser = mAuth.getCurrentUser();
        databaseReference = database.getReference("users");

        listContact = GetlistContact();
        lv = view.findViewById(R.id.listView_contacts);
        adapter = new AdapterListViewContact(this.getContext(), listContact);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                EntityContact contact = (EntityContact) parent.getAdapter().getItem(position);

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

    private ArrayList<EntityContact> GetlistContact(){
        final ArrayList<EntityContact> contactlist = new ArrayList<>();

        //TODO que actualice nada mas tener un nuevo contacto

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contactlist.clear();
                if (dataSnapshot.exists()){

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        EntityUsers user = snapshot.getValue(EntityUsers.class);

                        if (user.getId().equals(currentUser.getUid())){

                            if (!user.getContacts().isEmpty()){

                                for(EntityContact contacts: user.getContacts()){

                                    EntityContact contact = new EntityContact();

                                    contact.setImg(contacts.getImg());
                                    contact.setUsername(contacts.getUsername());
                                    contact.setEmail(contacts.getEmail());
                                    contact.setId(contacts.getId());

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