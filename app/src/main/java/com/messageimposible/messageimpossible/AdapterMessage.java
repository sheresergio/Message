package com.messageimposible.messageimpossible;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdapterMessage extends RecyclerView.Adapter<HolderMessage> {

    private List<EntityMessageTarget> messageList = new ArrayList<>();
    private Context c;

    public AdapterMessage(Context c) {
        this.c = c;
    }

    public void addMessage(EntityMessageTarget m){

        messageList.add(m);
        notifyItemInserted(messageList.size());

    }


    @NonNull
    @Override
    public HolderMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(c).inflate(R.layout.item_listview_inchat_owner, parent,false);

        return new HolderMessage(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage holder, int position) {

        holder.getMessage().setText(messageList.get(position).getMessage());

        Long timeCode = messageList.get(position).getTime();
        Date d = new Date(timeCode);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        holder.getTime().setText(sdf.format(d));

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

}

