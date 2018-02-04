package com.chronvas.nearbyvenues.activity.venues;

import com.chronvas.nearbyvenues.injection.ActivityScoped;
import com.chronvas.nearbyvenues.repo.IRepository;
import com.chronvas.nearbyvenues.repo.model.Venue;
import com.chronvas.nearbyvenues.utils.scheduler.ISchedulerProvider;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

@ActivityScoped
public class VenuesActivityPresenter implements IVenuesActivityContract.Presenter {

    private final IRepository repository;
    private final ISchedulerProvider schedulerProvider;
    private IVenuesActivityContract.View view;
    private CompositeDisposable disposables = new CompositeDisposable();
    private String venueName;
    private String nearby;

    @Inject
    public VenuesActivityPresenter(IVenuesActivityContract.View view, IRepository repository,
                                   ISchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        this.repository = repository;
        this.view = view;
        this.disposables = new CompositeDisposable();
    }

    private void searchForVenues(String venueName, String nearby) {
        this.nearby = nearby;
        this.venueName = venueName;
        if (StringUtils.isAnyBlank(venueName, nearby)) {
            view.showEmptyValuesToast();
        } else {
            refreshDisposables();
            disposables.add(repository.getVenues(venueName, nearby)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(searchResponse -> {
                        view.setItems(searchResponse.getResponse().getVenues());
                    }, throwable -> {
                        view.showErrorToast();
                        Timber.e(throwable, "throwable: ");
                    })
            );
        }
    }

    private void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void refreshDisposables() {
        dispose();
        disposables = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        dispose();
    }

    @Override
    public void refresh() {
        searchForVenues(venueName, nearby);
    }

    @Override
    public void venueItemClicked(Venue venue) {
        view.streetViewIntent(venue.getLocation().getLat().toString(), venue.getLocation().getLng().toString());
    }

    @Override
    public void searchClicked(String venueName, String nearBy) {
        searchForVenues(venueName, nearBy);
    }
}
