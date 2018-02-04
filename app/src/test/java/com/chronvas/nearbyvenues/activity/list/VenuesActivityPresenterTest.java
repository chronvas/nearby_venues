package com.chronvas.nearbyvenues.activity.list;

import com.chronvas.nearbyvenues.activity.venues.IVenuesActivityContract;
import com.chronvas.nearbyvenues.activity.venues.VenuesActivityPresenter;
import com.chronvas.nearbyvenues.repo.IRepository;
import com.chronvas.nearbyvenues.repo.model.Location;
import com.chronvas.nearbyvenues.repo.model.Search;
import com.chronvas.nearbyvenues.repo.model.SearchResponse;
import com.chronvas.nearbyvenues.repo.model.Venue;
import com.chronvas.nearbyvenues.utils.scheduler.ISchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VenuesActivityPresenterTest {

    private final static String SEARCH_NEARBY_LOCATION = "London";
    private final static String SEARCH_VENUE_NAME = "Coffee";
    private final static String MOCK_VENUE_1_NAME = "mock venue 1 name";
    private final static String MOCK_VENUE_2_NAME = "mock venue 2 name";
    private static final double mockVenue1LocationLatitude = 11d;
    private static final double mockVenue1LocationLongtitude = 12d;
    private static final double mockVenue2LocationLatitude = 13d;
    private static final double mockVenue2LocationLongtitude = 14d;

    private VenuesActivityPresenter presenter;

    private Venue mockVenue1 = new Venue();
    private Location mockVenue1Location = new Location();
    private Location mockVenue2Location = new Location();
    private Venue mockVenue2 = new Venue();
    private List<Venue> mockVenues = Collections.emptyList();
    private SearchResponse mockSearchResponse = new SearchResponse();
    private Search mockSearch = new Search();

    @Mock
    private IVenuesActivityContract.View view;
    @Mock
    private IRepository repository;
    @Mock
    private ISchedulerProvider schedulerProvider;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(schedulerProvider.computation()).thenReturn(Schedulers.trampoline());
        when(schedulerProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulerProvider.ui()).thenReturn(Schedulers.trampoline());

        presenter = new VenuesActivityPresenter(view, repository, schedulerProvider);
        setupMocks();
    }

    private void setupMocks() {
        mockVenue1Location.setLat(mockVenue1LocationLatitude);
        mockVenue1Location.setLng(mockVenue1LocationLongtitude);
        mockVenue2Location.setLat(mockVenue2LocationLatitude);
        mockVenue2Location.setLng(mockVenue2LocationLongtitude);

        mockVenue1.setName(MOCK_VENUE_1_NAME);
        mockVenue1.setLocation(mockVenue1Location);

        mockVenue2.setName(MOCK_VENUE_2_NAME);
        mockVenue2.setLocation(mockVenue2Location);

        mockVenues = Arrays.asList(mockVenue1, mockVenue2);
        mockSearchResponse.setVenues(mockVenues);
        mockSearch.setResponse(mockSearchResponse);
    }

    //region venueItemClicked
    @Test
    public void venueClicked_noError() throws Exception {
        when(repository.getVenues(anyString(), anyString())).thenReturn(Single.just(mockSearch));
        presenter.venueItemClicked(mockVenues.get(0));
        verify(view).streetViewIntent(String.valueOf(mockVenue1LocationLatitude), String.valueOf(mockVenue1LocationLongtitude));
    }
    //endregion


    //region search text
    @Test
    public void search_validText_searchNoError_displayResults() throws Exception {
        when(repository.getVenues(anyString(), anyString())).thenReturn(Single.just(mockSearch));
        presenter.searchClicked(SEARCH_VENUE_NAME, SEARCH_NEARBY_LOCATION);
        verify(view).setItems(mockVenues);
    }

    @Test
    public void search_validText_searchError_displayErrorToast() throws Exception {
        when(repository.getVenues(anyString(), anyString())).thenReturn(Single.error(new Throwable()));
        presenter.searchClicked(SEARCH_VENUE_NAME, SEARCH_NEARBY_LOCATION);
        verify(view).showErrorToast();
    }

    @Test
    public void search_invalidText_searchError_displayErrorToast() throws Exception {
        presenter.searchClicked("", "");
        verify(view).showEmptyValuesToast();
    }
    //endregion

    //region refresh
    @Test
    public void refresh_noError_flow() throws Exception {
        when(repository.getVenues(SEARCH_VENUE_NAME, SEARCH_NEARBY_LOCATION)).thenReturn(Single.just(mockSearch));
        presenter.searchClicked(SEARCH_VENUE_NAME, SEARCH_NEARBY_LOCATION);
        presenter.refresh();
        verify(view, times(2)).setItems(mockVenues);
    }

    @Test
    public void refresh_error_noitfyUser() throws Exception {
        when(repository.getVenues(SEARCH_VENUE_NAME, SEARCH_NEARBY_LOCATION)).thenReturn(Single.just(mockSearch));
        presenter.searchClicked(SEARCH_VENUE_NAME, SEARCH_NEARBY_LOCATION);
        when(repository.getVenues(SEARCH_VENUE_NAME, SEARCH_NEARBY_LOCATION)).thenReturn(Single.error(new Throwable()));
        presenter.refresh();
        verify(view, times(1)).setItems(mockVenues);
        verify(view).showErrorToast();
    }
    //endregion
}