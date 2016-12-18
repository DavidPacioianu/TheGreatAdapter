package com.pixelcan.thegreatadapter.items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pixelcan.thegreatadapter.GreatItem;
import com.pixelcan.thegreatadapter.GreatViewHolder;
import com.pixelcan.thegreatadapter.R;
import com.pixelcan.thegreatadapter.utils.UniqueId;
import com.pixelcan.thegreatadapter.viewholders.SimpleViewHolder;

/**
 * Created by David Pacioianu on 12/11/16.
 */

public class SimpleViewItem extends UniqueId implements GreatItem {

    private final static int SIMPLE_ITEM_TYPE = -1;

    private final int spanSize;
    private View view;

    public SimpleViewItem() {
        this.spanSize = FULL_SPAN;
    }

    public SimpleViewItem(View view) {
        this.spanSize = FULL_SPAN;
        this.view = view;
    }

    public SimpleViewItem(int spanSize, View view) {
        this.spanSize = spanSize;
        this.view = view;
    }

    public View getView() {
        return view;
    }

    @Override
    public int getItemType() {
        return SIMPLE_ITEM_TYPE;
    }

    @Override
    public int getSpanSize() {
        return spanSize;
    }

    @Override
    public long getItemId() {
        return getUniqueId();
    }

    @Override
    public GreatViewHolder getItemViewHolder(ViewGroup root, Context context) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.item_view_container, root, false);
        return new SimpleViewHolder(context, view);
    }
}
