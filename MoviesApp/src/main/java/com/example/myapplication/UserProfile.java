package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class UserProfile extends AppCompatActivity {

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







    MovieViewModel movieViewModel;
    List<User> listofusers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();

        MovieViewModel movieViewModel;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);


        final String name = intent.getStringExtra(EXTRA_NAME);
        final String phone = intent.getStringExtra(EXTRA_PHONENO);
        final String username = intent.getStringExtra(EXTRA_USERNAME);
        final String password = intent.getStringExtra(EXTRA_PASSWORD);
        final String email = intent.getStringExtra(EXTRA_EMAIL);
        final int id = intent.getIntExtra(EXTRA_ID,-1);
        setTitle("The profile of "+name);

        final ImageButton BackBut = findViewById(R.id.BackButton);
        final Button Backbut = findViewById(R.id.GoBAck);
        TextView nameText = findViewById(R.id.NameTextView);
        TextView phoneText = findViewById(R.id.phone);
        TextView usernameText = findViewById(R.id.username);
        TextView emailText = findViewById(R.id.email);
        nameText.setText(name);
        phoneText.setText(phone);
        emailText.setText(email);
        usernameText.setText(username);

        BackBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        Backbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });



    }

    public void goBack(){


        Intent data = new Intent();
        Intent intent = getIntent();
        final String name = intent.getStringExtra(EXTRA_NAME);
        final String phone = intent.getStringExtra(EXTRA_PHONENO);
        final String username = intent.getStringExtra(EXTRA_USERNAME);
        final String email = intent.getStringExtra(EXTRA_EMAIL);
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_PHONENO, phone);
        data.putExtra(EXTRA_USERNAME, username);
        data.putExtra(EXTRA_EMAIL, email);

        setResult(RESULT_OK, data);
        finish();
    }





}
