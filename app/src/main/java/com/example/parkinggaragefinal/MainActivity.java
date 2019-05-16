package com.example.parkinggaragefinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                logIn();
            }
        });
        Button buttonGenerate = (Button) findViewById(R.id.buttonGenerate);
        buttonGenerate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                    try {
                        Data.generate(getAssets());
                        Data.WriteAccountData(getAssets());
                    } catch (IOException e) {
                        AlertDialog.Builder fail = new AlertDialog.Builder(MainActivity.this);

                        fail.setCancelable(true);
                        fail.setTitle("Fail");
                        fail.setMessage("DAMN IT");

                        fail.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        fail.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        fail.show();
                    }

            }
        });

    }

    private void logIn()
    {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        String susername = username.getText().toString();
        String spassword = password.getText().toString();
        if(Data.getUsernames().contains(susername.toString())&&
                Data.getAccounts().get(susername).getPassword().equals(spassword))
        {
            Intent login = new Intent(getApplicationContext(), GarageActivity.class);
            login.putExtra("com.example.parkinggaragefinal.username", susername);
            startActivity(login);
        }
        else
        {
            AlertDialog.Builder incorrectLogin = new AlertDialog.Builder(MainActivity.this);

            incorrectLogin.setCancelable(true);
            incorrectLogin.setTitle("Incorrect login information");
            incorrectLogin.setMessage("The username/password entered was incorrect.");

            incorrectLogin.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            incorrectLogin.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            incorrectLogin.show();
        }

    }
}
