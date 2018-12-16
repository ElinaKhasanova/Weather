package com.example.elina.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    private TextView name_tv;
    private TextView temp_tv;
    private TextView pressure_tv;
    private TextView humidity_tv;
    private TextView wind_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det);
        Intent intent = getIntent();

        name_tv = findViewById(R.id.name_tv);
        temp_tv = findViewById(R.id.temp_tv);
        pressure_tv = findViewById(R.id.pressure_tv);
        humidity_tv = findViewById(R.id.humidity_tv);
        wind_tv = findViewById(R.id.wind_tv);

        name_tv.setText(intent.getStringExtra("name").toString());
        temp_tv.setText(intent.getStringExtra("temp").toString());
        pressure_tv.setText(intent.getStringExtra("pressure").toString());
        humidity_tv.setText(intent.getStringExtra("humidity").toString());
        wind_tv.setText(intent.getStringExtra("wind").toString());
    }
}
