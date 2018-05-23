package com.messageimposible.messageimpossible;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class FragmentChats extends Fragment {

    private ListView listView;
    private AdapterListViewChat adapterListViewChat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        ArrayList<EntityListItemChat> listContact = GetlistChat();
        ListView lv = view.findViewById(R.id.listView_chats);
        lv.setAdapter(new AdapterListViewChat(this.getActivity(), listContact));

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

    private ArrayList<EntityListItemChat> GetlistChat(){
        ArrayList<EntityListItemChat> contactlist = new ArrayList<EntityListItemChat>();

        EntityListItemChat contact = new EntityListItemChat();

        contact.setImg(R.drawable.facebook_icon);
        contact.setName("Topher");
        contact.setLastMessage("Hasta nunqui");
        contact.setLastConnection("20 Apr");
        contactlist.add(contact);

        contact = new EntityListItemChat();
        contact.setImg(R.drawable.mag_09);
        contact.setName("Mary");
        contact.setLastMessage("Hi");
        contact.setLastConnection("just now");
        contactlist.add(contact);

        contact = new EntityListItemChat();
        contact.setImg(R.mipmap.message_impossible_icon);
        contact.setName("Estalin");
        contact.setLastMessage("Tonto");
        contact.setLastConnection("Yesterday");
        contactlist.add(contact);

        contact = new EntityListItemChat();
        contact.setImg(R.drawable.mag_09);
        contact.setName("Mary");
        contact.setLastMessage("Hi");
        contact.setLastConnection("just now");
        contactlist.add(contact);

        contact = new EntityListItemChat();
        contact.setImg(R.mipmap.message_impossible_icon);
        contact.setName("Estalin");
        contact.setLastMessage("Tonto");
        contact.setLastConnection("Yesterday");
        contactlist.add(contact);

        contact = new EntityListItemChat();
        contact.setImg(R.drawable.mag_09);
        contact.setName("Mary");
        contact.setLastMessage("Hi");
        contact.setLastConnection("just now");
        contactlist.add(contact);

        contact = new EntityListItemChat();
        contact.setImg(R.mipmap.message_impossible_icon);
        contact.setName("Estalin");
        contact.setLastMessage("Tonto");
        contact.setLastConnection("Yesterday");
        contactlist.add(contact);

        contact = new EntityListItemChat();
        contact.setImg(R.drawable.mag_09);
        contact.setName("Mary");
        contact.setLastMessage("Hi");
        contact.setLastConnection("just now");
        contactlist.add(contact);

        contact = new EntityListItemChat();
        contact.setImg(R.mipmap.message_impossible_icon);
        contact.setName("Estalin");
        contact.setLastMessage("Tonto");
        contact.setLastConnection("Yesterday");
        contactlist.add(contact);

        return contactlist;
    }


}

