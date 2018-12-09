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
import com.example.elina.weatherapp.pojoClasses.Info;

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
    private java.util.List<Info> city;
    private double latitude, longitude;
    private int NUMBER_OF_CITIES = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = new ArrayList<>();
        cities = new ArrayList<>();
        list = findViewById(R.id.recycler_view);
        adapter = new CityAdapter(this, city);
        list.setAdapter(adapter);
        getCoordinates();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        checkPermissions();
    }

    @Override
    protected void onResume() {
        super.onResume();
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

        Call<Cities> call = openWeatherMapAPI.getData(latitude, longitude, NUMBER_OF_CITIES, API_KEY);
        call.enqueue(new Callback<Cities>() {

            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                if(response.isSuccessful()) {
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
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setList(Cities cities) {
        for (Info list : cities.getList()) {
            city.add(list);
        }
    }

    private void updateList() {
        adapter.notifyDataSetChanged();
    }
}
