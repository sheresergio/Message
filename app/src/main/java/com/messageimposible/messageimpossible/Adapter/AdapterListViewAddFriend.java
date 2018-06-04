package com.messageimposible.messageimpossible.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.messageimposible.messageimpossible.Entity.EntityUsers;
import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;

public class AdapterListViewAddFriend extends BaseAdapter {
    private Context context;
    private ArrayList<EntityUsers> listItems;

    public AdapterListViewAddFriend(Context context, ArrayList<EntityUsers> list) {
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

        EntityUsers item = (EntityUsers) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_add_friend, null);

        ImageView img = convertView.findViewById(R.id.img_friend);
        TextView name = convertView.findViewById(R.id.name_friend);
        TextView email = convertView.findViewById(R.id.email_friend);

        img.setImageResource(item.getImg());
        name.setText(item.getUsername());
        email.setText(item.getEmail());

        return convertView;

    }
}
