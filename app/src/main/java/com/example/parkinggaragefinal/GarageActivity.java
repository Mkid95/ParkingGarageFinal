package com.example.parkinggaragefinal;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


public class GarageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String user = getIntent().getExtras().getString("com.example.parkinggaragefinal.username");
        Account account = Data.getAccounts().get(user);
        if (account.isAdmin())
        {
            admin();
        }
        else
        {
            user(user);
        }
    }

    protected void admin()
    {
        setContentView(R.layout.activity_garage);
    }
    protected void user(String user)
    {
        setContentView(R.layout.ticket);
        ImageView image = findViewById(R.id.vehicleimage);
        image.setImageResource(R.drawable.truck);
    }
}

