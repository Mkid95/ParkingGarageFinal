package com.example.parkinggaragefinal;
import java.util.Date;

public class Vehicle implements java.io.Serializable, Vehicles{
    private int vehicleType;
    private String license;
    Date origin;


    public Vehicle(int vehicleType, String license)throws InvalidVehicleException{
        if(vehicleType>3||vehicleType<0)
        {
            throw new InvalidVehicleException();
        }
        else
        {
            this.vehicleType = vehicleType;
        }
        origin = new Date();
        this.license = license;
    }
    public Vehicle(int vehicleType, Date origin, String license)throws Exception{
        if(vehicleType>3||vehicleType<0)
        {
            throw new Exception();
        }
        else
        {
            this.vehicleType = vehicleType;
        }
        this.origin = origin;
        this.license = license;
    }
    public Vehicle(){
        this.vehicleType = 1;
        origin = new Date();
        this.license = "WRONG";
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

    public String getLicense() {
        return license;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleType=" + vehicleType +
                ", license='" + license + '\'' +
                '}';
    }

    public double getRates()
    {
        int i;
        if(isEB())
        {
            i=0;
        }
        else
        {
            i=1;
        }
        return rates[getVehicleType()][i];
    }

    private boolean isEB() {
        return true;
    }
}
