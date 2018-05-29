package com.messageimposible.messageimpossible.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.messageimposible.messageimpossible.Adapter.AdapterListViewInvites;
import com.messageimposible.messageimpossible.Entity.EntityListItemInvites;
import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInvites extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_invites, container, false);

        ArrayList<EntityListItemInvites> listInvites = GetlistInvites();
        ListView lv = view.findViewById(R.id.listView_invites);
        lv.setAdapter(new AdapterListViewInvites(this.getActivity(), listInvites));

        return view;

    }

    private ArrayList<EntityListItemInvites> GetlistInvites(){
        ArrayList<EntityListItemInvites> contactlist = new ArrayList<EntityListItemInvites>();

        EntityListItemInvites contact = new EntityListItemInvites();

        contact.setImg(R.drawable.facebook_icon);
        contact.setName("Topher");
        contact.setInviteMessage("Hey I'm Topher from school");
        contactlist.add(contact);

        contact = new EntityListItemInvites();
        contact.setImg(R.drawable.mag_09);
        contact.setName("Mary");
        contact.setInviteMessage("Hello, I would like to be your friend");
        contactlist.add(contact);

        contact = new EntityListItemInvites();
        contact.setImg(R.mipmap.message_impossible_icon);
        contact.setName("Estalin");
        contact.setInviteMessage("Hello, I would like to be your friend");
        contactlist.add(contact);

        return contactlist;
    }

}