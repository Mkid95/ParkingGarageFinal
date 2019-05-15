package com.example.parkinggaragefinal;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class GarageActivity extends AppCompatActivity implements Vehicles{
    private static final int[] BUTTON_IDS = {
            R.id.A1, R.id.B1, R.id.A2, R.id.B2, R.id.A3, R.id.B3, R.id.A4, R.id.B4, R.id.A5, R.id.B5, R.id.A6, R.id.B6, R.id.A7, R.id.B7, R.id.A8,
            R.id.B8, R.id.A9, R.id.B9, R.id.A10, R.id.B10, R.id.A11, R.id.B11, R.id.A12, R.id.B12, R.id.A13, R.id.B13, R.id.A14, R.id.B14, R.id.A15, R.id.B15,
    };
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        username = getIntent().getExtras().getString("com.example.parkinggaragefinal.username");
        Account account = Data.getAccounts().get(username);
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
        ImageButton[] lots = new ImageButton[30];
        for (int i = 0;i < 30;i++)
        {
            lots[i] = findViewById(BUTTON_IDS[i]);
            setImage(lots[i], Data.getAccounts().get(Data.getUsernames().get(i)).getVehicle().getVehicleType());
            final int j = i;
            lots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user(Data.getAccounts().get(Data.getUsernames().get(j)));
                }
            });
        }
    }
    protected void user(Account user)
    {
        setContentView(R.layout.ticket);

        ImageView image = findViewById(R.id.vehicleimage);
        setImage(image, user.getVehicle().getVehicleType());

        TextView tvName = findViewById(R.id.tvName);
        tvName.setText("Name: "+user.getFirstName()+" "+user.getLastName());
        TextView tvLicense = findViewById(R.id.tvLicense);
        tvLicense.setText("License: "+user.getVehicle().getLicense());
        TextView tvType = findViewById(R.id.tvType);
        tvType.setText("Vehicle Type: "+svehicle[user.getVehicle().getVehicleType()]);
        //String rate = getRate(user.getVehicle());
        TextView tvRate = findViewById(R.id.tvRate);
        tvRate.setText("Rate: "+user.getVehicle().getRates());
        TextView tvParkTime = findViewById(R.id.tvParkTime);
        tvParkTime.setText("Park Time: "+user.getVehicle().getOrigin().toString());
        if(Data.getAccounts().get(username).isAdmin())
        {
            Button back = findViewById(R.id.Back);
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    admin();
                }
            });
        }
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

