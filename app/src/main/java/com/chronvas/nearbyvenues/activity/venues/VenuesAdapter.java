package com.chronvas.nearbyvenues.activity.venues;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chronvas.nearbyvenues.R;
import com.chronvas.nearbyvenues.repo.model.Venue;

import java.util.List;

import timber.log.Timber;

public class VenuesAdapter extends RecyclerView.Adapter<BaseItemsViewHolder> {

    private VenueItemClickListener listener;
    private List<Venue> venues;

    public VenuesAdapter(@NonNull VenueItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Setter for the data to be displayed.
     *
     * @param venues The Venues list to be displayed
     */
    public void setData(List<Venue> venues) {
        this.venues = venues;
        notifyDataSetChanged();
    }

    @Override
    public BaseItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ContentItemViewHolder(inflater.inflate(R.layout.item_post, parent, false), parent, listener);
    }


    @Override
    public void onBindViewHolder(BaseItemsViewHolder holder, final int position) {
        holder.bind(venues.get(position));
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }


    private static class ContentItemViewHolder extends BaseItemsViewHolder {
        private final TextView venueNameTextView;
        private final TextView venueAddressTextView;
        private final TextView venueUrlTextView;
        private final View view;
        private final ViewGroup parent;
        private final VenueItemClickListener listener;

        ContentItemViewHolder(View view, ViewGroup parent, VenueItemClickListener listener) {
            super(view);
            this.view = view;
            this.parent = parent;
            this.listener = listener;
            this.venueNameTextView = itemView.findViewById(R.id.item_vemue_name);
            this.venueAddressTextView = itemView.findViewById(R.id.item_venue_address);
            this.venueUrlTextView = itemView.findViewById(R.id.item_venue_url);
        }

        @Override
        public void bind(Venue venue) {
            if (venue == null) {
                return;
            }
            if (listener == null) {
                Timber.e("calling class must implement: VenueItemClickListener");
                return;
            }
            view.setOnClickListener(v -> listener.venueItemClicked(venue));

            venueNameTextView.setText(venue.getName());
            venueAddressTextView.setText(venue.getLocation().getAddress());
            venueUrlTextView.setText(venue.getUrl());
        }
    }
}

