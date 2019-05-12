package com.example.parkinggaragefinal;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


public class GarageActivity extends AppCompatActivity implements Vehicles{
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
            user(account);
        }
    }

    protected void admin()
    {
        setContentView(R.layout.activity_garage);
    }
    protected void user(Account user)
    {
        setContentView(R.layout.ticket);
        ImageView image = findViewById(R.id.vehicleimage);
        setImage(image, user.getVehicle().getVehicleType());
        TextView tvName = findViewById(R.id.tvName);
        tvName.setText("Name: "+user.getFirstName()+" "+user.getLastName());
        TextView tvLicense = findViewById(R.id.tvLicense);
        tvLicense.setText(user.getVehicle().getLicense());
    }
    protected void setImage(ImageView image, int vehicleType)
    {
        switch (vehicleType)
        {
            case CAR:
                image.setImageResource(R.drawable.car);
                break;
            case TRUCK:
                image.setImageResource(R.drawable.truck);
                break;
            case MOTORCYCLE:
                image.setImageResource(R.drawable.cycle);
                break;
        }

    }
}

