package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class NewAccount extends AppCompatActivity {

    public int x;
    private MovieViewModel movieViewModel;




    protected void onCreate(Bundle savedInstanceState) {
        final List<User> listofusers = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaccount);
        setTitle("Create a New Account");

        final Button submitButton = (Button)findViewById(R.id.SubmitButton);
        final EditText pass = findViewById(R.id.PassowrdText);
        final EditText pass2 = findViewById(R.id.ConfirmPassword);
        //final TextView passwordCor = findViewById(R.id.PasswordCorr);
        final EditText phone = findViewById(R.id.PhoneNumberText);
        final EditText name = findViewById(R.id.NameText);
        final EditText email = findViewById(R.id.EmailText);
        final EditText username =findViewById(R.id.UsernameText);
        //passwordCor.setVisibility(View.INVISIBLE);

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
        submitButton.setOnClickListener(new View.OnClickListener() {


                                            @Override
                                            public void onClick(View view) {

                                                if(pass.getText().toString().isEmpty() || pass2.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || name.getText().toString().isEmpty() || username.getText().toString().isEmpty() || email.getText().toString().isEmpty()){
                                                    Toast.makeText(NewAccount.this, "All fields must be provided", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }

                                                if(!email.getText().toString().contains("@") || !email.getText().toString().contains(".com") || email.getText().toString().contains(" ")){
                                                    Toast.makeText(NewAccount.this, "The email must be in the form of example@example.com and with no spaces.", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }

                                                if(!pass.getText().toString().matches(pass2.getText().toString())){
                                                    Toast.makeText(NewAccount.this, "Passwords must be identical.", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }

                                                if(username.getText().toString().contains(" ")){
                                                    Toast.makeText(NewAccount.this, "Username can't contain spaces.", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }

                                                if (name.getText().toString().trim().length() == 0 || phone.getText().toString().trim().length() == 0 || email.getText().toString().trim().length() == 0 || pass.getText().toString().trim().length() == 0 || username.getText().toString().trim().length() == 0) {
                                                    Toast.makeText(NewAccount.this, "Can't keep fields as spaces.", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }
                                                for(User s:listofusers){
                                                    if(username.getText().toString().equals(s.getUsername())){
                                                        Toast.makeText(NewAccount.this, "This username already exists", Toast.LENGTH_SHORT).show();
                                                        return;
                                                    }

                                                    if(email.getText().toString().equals(s.getEmail())){
                                                        Toast.makeText(NewAccount.this, "This email already exists", Toast.LENGTH_SHORT).show();
                                                        return;
                                                    }
                                                }

                                                User user = new User(name.getText().toString(), email.getText().toString(), pass.getText().toString(), phone.getText().toString(), username.getText().toString());
                                                movieViewModel.insert(user);

                                                Intent intent = new Intent(NewAccount.this, MainLoggedInPage.class);
                                                intent.putExtra(MainLoggedInPage.EXTRA_NAME, user.getName());
                                                intent.putExtra(MainLoggedInPage.EXTRA_ID, user.getId());
                                                intent.putExtra(MainLoggedInPage.EXTRA_EMAIL, user.getEmail());
                                                intent.putExtra(MainLoggedInPage.EXTRA_PHONENO, user.getPhonenumber());
                                                intent.putExtra(MainLoggedInPage.EXTRA_USERNAME, user.getUsername());
                                                intent.putExtra(MainLoggedInPage.EXTRA_PASSWORD, user.getPassword());
                                                startActivityForResult(intent, 1);



                                            }
                                        }
        );
    }

    public int returnnoofusers(){
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                int x = users.size();
                Toast.makeText(NewAccount.this, Integer.toString(x), Toast.LENGTH_SHORT).show();
            }
        });
        return x;
    }


}
