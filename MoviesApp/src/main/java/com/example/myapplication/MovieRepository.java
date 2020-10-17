package com.example.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MovieRepository {
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;
    private LiveData<List<User>> allUsers;

    public MovieRepository(Application application){
        MovieDatabse database = MovieDatabse.getInstance(application);
        movieDao = database.movieDao();
        allMovies = movieDao.getAllMovies();
        allUsers = movieDao.getAllUsers();
    }


    public void insert(Movie movie){

        new InsertMovieAsyncTask(movieDao).execute(movie);
    }

    public void update(Movie movie){

        new UpdateMovieAsyncTask(movieDao).execute(movie);
    }

    public void delete(Movie movie){

        new DeleteMovieAsyncTask(movieDao).execute(movie);
    }

    public void deleteAllMovies(){

        new DeleteAllMoviesAsyncTask(movieDao).execute();
    }



    public void insert(User user){
        new InsertUserAsyncTask(movieDao).execute(user);
    }


    public void update(User user){

        new UpdateUserAsyncTask(movieDao).execute(user);
    }





    public LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    private static class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Void>{
        private MovieDao movieDao;

        private InsertMovieAsyncTask(MovieDao movieDao){
            this.movieDao=movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.insert(movies[0]);
            return null;
        }
    }


    private static class UpdateMovieAsyncTask extends AsyncTask<Movie, Void, Void>{
        private MovieDao movieDao;

        private UpdateMovieAsyncTask(MovieDao movieDao){
            this.movieDao=movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.update(movies[0]);
            return null;
        }
    }

    private static class DeleteMovieAsyncTask extends AsyncTask<Movie, Void, Void>{
        private MovieDao movieDao;

        private DeleteMovieAsyncTask(MovieDao movieDao){
            this.movieDao=movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.delete(movies[0]);
            return null;
        }
    }

    private static class DeleteAllMoviesAsyncTask extends AsyncTask<Void, Void, Void>{
        private MovieDao movieDao;

        private DeleteAllMoviesAsyncTask(MovieDao movieDao){
            this.movieDao=movieDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.deleteAllMovies();
            return null;
        }
    }


    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void>{
        private MovieDao movieDao;

        private InsertUserAsyncTask(MovieDao movieDao){
            this.movieDao=movieDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            movieDao.insert(users[0]);
            return null;
        }
    }


    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void>{
        private MovieDao movieDao;

        private UpdateUserAsyncTask(MovieDao movieDao){
            this.movieDao=movieDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            movieDao.update(users[0]);
            return null;
        }
    }
}
