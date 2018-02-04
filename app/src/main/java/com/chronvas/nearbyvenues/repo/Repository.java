package com.chronvas.nearbyvenues.repo;

import android.content.Context;

import com.chronvas.nearbyvenues.repo.api.IApiSource;
import com.chronvas.nearbyvenues.repo.api.errors.ParsingThrowable;
import com.chronvas.nearbyvenues.repo.api.errors.ResponseThrowable;
import com.chronvas.nearbyvenues.repo.model.Search;
import com.chronvas.nearbyvenues.utils.scheduler.ISchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * This class may be used for having multiple sources of data. Eg remote and local (or cached data)
 */

public class Repository implements IRepository {

    private final IApiSource apiSource;
    private final ISchedulerProvider schedulerProvider;

    @Inject
    Context context;

    @Inject
    public Repository(IApiSource apiSource, ISchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        this.apiSource = apiSource;
    }

    @Override
    public Single<Search> getVenues(String venueName, String near) {
        if (apiSource == null) {
            Timber.e("apiSource == null");
            return Single.error(new Throwable("apiSource == null"));
        } else {
            return apiSource.getSearchVenueResult(venueName, near)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.computation())
                    .flatMap(listResult -> {
                        if (listResult.response() == null) {
                            if (listResult.error() != null && listResult.error().getMessage() != null) {
                                Timber.e(listResult.error(), "Unsucessful Parsing error: ");
                            }
                            return Single.error(new ParsingThrowable());
                        } else {
                            if (!listResult.response().isSuccessful()) {
                                Timber.e(listResult.error(), "Response error: ");
                                return Single.error(new ResponseThrowable());
                            } else {
                                Timber.e(": " + listResult.response().body());
                                return Single.just(listResult.response().body());
                            }
                        }
                    });
        }
    }
}
