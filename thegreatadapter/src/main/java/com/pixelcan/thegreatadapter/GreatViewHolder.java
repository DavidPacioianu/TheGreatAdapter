package com.pixelcan.thegreatadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by David Pacioianu on 11/19/16.
 */

public class GreatViewHolder extends RecyclerView.ViewHolder {

    public final Context context;

    public GreatViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
    }

    public void bindType(GreatItem item){

    }
}