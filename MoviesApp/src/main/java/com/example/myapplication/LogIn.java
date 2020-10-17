package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class LogIn extends AppCompatActivity {

    private MovieViewModel movieViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        final List<User> listofusers = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTitle("LogIn Page");
        final Button LoginBut = (Button)findViewById(R.id.LogInButton);
        final TextView userText = findViewById(R.id.Username);
        final TextView PassText = findViewById(R.id.PasswordLogIn);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                int x = users.size();
                String m = Integer.toString(users.size());
                //Toast.makeText(NewAccount.this, m, Toast.LENGTH_SHORT).show();
                for(int i=0;i< x; i++){
                    User okk = new User(users.get(i).getName(),users.get(i).getEmail(),users.get(i).getPassword(),users.get(i).getPhonenumber(),users.get(i).getUsername());
                    listofusers.add(okk);
                }

            }
        });



        LoginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userText.getText().toString().isEmpty() || PassText.getText().toString().isEmpty()){
                    Toast.makeText(LogIn.this, "Please provide a username and the password", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean loggedin=false;
                for(User s:listofusers){
                    if(userText.getText().toString().equals(s.getUsername())){
                        if(PassText.getText().toString().equals(s.getPassword())){
                            Intent intent = new Intent(LogIn.this, MainLoggedInPage.class);
                            intent.putExtra(MainLoggedInPage.EXTRA_NAME, s.getName());
                            intent.putExtra(MainLoggedInPage.EXTRA_ID, s.getId());
                            intent.putExtra(MainLoggedInPage.EXTRA_EMAIL, s.getEmail());
                            intent.putExtra(MainLoggedInPage.EXTRA_PHONENO, s.getPhonenumber());
                            intent.putExtra(MainLoggedInPage.EXTRA_USERNAME, s.getUsername());
                            intent.putExtra(MainLoggedInPage.EXTRA_PASSWORD, s.getPassword());
                            loggedin=true;
                            Toast.makeText(LogIn.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivityForResult(intent, 1);
                        }
                    }
                }
                if(loggedin==false){
                    Toast.makeText(LogIn.this, "Please check the username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

    }


}
