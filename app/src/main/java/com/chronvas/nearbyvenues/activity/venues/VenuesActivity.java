package com.chronvas.nearbyvenues.activity.venues;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chronvas.nearbyvenues.R;
import com.chronvas.nearbyvenues.repo.model.Venue;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class VenuesActivity extends AppCompatActivity implements IVenuesActivityContract.View, VenueItemClickListener {

    @Inject
    IVenuesActivityContract.Presenter presenter;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.search_btn)
    Button searchButton;
    @BindView(R.id.nearby)
    EditText nearby;
    @BindView(R.id.venue_name)
    EditText venueName;

    private DividerItemDecoration dividerItemDecoration;
    private LinearLayoutManager linearLayoutManager;
    private VenuesAdapter venuesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.refresh());
        venuesAdapter = new VenuesAdapter(this);
        recyclerView.setAdapter(venuesAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
    }

    private void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(this, "Something went wrong. Pull to refresh", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setItems(List<Venue> venues) {
        setRefreshing(false);
        venuesAdapter.setData(venues);
        if (recyclerView.getLayoutManager() == null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        if (recyclerView.getItemDecorationAt(0) == null) {
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
    }

    @Override
    public void showEmptyValuesToast() {
        Toast.makeText(this, "Incorrect values", Toast.LENGTH_LONG).show();
        setRefreshing(false);
    }

    @Override
    public void venueItemClicked(Venue venue) {
        presenter.venueItemClicked(venue);
    }

    @Override
    public void streetViewIntent(String latitude, String longitude) {
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + latitude + "," +
                longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Google maps not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.search_btn)
    public void searchClicked() {
        hideKeyboard();
        presenter.searchClicked(venueName.getText().toString(), nearby.getText().toString());
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
