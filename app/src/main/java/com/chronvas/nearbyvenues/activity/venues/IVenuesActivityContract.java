package com.chronvas.nearbyvenues.activity.venues;

import com.chronvas.nearbyvenues.repo.model.Venue;

import java.util.List;

interface IVenuesActivityContract {
    interface View {
        void showErrorToast();

        void setItems(List<Venue> items);

        void showEmptyValuesToast();

        void streetViewIntent(String latitude, String longitude);
    }

    interface Presenter {
        void onDestroy();

        void refresh();

        void venueItemClicked(Venue venue);

        void searchClicked(String nearby, String venueName);
    }
}
