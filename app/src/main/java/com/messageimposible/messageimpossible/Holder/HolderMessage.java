package com.messageimposible.messageimpossible.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.messageimposible.messageimpossible.Entity.EntityMessageTarget;
import com.messageimposible.messageimpossible.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HolderMessage extends RecyclerView.ViewHolder{

    TextView message;
    TextView time;

    public HolderMessage(View itemView) {
        super(itemView);

        message = itemView.findViewById(R.id.message_owner);
        time = itemView.findViewById(R.id.time_owner);

    }

    public void bind(EntityMessageTarget item){

        message.setText(item.getMessage());

        Long timeCode = item.getTime();
        Date d = new Date(timeCode);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        time.setText(sdf.format(d));

    }

    public TextView getMessage() {
        return message;
    }

    public void setMessage(TextView message) {
        this.message = message;
    }

    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }
}
