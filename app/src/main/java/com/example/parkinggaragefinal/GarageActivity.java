package com.example.parkinggaragefinal;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GarageActivity extends AppCompatActivity {
    LinearLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        parent=(LinearLayout)findViewById(R.id.layout);
        String[] buttons_names = new String[30];
        //ImageView image = new ImageView(R.id.imageView);
    }
}

