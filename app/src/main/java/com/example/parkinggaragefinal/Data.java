package com.example.parkinggaragefinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

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
                //Accounts.put(username, new Account(username, password, ));
                //usernames.add(email);
            }
            else
            {
                read.close();
                return;
            }
        }
        // System.out.println(Accounts.get("DustinKBerry@armyspy.com").toString());
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
    public static void WriteAccountData(Account x)
    {
        deleteEND();
        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter(new FileWriter("DATA_TEST.txt", true));
            //bw.newLine();
            //bw.write(x.getEmail()+"\t"+x.getNickname()+"\t"+x.getPassword()+"\t"+x.getHobbies()[0]+"\t"+x.getHobbies()[1]+"\t"+x.getHobbies()[2]+"\t"+x.getHobbies()[3]+"\t"+x.getHobbies()[4]+"\t"+x.getHobbies()[5]);
            bw.newLine();
            bw.write("End");
            bw.flush();
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        } finally
        {
            if (bw != null)
                try
                {
                    bw.close();
                } catch (IOException ioe2)
                {
                }
        }
    }
    public static void generate() throws FileNotFoundException
    {
        File fileBoys = new File("boys_names.txt");
        Scanner readBoys = new Scanner(fileBoys);
        File fileGirls = new File("girls_names.txt");
        Scanner readGirls = new Scanner(fileGirls);
        File fileLast = new File("Last Names.txt");
        Scanner readLast = new Scanner(fileLast);
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
        for(int i = 0; i < 1500; i++)
        {
            String firstName = boys[r.nextInt(600)];
            String lastName = last[r.nextInt(2000)];
            String password = generatePassword();
            double GPA = Math.random() * 4.0;
            Account x = new Account(firstName, lastName, password);
            Accounts.put(x.getUsername(), x);
            System.out.println(x.toString());

        }
        for(int i = 0; i < 1500; i++)
        {
            String firstName = girls[r.nextInt(599)];
            String lastName = last[r.nextInt(2000)];
            String password = generatePassword();
            double GPA = Math.random() * 4.0;
            Account x = new Account(firstName, lastName, password);
            Accounts.put(x.getUsername(), x);
        }
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
}
