package com.example.siteautobarcode;

import java.io.*;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class uploadToSite {
    public static void main(String[] args)
    {
        int x = 5, y = 9;
        ArrayList<String> result = new ArrayList<>();
//
//        try {
//            File file = new File("result.txt");
//            //создаем объект FileReader для объекта File
//            FileReader fr = new FileReader(file);
//            //создаем BufferedReader с существующего FileReader для построчного считывания
//            BufferedReader reader = new BufferedReader(fr);
//
//            // считаем сначала первую строку
//            String line = reader.readLine();
//            while (line != null) {
//                result.add(line);
//                //System.out.println(line);
//                // считываем остальные строки в цикле
//                line = reader.readLine();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String token;
//        GetBalance getBalance = new GetBalance();
//        for(int i = 0; i < result.size(); i++)
//        {
//            token = result.get(i).split("/")[3];
//            System.out.println(getBalance.getBalance(token).getMainPointsBalance() / 100);
//        }

        GetBalance getBalance = new GetBalance();
//
        try {
            File file = new File("result.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);

            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                result.add(line);
                //System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

                //вместо 50 максимальноый баланс в коллекции
//        for(int k = 0; k < 50; k++) {
            for(int i = 0; i < result.size(); i++)
            {
                int balance = getBalance.getBalance(result.get(i).split("/")[3]).getMainPointsBalance() / 100;
                if (balance >= x & balance <= y) {
                    try {
                        String token = createToken();
                        insertData(token, result.get(i).split("/")[1], result.get(i).split("/")[3], balance);
                        FileWriter writer = new FileWriter(x + "-" + y + ".txt", true);
                        writer.write("magnit.host/card?token=" + token + ",");
                        // запись по символам
                        writer.append('\n');
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
//            System.out.println(k);
//            x += 10;
//            y += 10;
//        }
    }

    private static void insertData(String id,String card, String token, int balance)
    {
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO dataforqr (id, card, token, balance) " +
                    "VALUES ((?), (?), (?), (?))")) {
                statement.setString(1, id);
                statement.setString(2, card);
                statement.setString(3, token);
                statement.setInt(4, balance);
                statement.executeUpdate();
            } finally {
                connection.close();
            }
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        String sslMode = "sslmode=require&"; //sslmode=require&
        String dbUrl = "jdbc:postgresql://ec2-54-217-213-79.eu-west-1.compute.amazonaws.com:5432/d7k7v3h3fooao?"+ sslMode +"user=twalvkyanjnxuz&password=ef67173560124532e6733872393b1f10664358fbab94c2aac8a947d2938b856f";
        return DriverManager.getConnection(dbUrl);
    }

    private static String createToken()
    {
        double k = 0,c = 10 + Math.random()*81;;
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM dataforqr")) {
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next())
                {
                    k = resultSet.getInt(1);
                }
            } finally {
                connection.close();
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return MD5((char)('A' + new Random().nextInt(26)) + String.valueOf(c) +
                (char)('A' + new Random().nextInt(26)) + String.valueOf(k));
    }

    private static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
