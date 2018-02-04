package com.chronvas.nearbyvenues.activity.venues;

import com.chronvas.nearbyvenues.injection.ActivityScoped;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScoped
@Subcomponent
public interface VenuesActivityPresenterComponent extends AndroidInjector<VenuesActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<VenuesActivity> {}
}