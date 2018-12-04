package com.example.elina.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    private TextView temp_tv;
    private TextView pressure_tv;
    private TextView humidity_tv;
    private TextView wind_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det);

        temp_tv = findViewById(R.id.temp_tv);
        pressure_tv = findViewById(R.id.pressure_tv);
        humidity_tv = findViewById(R.id.humidity);
        wind_tv = findViewById(R.id.wind_tv);

        temp_tv.setText();
        pressure_tv.setText();
        humidity_tv.setText();
        wind_tv.setText();
    }
}
