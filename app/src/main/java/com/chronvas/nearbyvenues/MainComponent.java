package com.chronvas.nearbyvenues;

import com.chronvas.nearbyvenues.activity.venues.VenuesActivityPresenterModule;
import com.chronvas.nearbyvenues.repo.RepositoryModule;
import com.chronvas.nearbyvenues.utils.network.NetworkModule;
import com.chronvas.nearbyvenues.utils.scheduler.SchedulerProviderModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
        modules = {
                AndroidSupportInjectionModule.class, //mandatory
                MainModule.class,
                VenuesActivityPresenterModule.class,
                RepositoryModule.class,
                SchedulerProviderModule.class,
                NetworkModule.class
        }
)
@Singleton
public interface MainComponent extends AndroidInjector<Main> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<Main> {
    }
}
