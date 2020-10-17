package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainLoggedInPage extends AppCompatActivity {

    public int USERPAGE = 3;
    public static final String EXTRA_NAME =
            "com.example.myapplication.EXTRA_NAME";
    public static final String EXTRA_ID =
            "com.example.myapplication.EXTRA_ID";
    public static final String EXTRA_USERNAME =
            "com.example.myapplication.EXTRA_USERNAME";
    public static final String EXTRA_EMAIL =
            "com.example.myapplication.EXTRA_EMAIL";
    public static final String EXTRA_PHONENO =
            "com.example.myapplication.EXTRA_PHONENO";
    public static final String EXTRA_PASSWORD =
            "com.example.myapplication.EXTRA_PASSWORD";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainloggedinpage);
        setTitle("Main page");

        Intent intent = getIntent();
        final String EXTRANAME = intent.getStringExtra(EXTRA_NAME);
        final int EXTRAID = intent.getIntExtra(EXTRA_ID,-1);
        final String EXTRAUSERNAME = intent.getStringExtra(EXTRA_USERNAME);
        final String EXTRAEMAIL = intent.getStringExtra(EXTRA_EMAIL);
        final String EXTRAPHONENO = intent.getStringExtra(EXTRA_PHONENO);
        final String EXTRAPASSWORD = intent.getStringExtra(EXTRA_PASSWORD);
        final Button PersonalPageBut = findViewById(R.id.PersonalBut);
        final Button signoutbutton = findViewById(R.id.signoutbut);
        final Button ShowMoviesButton = findViewById(R.id.showMoviesBut);
        PersonalPageBut.setText(EXTRANAME);
        TextView hello = findViewById(R.id.HelloText);
        hello.setText("Hello, " + EXTRANAME);

        PersonalPageBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainLoggedInPage.this, UserProfile.class);
                intent.putExtra(UserProfile.EXTRA_NAME, EXTRANAME);
                intent.putExtra(UserProfile.EXTRA_ID, EXTRAID);
                intent.putExtra(UserProfile.EXTRA_EMAIL, EXTRAEMAIL);
                intent.putExtra(UserProfile.EXTRA_PHONENO, EXTRAPHONENO);
                intent.putExtra(UserProfile.EXTRA_USERNAME, EXTRAUSERNAME);
                intent.putExtra(UserProfile.EXTRA_PASSWORD, EXTRAPASSWORD);
                startActivityForResult(intent, USERPAGE);
            }
        });


        signoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        ShowMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ShowMovies.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }

    @Override


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Returned", Toast.LENGTH_SHORT).show();

        }


        if (requestCode == USERPAGE && resultCode == RESULT_OK) {
            Toast.makeText(this, "Returned from user page", Toast.LENGTH_SHORT).show();

        }
    }
}
