package com.example.elina.weatherapp.database;

import android.app.Application;
import android.arch.persistence.room.Room;

public class App extends Application {

    //private static final String DB_NAME = "database";
    public static App instance;
    private AppDb appDb;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDb = Room.databaseBuilder(getApplicationContext(),
                AppDb.class, "db").build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDb getDatabase() {
        return appDb;
    }
}
