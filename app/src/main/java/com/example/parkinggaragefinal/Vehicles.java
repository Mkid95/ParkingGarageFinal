package com.example.parkinggaragefinal;

import java.util.Date;

public interface Vehicles {
    int CAR = 0, TRUCK = 1, MOTORCYCLE = 2;
    String[] svehicle = {"Car","Truck", "Motorcycle"};
    double[][] rates = {{20.0,2.50},{40.00,5.00},{10.00,1.00}};
    Date simulatedDate = new Date(1558044000000L); //6pm simulated time
}
