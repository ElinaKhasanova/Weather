package com.example.elina.weatherapp.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.elina.weatherapp.pojoClasses.Info;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;

public interface Dao {
    @Query("SELECT * FROM cities")
    Single<List<Info>> getAll();

    @Query("SELECT * FROM cities WHERE id = :id")
    Info getById(Integer id);

    @Insert
    void insert (Call info);

    @Update
    void update (Info info);

    @Delete
    void delete(Info info);
}
