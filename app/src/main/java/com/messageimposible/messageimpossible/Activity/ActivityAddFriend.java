package com.messageimposible.messageimpossible.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.messageimposible.messageimpossible.Adapter.AdapterListViewAddFriend;
import com.messageimposible.messageimpossible.Entity.EntityListItemAddFriend;
import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;

public class ActivityAddFriend extends AppCompatActivity{

    private ListView lv;
    private ArrayList<EntityListItemAddFriend> listFriends;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_friend);

        listFriends = GetlistChat();
        mAuth = FirebaseAuth.getInstance();

        lv = findViewById(R.id.listView_friends);
        lv.setAdapter(new AdapterListViewAddFriend(this, listFriends));

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //que cree un menu emergente donde ponga nombre de la persona a la que quieres invitar
                //y dos botones, uno para comfirmar y otro para cancelar

                //si confirma debera aparecer una invitacion en la pestaña de invites del taget

                //si el target acepta, debera aparecer en la pestaña de contactos el nuevo amigo aceptado,
                //tanto en la del target como en la del owner.

            }
        });

    }


    private ArrayList<EntityListItemAddFriend> GetlistChat(){
        ArrayList<EntityListItemAddFriend> contactlist = new ArrayList<EntityListItemAddFriend>();

        EntityListItemAddFriend contact = new EntityListItemAddFriend();

        contact.setImg(R.drawable.facebook_icon);
        contact.setName("Topher");
        contact.setEmail("topher@gmail.com");
        contactlist.add(contact);

        contact = new EntityListItemAddFriend();
        contact.setImg(R.drawable.mag_09);
        contact.setName("Mary");
        contact.setEmail("mary@gmail.com");
        contactlist.add(contact);

        contact = new EntityListItemAddFriend();
        contact.setImg(R.mipmap.message_impossible_icon);
        contact.setName("Estalin");
        contact.setEmail("estalin@admin.com");
        contactlist.add(contact);


        return contactlist;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item_friend,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int res_id = item.getItemId();

        if(res_id == R.id.action_search_friend){

            Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();

        }else if(res_id == R.id.action_back_friend){

            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_LONG).show();
            finish();

        }else if (res_id == R.id.action_logout_friend){

            mAuth.signOut();
            Toast.makeText(getApplicationContext(), "Loged out", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ActivityLogin.class);
            startActivity(i);
            finish();
        }

        return true;
    }

}
