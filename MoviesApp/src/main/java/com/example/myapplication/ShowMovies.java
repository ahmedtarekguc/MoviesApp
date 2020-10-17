package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowMovies extends AppCompatActivity {

    private MovieViewModel movieViewModel;


    public static final int ADD_MOVIE_REQUEST = 1;
    public static final int EDIT_MOVIE_REQUEST = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_movie);
        RecyclerView recyclerView = findViewById(R.id.recyclerviewMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton addbut = findViewById(R.id.AddMoviePlusBut);
        recyclerView.setHasFixedSize(true);
        setTitle("All Movies available");

        addbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ShowMovies.this, AddEditMovie.class);
                startActivityForResult(myIntent,ADD_MOVIE_REQUEST);
            }
        });

        final MovieAdapter adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.setMovie(movies);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                movieViewModel.delete(adapter.getMovieAt(viewHolder.getAdapterPosition()));
                Toast.makeText(ShowMovies.this, "Movie Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(ShowMovies.this, AddEditMovie.class);
                intent.putExtra(AddEditMovie.EXTRA_TITLE, movie.getName());
                intent.putExtra(AddEditMovie.EXTRA_ID, movie.getID());
                intent.putExtra(AddEditMovie.EXTRA_YEAR, movie.getProductionyear());
                intent.putExtra(AddEditMovie.EXTRA_DESCRIPTION, movie.getDescription());
                startActivityForResult(intent, EDIT_MOVIE_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_MOVIE_REQUEST && resultCode == RESULT_OK){
            String NameofMovie = data.getStringExtra(AddEditMovie.EXTRA_TITLE);
            int YearofMovie = data.getIntExtra(AddEditMovie.EXTRA_YEAR, 2020);
            String DesofMovie = data.getStringExtra(AddEditMovie.EXTRA_DESCRIPTION);
            Movie movie = new Movie(NameofMovie, DesofMovie, YearofMovie);
            movieViewModel.insert(movie);

            Toast.makeText(this, "Movie Saved", Toast.LENGTH_SHORT).show();

        } else if(requestCode==EDIT_MOVIE_REQUEST && resultCode == RESULT_OK){

            int id = data.getIntExtra(AddEditMovie.EXTRA_ID,-1);
            if(id==-1){
                Toast.makeText(this, "Movie can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String NameofMovie = data.getStringExtra(AddEditMovie.EXTRA_TITLE);
            int YearofMovie = data.getIntExtra(AddEditMovie.EXTRA_YEAR, 2020);
            String DesofMovie = data.getStringExtra(AddEditMovie.EXTRA_DESCRIPTION);
            Movie movie = new Movie(NameofMovie, DesofMovie, YearofMovie);
            movie.setID(id);
            movieViewModel.update(movie);
            Toast.makeText(this, "Movie updated successfully", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Could not add movie", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.show_movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteallmovies:
                movieViewModel.deleteAllMovies();
                Toast.makeText(this, "All movies deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
