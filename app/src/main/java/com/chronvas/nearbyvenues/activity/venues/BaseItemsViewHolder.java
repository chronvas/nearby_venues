package com.chronvas.nearbyvenues.activity.venues;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chronvas.nearbyvenues.repo.model.Venue;

abstract class BaseItemsViewHolder extends RecyclerView.ViewHolder {
    public BaseItemsViewHolder(View view) {
        super(view);
    }

    abstract void bind(Venue venue);
}
