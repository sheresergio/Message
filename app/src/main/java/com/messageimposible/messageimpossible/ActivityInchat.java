package com.messageimposible.messageimpossible;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

public class ActivityInchat extends AppCompatActivity {

    private Button b_send;
    private Button b_bomb;
    private ImageView target_img;
    private TextView target_name;
    private EditText txt_message;
    private RecyclerView rv_message;

    private AdapterListViewInchatOwner adapterOwner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inchat);

        b_send = findViewById(R.id.btn_send);
        b_bomb = findViewById(R.id.btn_bomb);
        target_img = findViewById(R.id.iv_target_img);
        target_name = findViewById(R.id.tv_targetName);
        txt_message = findViewById(R.id.et_inchat);
        rv_message = findViewById(R.id.rv_inchat);


        adapterOwner = new AdapterListViewInchatOwner(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rv_message.setLayoutManager(l);
        rv_message.setAdapter(adapterOwner);


        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!txt_message.getText().toString().equals("")) {

                    adapterOwner.addMessage(new EntityListItemInchatOwner("00:00", txt_message.getText().toString()));

                }
            }
        });


        adapterOwner.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
                txt_message.setText("");
            }
        });

    }


    private void setScrollbar(){

        rv_message.scrollToPosition(adapterOwner.getItemCount()-1);

    }

}