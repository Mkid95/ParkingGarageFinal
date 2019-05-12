package com.example.parkinggaragefinal;


public class Account implements java.io.Serializable {

    private String firstName, lastName, id, password, username;
    private boolean isAdmin;
    private Vehicle vehicle;

    public Account(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = generateID();
        this.password = password;
        this.username = generateUser();
        this.isAdmin = true;
    }

    public Account(String username, String password) {
        this.password = password;
        this.username = username;
        this.isAdmin = true;
    }

    public Account(String firstName, String lastName, String password, Vehicle vehicle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = generateID();
        this.password = password;
        this.username = generateUser();
        this.isAdmin = false;
        this.vehicle = vehicle;
    }


    @Override
    public String toString() {
        return "Account{" + "password: " + password + ", username: " + username + "} " + vehicle.toString();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    private static String generateID()
    {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {


            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());

            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString(); 
    } 

    private String generateUser()
    {
        StringBuilder sb = new StringBuilder(6); 
        if(getLastName().length() >= 4)
        {
            sb.append(getLastName().charAt(0)); 
            sb.append(getLastName().charAt(1)); 
            sb.append(getLastName().charAt(2)); 
            sb.append(getLastName().charAt(3));
        }
        else
        {
            sb.append(getLastName());
        }
        sb.append(getFirstName().charAt(0)); 
        sb.append(getId().charAt(0)); 
        return sb.toString();
    }
}
