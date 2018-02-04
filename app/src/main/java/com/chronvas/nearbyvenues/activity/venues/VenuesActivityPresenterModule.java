package com.chronvas.nearbyvenues.activity.venues;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = VenuesActivityPresenterComponent.class)
public abstract class VenuesActivityPresenterModule {

    @Binds
    @IntoMap
    @ActivityKey(VenuesActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bind(VenuesActivityPresenterComponent.Builder builder);

    @Binds
    abstract IVenuesActivityContract.Presenter listPresenter(VenuesActivityPresenter presenter);

    @Binds
    abstract IVenuesActivityContract.View listView(VenuesActivity view);
}
