package com.messageimposible.messageimpossible;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListViewInchatTarget extends RecyclerView.Adapter<HolderMessageTarget> {

    private Context c;
    private ArrayList<EntityListItemInchatTarget> messageList;

    public AdapterListViewInchatTarget(Context context) {

        this.c = context;

    }

    public void addMessage(EntityListItemInchatTarget m){

        messageList.add(m);

    }


    @NonNull
    @Override
    public HolderMessageTarget onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_listview_inchat_target, parent,false);

        return new HolderMessageTarget(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessageTarget holder, int position) {

        holder.getMessage().setText(messageList.get(position).getMessage());
        holder.getTime().setText(messageList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
