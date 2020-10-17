package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AtaMessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
        Button LogInbutton = (Button)findViewById(R.id.Button1);
        LogInbutton.setTextColor(Color.BLUE);

        LogInbutton.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          Intent myIntent = new Intent(view.getContext(),LogIn.class);
                                          startActivityForResult(myIntent,0);
                                      }
                                  }

        );


        final Button NewAccountButton = (Button)findViewById(R.id.button2);
        NewAccountButton.setTextColor(Color.BLUE);

        NewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),NewAccount.class);
                startActivityForResult(myIntent,0);
            }
        });


    }

}