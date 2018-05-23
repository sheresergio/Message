package com.messageimposible.messageimpossible;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContacts extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        final ArrayList<EntityListItemContact> listContact = GetlistContact();
        final ListView lv = view.findViewById(R.id.listView_contacts);
        AdapterListViewContact adapter = new AdapterListViewContact(this.getContext(), listContact);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getActivity(), ActivityInchat.class);
                i.putExtra("position", position);
                startActivity(i);

            }
        });

        return view;
    }

    private ArrayList<EntityListItemContact> GetlistContact(){
        ArrayList<EntityListItemContact> contactlist = new ArrayList<EntityListItemContact>();

        EntityListItemContact contact = new EntityListItemContact();

        contact.setImg(R.drawable.facebook_icon);
        contact.setName("Topher");
        contact.setOnline("ONLINE");
        contact.setLastConnection("20 Apr");
        contactlist.add(contact);

        contact = new EntityListItemContact();
        contact.setImg(R.drawable.mag_09);
        contact.setName("Mary");
        contact.setOnline("ONLINE");
        contact.setLastConnection("just now");
        contactlist.add(contact);

        contact = new EntityListItemContact();
        contact.setImg(R.mipmap.message_impossible_icon);
        contact.setName("Estalin");
        contact.setOnline("OFFLINE");
        contact.setLastConnection("Yesterday");
        contactlist.add(contact);

        return contactlist;
    }

}