package com.chronvas.nearbyvenues.repo.api.calls;

import com.chronvas.nearbyvenues.repo.model.Search;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IFourSquareSource {

    @GET("venues/search")
    Single<Result<Search>> getVenues(
            @QueryMap Map<String, String> parameters,
            @Query("query") String query,
            @Query("near") String latLong
    );
}
