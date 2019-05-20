package com.example.parkinggaragefinal;

import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;


public class GarageActivity extends AppCompatActivity implements Vehicles{
    private static final int[] BUTTON_IDS = {
            R.id.A1, R.id.B1, R.id.A2, R.id.B2, R.id.A3, R.id.B3, R.id.A4, R.id.B4, R.id.A5, R.id.B5, R.id.A6, R.id.B6, R.id.A7, R.id.B7, R.id.A8,
            R.id.B8, R.id.A9, R.id.B9, R.id.A10, R.id.B10, R.id.A11, R.id.B11, R.id.A12, R.id.B12, R.id.A13, R.id.B13, R.id.A14, R.id.B14, R.id.A15, R.id.B15,
    };
    private static Integer[] nums = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
    private LinkedList<Integer> numbers;
    private String username;
    private ImageButton[] lots;
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
            user(account, -1);
        }
    }

    protected void admin()
    {
        setContentView(R.layout.activity_garage);
        lots = new ImageButton[30];
        if(numbers==null)
        {
            numbers = new LinkedList<Integer>(Arrays.asList(nums));
            Collections.shuffle(numbers);
        }
        LinkedList<Integer> numbersC = (LinkedList<Integer>) numbers.clone();
        for (int i = 0;i < 30;i++) {
            lots[i] = findViewById(BUTTON_IDS[i]);
            final int j = i;
            lots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    create(j);
                }
            });
        }
        for (int i = 0;i < Data.getUsernames().size()-1;i++) {
            int k = numbersC.pop();
            setImage(lots[k], Data.getAccounts().get(Data.getUsernames().get(i+1)).getVehicle().getVehicleType());
            final int j = i+1;
            final int l = k;
            lots[k].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user(Data.getAccounts().get(Data.getUsernames().get(j)),l);
                }
            });
        }
    }
    protected void user(Account user, int i)
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
        TextView tvRate = findViewById(R.id.tvRate);
        tvRate.setText("Rate: "+user.getVehicle().getRates());
        TextView tvParkTime = findViewById(R.id.tvParkTime);
        tvParkTime.setText("Park Time: "+user.getVehicle().getOrigin().toString()+" ("+user.getVehicle().getHours()+" Hours)");
        TextView tvCurrentTime = findViewById(R.id.tvCurrentTime);
        tvCurrentTime.setText("Current Time: "+simulatedDate.toString());
        TextView tvCost = findViewById(R.id.tvCost);
        tvCost.setText("Cost: "+user.getVehicle().getCost());
        final Button unPark = findViewById(R.id.unPark);
        final String username2 = user.getUsername();
        final int j = i;
        unPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.removeAccount(username2);
                if(Data.getAccounts().containsKey(username2)&& Data.getAccounts().get(username2).isAdmin())
                {
                    System.out.println(Arrays.toString(numbers.toArray()));
                    numbers.remove(new Integer(j));
                    System.out.println(Arrays.toString(numbers.toArray()));
                    numbers.add(j);
                    System.out.println(Arrays.toString(numbers.toArray()));
                    admin();
                }
                else
                {
                    unPark.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder fail = new AlertDialog.Builder(GarageActivity.this);

                    fail.setCancelable(true);
                    fail.setTitle("Ticket is now Printing");
                    fail.setMessage("Please present your ticket to the administrator");

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
    protected void create(int i)
    {
        setContentView(R.layout.create);
        final int j = i;
        final EditText first = findViewById(R.id.tbFirst);
        final EditText last = findViewById(R.id.tbLast);
        final EditText license = findViewById(R.id.tbLicense);
        final RadioGroup radiovt = findViewById(R.id.rg);

        Button back2 = findViewById(R.id.back2);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin();
            }
        });
        Button park = findViewById(R.id.park);
        park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sfirst = first.getText().toString();
                String slast = last.getText().toString();
                String slicense = license.getText().toString();
                if(sfirst.length()<3||slast.length()<3||slicense.length()!=7)
                {
                    AlertDialog.Builder badInfo = new AlertDialog.Builder(GarageActivity.this);

                    badInfo.setCancelable(true);
                    badInfo.setTitle("Incorrect Info");
                    badInfo.setMessage("-names must be at least 3 characters\n-License must be 7 characters");

                    badInfo.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    badInfo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    badInfo.show();
                }
                else
                {
                    Vehicle vehicle;
                    int type = radiovt.getCheckedRadioButtonId();
                    if(type == R.id.car)
                    {
                        type = CAR;
                    }
                    else if (type == R.id.truck)
                    {
                        type = TRUCK;
                    }
                    else
                    {
                        type = MOTORCYCLE;
                    }
                    try {
                        vehicle = new Vehicle(type, slicense);
                    }
                    catch (InvalidVehicleException e)
                    {
                        vehicle = null;
                    }
                    Data.addAccount(sfirst,slast, vehicle);
                    System.out.println("j = "+ j);
                    System.out.println(Arrays.toString(numbers.toArray()));
                    int index = numbers.indexOf(j);
                    Collections.swap(numbers,index, Data.getUsernames().size()-2);
                    System.out.println("index: "+index+" swapped with index: "+(Data.getUsernames().size()-1));
                    System.out.println(Arrays.toString(numbers.toArray()));
                    admin();
                }
            }
        });
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

