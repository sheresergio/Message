package com.messageimposible.messageimpossible.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.messageimposible.messageimpossible.Entity.EntityListItemChat;
import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;

public class AdapterListViewChat extends BaseAdapter {
    private Context context;
    private ArrayList<EntityListItemChat> listItems;

    public AdapterListViewChat(Context context, ArrayList<EntityListItemChat> list) {
        this.context = context;
        this.listItems = list;

    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {

        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EntityListItemChat item = (EntityListItemChat) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_chat, null);

        ImageView img = convertView.findViewById(R.id.img_chat);
        TextView name = convertView.findViewById(R.id.name_chat);
        TextView last_message = convertView.findViewById(R.id.last_message_chat);
        TextView last_connection = convertView.findViewById(R.id.last_connection_chat);

        img.setImageResource(item.getImg());
        name.setText(item.getUsername());
        last_message.setText(item.getLastMessage());
        last_connection.setText(item.getLastConnection());

        return convertView;

    }
}
