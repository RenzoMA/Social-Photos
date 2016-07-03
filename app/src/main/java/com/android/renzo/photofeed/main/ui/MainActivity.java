package com.android.renzo.photofeed.main.ui;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.renzo.photofeed.PhotoFeedApp;
import com.android.renzo.photofeed.PhotoListFragment;
import com.android.renzo.photofeed.PhotoMapFragment;
import com.android.renzo.photofeed.R;
import com.android.renzo.photofeed.login.ui.LoginActivity;
import com.android.renzo.photofeed.main.MainPresenter;
import com.android.renzo.photofeed.main.adapters.MainSectionsPagerAdapter;
import com.android.renzo.photofeed.main.events.MainEvent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Inject
    MainPresenter presenter;
    @Inject
    MainSectionsPagerAdapter adapter;
    @Inject
    SharedPreferences sharedPreferences;

    private Location lastKnowLocation;
    private GoogleApiClient apiClient;
    private PhotoFeedApp app;
    private boolean resolvingError = false;
    private static final int PERMISSIONS_REQUEST_LOCATION = 1;
    private static final int REQUEST_RESOLVE_ERROR = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        app = (PhotoFeedApp) getApplication();
        setupInjection();
        setupNavigation();
        setupGoogleAPIClient();
        presenter.onCreate();
    }

    private void setupGoogleAPIClient() {
        if (apiClient == null) {
            apiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }


    @Override
    protected void onStop() {
        apiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        apiClient.connect();
    }

    private void setupNavigation() {
        String email = sharedPreferences.getString(app.getEmailKey(), getString(R.string.app_name));
        toolbar.setTitle(email);
        setSupportActionBar(toolbar);

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);

    }

    private void setupInjection() {

        String[] titles = new String[] {getString(R.string.main_title_list),
                getString(R.string.main_title_map)};

        Fragment[] fragments = new Fragment[]{new PhotoListFragment(),
        new PhotoMapFragment()};

        adapter = new MainSectionsPagerAdapter(getSupportFragmentManager(), titles, fragments);
        sharedPreferences = getSharedPreferences(app.getSharedPreferencesName(), MODE_PRIVATE);
        presenter = new MainPresenter() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public void logout() {

            }

            @Override
            public void uploadPhoto(Location location, String path) {

            }

            @Override
            public void onEVentMainThread(MainEvent event) {

            }
        };

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        presenter.logout();
        sharedPreferences.edit().clear().commit();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[]{ Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
            }

            return;
        }
        if(LocationServices.FusedLocationApi.getLocationAvailability(apiClient).isLocationAvailable()){
            lastKnowLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            Snackbar.make(viewPager, lastKnowLocation.toString(), Snackbar.LENGTH_SHORT).show();
        }else{
            Snackbar.make(viewPager, R.string.main_error_location_notavailable, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSIONS_REQUEST_LOCATION: {
                if(grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(LocationServices.FusedLocationApi.getLocationAvailability(apiClient).isLocationAvailable()){
                        lastKnowLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                    }else{
                        Snackbar.make(viewPager, R.string.main_error_location_notavailable, Snackbar.LENGTH_SHORT).show();
                    }

                }
                return;
            }
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if(resolvingError){
            return;
        }else if(connectionResult.hasResolution()){
            resolvingError = true;
            try {
                connectionResult.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            resolvingError = true;
            GoogleApiAvailability.getInstance().getErrorDialog(this, connectionResult.getErrorCode(), REQUEST_RESOLVE_ERROR).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_RESOLVE_ERROR){
            resolvingError = false;
            if(resultCode == RESULT_OK){
                if(!apiClient.isConnecting() && apiClient.isConnected()){
                    apiClient.connect();
                }

            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        apiClient.connect();
    }

    @Override
    public void onUploadInit() {

    }

    @Override
    public void onUploadComplete() {

    }

    @Override
    public void onUploadError(String error) {

    }
}
