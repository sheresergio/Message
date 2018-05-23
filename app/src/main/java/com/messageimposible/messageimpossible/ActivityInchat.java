package com.messageimposible.messageimpossible;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityInchat extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inchat);


        Bundle bundle = getIntent().getExtras();

        if(bundle!= null){

            if(bundle.getInt("position") != -1){

                Toast.makeText(getApplicationContext(), "position " + bundle.getInt("position"), Toast.LENGTH_LONG).show();

            }

        }



    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int res_id = item.getItemId();

        if(res_id == R.id.action_search){

            Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();

        }else if(res_id == R.id.action_addFriend){

            Toast.makeText(getApplicationContext(), "add friend", Toast.LENGTH_LONG).show();

        }else if (res_id == R.id.action_settings){

            Toast.makeText(getApplicationContext(), "settings", Toast.LENGTH_LONG).show();

        }

        return true;
    }

}