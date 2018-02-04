package com.chronvas.nearbyvenues.repo.api;


import com.chronvas.nearbyvenues.repo.model.Search;

import io.reactivex.Single;
import retrofit2.adapter.rxjava2.Result;

public interface IApiSource {
    Single<Result<Search>> getSearchVenueResult(String venueName, String near);
}
