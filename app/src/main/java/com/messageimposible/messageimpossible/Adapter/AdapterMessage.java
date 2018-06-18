package com.messageimposible.messageimpossible.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.messageimposible.messageimpossible.Entity.EntityMessage;
import com.messageimposible.messageimpossible.Entity.EntityMessageTarget;
import com.messageimposible.messageimpossible.Holder.HolderMessage;
import com.messageimposible.messageimpossible.Holder.HolderMessageTarget;
import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;
import java.util.List;


public class AdapterMessage extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<EntityMessageTarget> messageList = new ArrayList<>();
    private Context c;
    private String currentUserID;

    private static final int MESSAGE_OWNER = 0;
    private static final int MESSAGE_TARGET = 1;


    public AdapterMessage(Context c) {
        this.c = c;
    }

    public void addMessage(EntityMessageTarget m){

        messageList.add(m);
        notifyItemInserted(messageList.size());

    }

    public void setCurrentUserID(String id){

        this.currentUserID = id;

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == MESSAGE_OWNER) {
            View v = layoutInflater.inflate(R.layout.item_listview_inchat_owner, parent, false);

            return new HolderMessage(v);
        } else {
            View v = layoutInflater.inflate(R.layout.item_listview_inchat_target, parent, false);

            return new HolderMessageTarget(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EntityMessageTarget item = messageList.get(position);

        if(holder instanceof HolderMessage){

            ((HolderMessage) holder).bind(item);

        }else{

            ((HolderMessageTarget) holder).bind(item);

        }


    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position).getId().equals(currentUserID)) {
            return MESSAGE_OWNER;
        } else {
            return MESSAGE_TARGET;
        }

    }

    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);


    }
}