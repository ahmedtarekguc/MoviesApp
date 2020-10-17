package com.example.myapplication;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MovieDao {

    @Insert
    void insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movie_table ")
    void deleteAllMovies();

    @Query("SELECT * FROM movie_table ORDER BY productionyear DESC")
    LiveData<List<Movie>> getAllMovies();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM users_table WHERE username= :usernameinput")
    LiveData<User> getUser(String usernameinput);

    @Query("SELECT * FROM users_table")
    LiveData<List<User>> getAllUsers();

}
