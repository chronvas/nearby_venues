package com.chronvas.nearbyvenues;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
abstract class MainModule {

    @Provides
    static Context provideContext(Main main) {
        return main.getApplicationContext();
    }

    @Binds
    abstract Application application(Main main);
}
