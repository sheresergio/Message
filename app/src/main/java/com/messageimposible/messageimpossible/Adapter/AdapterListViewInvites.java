package com.messageimposible.messageimpossible.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.messageimposible.messageimpossible.Entity.EntityListItemInvites;
import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;

public class AdapterListViewInvites extends BaseAdapter {
    private Context context;
    private ArrayList<EntityListItemInvites> listItems;

    public AdapterListViewInvites(Context context, ArrayList<EntityListItemInvites> list) {
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

        EntityListItemInvites item = (EntityListItemInvites) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_invites, null);

        ImageView img = convertView.findViewById(R.id.img_invites);
        TextView name = convertView.findViewById(R.id.name_invites);
        TextView invite_message = convertView.findViewById(R.id.invite_message);

        img.setImageResource(item.getImg());
        name.setText(item.getUsername());
        invite_message.setText(item.getInviteMessage());

        return convertView;

    }

    public void delete(int position){

        listItems.remove(position);

    }

}
