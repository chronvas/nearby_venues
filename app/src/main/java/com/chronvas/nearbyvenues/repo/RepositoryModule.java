package com.chronvas.nearbyvenues.repo;

import com.chronvas.nearbyvenues.repo.api.ApiSource;
import com.chronvas.nearbyvenues.repo.api.IApiSource;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    abstract IRepository provideRepository(Repository repository);

    @Binds
    abstract IApiSource provideApiSource(ApiSource apiSource);
}
