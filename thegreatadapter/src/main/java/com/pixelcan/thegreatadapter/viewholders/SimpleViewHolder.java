package com.pixelcan.thegreatadapter.viewholders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pixelcan.thegreatadapter.GreatItem;
import com.pixelcan.thegreatadapter.GreatViewHolder;
import com.pixelcan.thegreatadapter.R;

import com.pixelcan.thegreatadapter.items.SimpleViewItem;

/**
 * Created by David Pacioianu on 11/19/16.
 */

public class SimpleViewHolder extends GreatViewHolder {

    private LinearLayout parent;

    public SimpleViewHolder(Context context, View itemView) {
        super(itemView, context);
        parent = (LinearLayout) itemView.findViewById(R.id.container);
    }

    @Override
    public void bindType(GreatItem item) {
        SimpleViewItem simpleItem = (SimpleViewItem) item;
        View simpleView = simpleItem.getView();

        if (simpleView.getParent() != null) {
            ((ViewGroup) simpleView.getParent()).removeAllViews();
        }

        parent.removeAllViews();
        parent.addView(simpleItem.getView());
    }
}
