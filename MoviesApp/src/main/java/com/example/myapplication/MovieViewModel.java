package com.example.myapplication;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repository;
    private LiveData<List<Movie>> allMovies;
    private LiveData<List<User>> allUsers;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository=new MovieRepository(application);
        allMovies=repository.getAllMovies();
        allUsers=repository.getAllUsers();
    }

    public void insert(Movie movie){
        repository.insert(movie);
    }

    public void update(Movie movie){
        repository.update(movie);
    }

    public void delete(Movie movie){
        repository.delete(movie);
    }

    public void deleteAllMovies(){
        repository.deleteAllMovies();
    }

    public LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }


    public void insert(User user){
        repository.insert(user);
    }

    public void update(User user){
        repository.update(user);
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
}
