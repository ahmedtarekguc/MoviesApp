package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class AddEditMovie extends AppCompatActivity {

    public static final String EXTRA_TITLE =
            "com.example.myapplication.EXTRA_NAME";
    public static final String EXTRA_ID =
            "com.example.myapplication.EXTRA_ID";
    public static final String EXTRA_DESCRIPTION =
            "com.example.myapplication.EXTRA_DESCRIPTION";
    public static final String EXTRA_YEAR =
            "com.example.myapplication.EXTRA_YEAR";




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie);

        TextView NameTextView = findViewById(R.id.NameOfMovieTxt);
        TextView DescTextView = findViewById(R.id.DescOfMovieText);
        NumberPicker datePicker = findViewById(R.id.DatePickerMov);
        Button MovieSubmitButton = findViewById(R.id.MovieSubmitBut);

        datePicker.setValue(Calendar.getInstance().get(Calendar.YEAR));
        datePicker.setMaxValue(Calendar.getInstance().get(Calendar.YEAR));
        datePicker.setMinValue(1950);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_icon);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Movie");
            MovieSubmitButton.setText("Update movie");
            NameTextView.setText(intent.getStringExtra(EXTRA_TITLE));
            DescTextView.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            datePicker.setValue(intent.getIntExtra(EXTRA_YEAR,Calendar.getInstance().get(Calendar.YEAR)));
        }else{
            setTitle("Add Movie");
        }



        MovieSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMovie();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_movie:
                saveMovie();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    private void saveMovie() {
        TextView NameTextView = findViewById(R.id.NameOfMovieTxt);
        TextView DescTextView = findViewById(R.id.DescOfMovieText);
        NumberPicker datePicker = findViewById(R.id.DatePickerMov);
        String Name = NameTextView.getText().toString();
        String Descr = DescTextView.getText().toString();
        int year = datePicker.getValue();

        if(Name.trim().isEmpty() || Descr.trim().isEmpty()){
            Toast.makeText(this, "Please insert the movie name and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, Name);
        data.putExtra(EXTRA_DESCRIPTION, Descr);
        data.putExtra(EXTRA_YEAR, year);
        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!=-1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
