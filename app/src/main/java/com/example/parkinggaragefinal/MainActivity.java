package com.example.parkinggaragefinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //test push from laptop
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
    }

    private void logIn()
    {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        if(username.getText().toString().equals("hello"))
        {
            Intent login = new Intent(getApplicationContext(), GarageActivity.class);
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
