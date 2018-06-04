package com.messageimposible.messageimpossible.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.messageimposible.messageimpossible.Entity.EntityContact;
import com.messageimposible.messageimpossible.Entity.EntityListItemContact;
import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;

public class AdapterListViewContact extends BaseAdapter {
    private Context context;
    private ArrayList<EntityContact> listItems;

    public AdapterListViewContact(Context context, ArrayList<EntityContact> list) {
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
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EntityContact item = (EntityContact) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_contacts, null);

        ImageView img = convertView.findViewById(R.id.img_contact);
        TextView name = convertView.findViewById(R.id.name_contact);
        TextView online = convertView.findViewById(R.id.online);
        TextView last_connection = convertView.findViewById(R.id.last_connection_contact);

        img.setImageResource(item.getImg());
        name.setText(item.getUsername());
        //todo
        online.setText("online");
        last_connection.setText("last connection");

        return convertView;

    }
}
