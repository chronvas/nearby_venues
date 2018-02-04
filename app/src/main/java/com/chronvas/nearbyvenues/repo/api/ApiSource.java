package com.chronvas.nearbyvenues.repo.api;

import com.chronvas.nearbyvenues.repo.api.calls.IFourSquareSource;
import com.chronvas.nearbyvenues.repo.model.Search;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;

public class ApiSource implements IApiSource {

    private final Retrofit retrofit;

    @Inject
    public ApiSource(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Single<Result<Search>> getSearchVenueResult(String venueName, String near) {
        IFourSquareSource contentSource = retrofit.create(IFourSquareSource.class);
        return contentSource.getVenues(Helper.QUERY_CONSTANTS, venueName, near);
    }
}
