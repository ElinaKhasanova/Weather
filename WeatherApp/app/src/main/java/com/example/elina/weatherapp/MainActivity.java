package com.example.elina.weatherapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.elina.weatherapp.pojoClasses.Cities;
import com.example.elina.weatherapp.pojoClasses.List;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.elina.weatherapp.PermissionChecker.RuntimePermissions.PERMISSION_REQUEST_FINE_LOCATION;

public class MainActivity extends AppCompatActivity implements PermissionCallback {

    public final String API_KEY = "e6273efa63bd8b86d404eb266e105d0e";

    private java.util.List<Cities> cities;
    private LocationManager locationManager;
    private CityAdapter adapter;
    private RecyclerView list;
    private double latitude, longitude;
    private int numberOfCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cities = new ArrayList<>();
        list = findViewById(R.id.recycle_view);
        adapter = new CityAdapter(this, cities);
        list.setAdapter(adapter);

        numberOfCities = 20;

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        checkPermissions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCoordinates();
        getCities();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_FINE_LOCATION.VALUE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionRec(PERMISSION_REQUEST_FINE_LOCATION);
            } else {
                permissionNotRec(PERMISSION_REQUEST_FINE_LOCATION);
            }
        }
    }

    @Override
    public void permissionRec(PermissionChecker.RuntimePermissions permission) {
    }

    @Override
    public void permissionNotRec(PermissionChecker.RuntimePermissions permission) {
    }

    private void checkPermissions() {
        PermissionChecker permissionChecker = new PermissionChecker();
        permissionChecker.checkForPermissions(this, PERMISSION_REQUEST_FINE_LOCATION, this);
    }

    private boolean getCoordinates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return false;
        latitude = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude();
        longitude = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude();
        return true;
    }

    private void getCities() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OpenWeatherMapApi openWeatherMapAPI = retrofit.create(OpenWeatherMapApi.class);

        Call<Cities> call = openWeatherMapAPI.getData(latitude, longitude, numberOfCities, API_KEY);
        call.enqueue(new Callback<Cities>() {

            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {

                if(response.code() != 200) {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_LONG).show();
                    return;
                }

                if(response.body() != null) {
                    setList(response.body());
                    updateList();
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
            }
        });
    }

    private void setList(Cities cities) {
        for (List list : cities.getList()) {
            list.add();
        }
    }

    private void updateList() {
        adapter.notifyDataSetChanged();
    }
}
