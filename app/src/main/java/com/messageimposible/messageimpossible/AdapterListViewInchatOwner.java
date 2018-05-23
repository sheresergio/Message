package com.messageimposible.messageimpossible;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListViewInchatOwner extends BaseAdapter {

    private Context context;
    private ArrayList<EntityListItemContact> listItems;

    public AdapterListViewInchatOwner(Context context, ArrayList<EntityListItemContact> list) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EntityListItemInchatOwner item = (EntityListItemInchatOwner) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_inchat_owner, null);

        TextView time = convertView.findViewById(R.id.online);
        TextView message = convertView.findViewById(R.id.last_connection_contact);

        time.setText(item.getTime());
        message.setText(item.getMessage());

        return convertView;

    }
}
