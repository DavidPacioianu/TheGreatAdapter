package com.pixelcan.thegreatadapter;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by David Pacioianu on 11/19/16.
 */

public interface GreatItem {
    int FULL_SPAN = -1;

    int getItemType();
    int getSpanSize();
    long getItemId();
    GreatViewHolder getItemViewHolder(ViewGroup root, Context context);
}