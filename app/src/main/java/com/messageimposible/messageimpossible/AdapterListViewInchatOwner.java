package com.messageimposible.messageimpossible;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterListViewInchatOwner extends RecyclerView.Adapter<HolderMessageOwner> {

    private List<EntityListItemInchatOwner> messageList = new ArrayList<>();
    private Context c;

    public AdapterListViewInchatOwner(Context c) {
        this.c = c;
    }

    public void addMessage(EntityListItemInchatOwner m){

        messageList.add(m);

        notifyItemInserted(messageList.size());

    }

    @NonNull
    @Override
    public HolderMessageOwner onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(c).inflate(R.layout.item_listview_inchat_owner, parent,false);

        return new HolderMessageOwner(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessageOwner holder, int position) {

        holder.getMessage().setText(messageList.get(position).getMessage());
        holder.getTime().setText(messageList.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}

