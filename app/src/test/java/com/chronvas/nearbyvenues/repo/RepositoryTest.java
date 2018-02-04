package com.chronvas.nearbyvenues.repo;

import com.chronvas.nearbyvenues.repo.api.IApiSource;
import com.chronvas.nearbyvenues.repo.api.errors.ParsingThrowable;
import com.chronvas.nearbyvenues.repo.api.errors.ResponseThrowable;
import com.chronvas.nearbyvenues.repo.model.Search;
import com.chronvas.nearbyvenues.utils.scheduler.ISchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class RepositoryTest {
    private final static String APPLICATION_JSON = "application/json";
    private static final MediaType jsonUtf8MediaType = MediaType.parse(APPLICATION_JSON + "; charset=utf-8");

    private final static String SEARCH_NEARBY_LOCATION = "London";
    private final static String SEARCH_VENUE_NAME = "Coffee";

    Repository repository;

    @Mock
    IApiSource apiSource;
    @Mock
    ISchedulerProvider schedulerProvider;

    private Search mockSearch = new Search();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(schedulerProvider.computation()).thenReturn(Schedulers.trampoline());
        when(schedulerProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulerProvider.ui()).thenReturn(Schedulers.trampoline());

        repository = new Repository(apiSource, schedulerProvider);
    }

    @Test
    public void getContentList_noError_returnExpectedResponse() throws Exception {
        Result<Search> mockResult = Result.response(Response.success(mockSearch));
        when(apiSource.getSearchVenueResult(anyString(), anyString())).thenReturn(Single.just(mockResult));

        Single<Search> contentResponseSingle = repository.getVenues(SEARCH_VENUE_NAME, SEARCH_NEARBY_LOCATION);

        TestObserver<Search> testObserver = new TestObserver<>();
        contentResponseSingle.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertValueCount(1);
        assertEquals(mockSearch, testObserver.values().get(0));
    }

    @Test
    public void getContentList_errorResponse_returnUnsucessfulParsingThrowable() throws Exception {
        Result<Search> mockResult = Result.error(new ParsingThrowable());
        when(apiSource.getSearchVenueResult(anyString(), anyString())).thenReturn(Single.just(mockResult));

        Single<Search> contentResponseSingle = repository.getVenues(SEARCH_VENUE_NAME, SEARCH_NEARBY_LOCATION);

        TestObserver<Search> testObserver = new TestObserver<>();
        contentResponseSingle.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertError(ParsingThrowable.class);
    }

    @Test
    public void getContentList_unsuccessfulResponse_returnUnsucessfulParsingThrowable() throws Exception {
        Result<Search> mockResult = Result.response(Response.error(400,
                ResponseBody.create(jsonUtf8MediaType, "")));
        when(apiSource.getSearchVenueResult(anyString(), anyString())).thenReturn(Single.just(mockResult));

        Single<Search> contentResponseSingle = repository.getVenues(SEARCH_VENUE_NAME, SEARCH_NEARBY_LOCATION);

        TestObserver<Search> testObserver = new TestObserver<>();
        contentResponseSingle.subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertError(ResponseThrowable.class);
    }
}