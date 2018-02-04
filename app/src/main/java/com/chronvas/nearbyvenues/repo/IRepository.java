package com.chronvas.nearbyvenues.repo;

import com.chronvas.nearbyvenues.repo.model.Search;

import io.reactivex.Single;

public interface IRepository {

    Single<Search> getVenues(String venueName, String near);
}
