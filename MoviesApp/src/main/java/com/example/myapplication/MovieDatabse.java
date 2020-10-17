package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Movie.class, User.class}, version = 1)
public abstract class MovieDatabse extends RoomDatabase {
    private static MovieDatabse instance;

    public abstract MovieDao movieDao();

    public static synchronized MovieDatabse getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MovieDatabse.class,"movie_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private MovieDao movieDao;
        private PopulateDbAsyncTask(MovieDatabse db){
            movieDao=db.movieDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.insert(new Movie("Creed 1", "A boxer who begins his career", 2015));
            movieDao.insert(new Movie("Creed 2", "A boxer who becomes champ", 2019));
            movieDao.insert(new User("Ahmed Tarek", "atamsn253@gmail.com", "hhhgiveano2?", "01095953445","ata253"));
            return null;
        }
    }
}
