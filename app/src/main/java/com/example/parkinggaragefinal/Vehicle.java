package com.example.parkinggaragefinal;
import java.util.Date;

public class Vehicle implements java.io.Serializable{
    private final int CAR = 0, TRUCK = 1, MOTORCYCLE = 2;
    private int vehicleType;
    Date origin;


    public Vehicle(int vehicleType)throws InvalidVehicleException{
        if(vehicleType>3||vehicleType<0)
        {
            throw new InvalidVehicleException();
        }
        else
        {
            this.vehicleType = vehicleType;
        }
        origin = new Date();
    }
    public Vehicle(int vehicleType, Date origin)throws Exception{
        if(vehicleType>3||vehicleType<0)
        {
            throw new Exception();
        }
        else
        {
            this.vehicleType = vehicleType;
        }
        this.origin = origin;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public Date getOrigin() {
        return origin;
    }

    public long getHours(Date date)
    {
        long current = date.getTime();
        long prev = origin.getTime();
        long hours = current - prev;
        hours = hours/(1000 * 60 * 60);
        return hours;

    }
}
