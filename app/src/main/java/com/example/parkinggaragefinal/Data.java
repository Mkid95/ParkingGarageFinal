package com.example.parkinggaragefinal;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;
import android.content.res.AssetManager;
import java.io.FileNotFoundException;

public class Data
{
    private static Hashtable<String, Account> Accounts = new Hashtable<String, Account>();
    private static ArrayList<String> usernames = new ArrayList<String>();

    public static ArrayList<String> getUsernames()
    {
        return usernames;
    }


    public static void loadAccounts() throws Exception
    {
        File file = new File("DATA_TEST.txt");
        Scanner read = new Scanner(file);
        read.nextLine();
        while (read.hasNextLine())
        {
            String username = read.next();
            if(!username.equals("End"))
            {
                String password = read.next();
                String first = read.next();
                String last = read.next();
                String ID = read.next();
                boolean gender = read.nextBoolean();
                double GPA = read.nextDouble();
            }
            else
            {
                read.close();
                return;
            }
        }

        read.close();
    }

    public static Hashtable<String, Account> getAccounts()
    {
        return Accounts;
    }
    public static void deleteEND()//same as delete data but just for deleting the END
    {
        //System.out.println(lineToRemove);
        try {
            File inFile = new File("DATA_TEST.txt");
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader("DATA_TEST.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line ;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                if (!line.trim().equals("End")) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void WriteAccountData(AssetManager manager)
    {
        try
        {
            //File f = new File("DATA.txt");
            manager.openFd("DATA.txt").createOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(manager.openFd("DATA.txt").createOutputStream());
            for (int i = 0;i < 30;i++)
            {
                oos.writeObject(Accounts.get(usernames.get(i)));
            }
            oos.flush();
            oos.close();
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        } finally
        {

        }
    }
    public static void generate(AssetManager manager) throws IOException
    {
        InputStream is = manager.open("boys_names.txt");
        Scanner readBoys = new Scanner(is);
        InputStream is2 = manager.open("girls_names.txt");
        Scanner readGirls = new Scanner(is2);
        InputStream is3 = manager.open("Last Names.txt");
        Scanner readLast = new Scanner(is3);
        String[] boys = new String[600];
        String[] girls = new String[599];
        String[] last = new String[2000];
        for(int i = 0; i < 600; i++)
        {
            readBoys.next();
            boys[i] = readBoys.next();
        }
        for(int i = 0; i < 599; i++)
        {
            girls[i] = readGirls.next();
        }
        int n = 0;
        while(readLast.hasNextLine())
        {
            last[n]= readLast.nextLine();
            n++;
        }
        Random r = new Random();
        Account admin = new Account("Mkid", "1453");
        Accounts.put("Mkid",admin);
        usernames.add(admin.getUsername());
        for(int i = 0; i < r.nextInt(15)+10; i++)
        {

            String firstNameBoys = boys[r.nextInt(600)];
            String firstNameGirls = girls[r.nextInt(599)];
            String firstName;
            if(r.nextInt(1)== 1)
            {
                firstName = firstNameGirls;
            }
            else
            {
                firstName = firstNameBoys;
            }
            String lastName = last[r.nextInt(2000)];
            String password = generatePassword();
            Vehicle vehicle = generateVehicle();
            Account x = new Account(firstName, lastName, password, vehicle);
            Accounts.put(x.getUsername(), x);
            usernames.add(x.getUsername());
            System.out.println(x.toString());

        }
    }
    public static String addAccount(String first, String last, Vehicle vehicle)
    {
        Account account = new Account(first, last,generatePassword(), vehicle);
        String username = account.getUsername();
        Accounts.put(username,account);
        usernames.add(username);
        return username;
    }
    public static void removeAccount(String username)
    {
        Accounts.remove(username);
        usernames.remove(username);
    }
    public static String generatePassword()
    {
        StringBuilder sb = new StringBuilder(8);
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvxyz";
        String number = "0123456789";
        String special = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";
        for(int i = 0; i < 5; i++)
        {
            int index
                    = (int)(lower.length()
                    * Math.random());
            sb.append(lower.charAt(index));
        }
        int index
                = (int)(upper.length()
                * Math.random());
        sb.append(upper.charAt(index));
        index
                = (int)(number.length()
                * Math.random());
        sb.append(number.charAt(index));
        index
                = (int)(special.length()
                * Math.random());
        sb.append(special.charAt(index));
        return sb.toString();
    }
    public static Vehicle generateVehicle()
    {
        Random r = new Random();
        try {
            long leftLimit = 1L;
            long rightLimit = 39600000L;
            long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
            Date d2 = new Date(1558000890000L+generatedLong);
            Vehicle vehicle = new Vehicle(r.nextInt(3),d2, generateLicense());
            return vehicle;
        } catch (InvalidVehicleException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Vehicle();
    }
    public static String generateLicense()
    {
        StringBuilder sb = new StringBuilder(8);
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String number = "0123456789";
        for(int i = 0; i < 3; i++)
        {
            int index
                    = (int)(upper.length()
                    * Math.random());
            sb.append(upper.charAt(index));
        }
        sb.append("-");
        for(int i = 0; i < 4; i++)
        {
            int index
                    = (int)(number.length()
                    * Math.random());
            sb.append(number.charAt(index));
        }
        return sb.toString();
    }
}
