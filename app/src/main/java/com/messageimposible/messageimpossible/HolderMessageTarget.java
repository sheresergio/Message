package com.messageimposible.messageimpossible;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class HolderMessageTarget extends RecyclerView.ViewHolder{

    TextView message;
    TextView time;

    public HolderMessageTarget(View itemView) {
        super(itemView);

        message = itemView.findViewById(R.id.message_target);
        time = itemView.findViewById(R.id.time_target);

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
