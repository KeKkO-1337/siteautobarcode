package com.example.siteautobarcode.DAO;

import com.example.siteautobarcode.POJO.RowDB;
import com.example.siteautobarcode.POJO.RowKSO;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class DBConnection {
    private Connection getConnection() throws URISyntaxException, SQLException {
        String sslMode = ""; //sslmode=require&
        String dbUrl = "jdbc:postgresql://ec2-54-217-213-79.eu-west-1.compute.amazonaws.com:5432/d7k7v3h3fooao?"+ sslMode +"user=twalvkyanjnxuz&password=ef67173560124532e6733872393b1f10664358fbab94c2aac8a947d2938b856f";
        return DriverManager.getConnection(dbUrl);
    }

    public void setBalance(String idDB, int balance,String owner)
    {
        RowKSO rowKSO = getRowForKSO(idDB);
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO \"dataWithKSO\" (token,card,balance,region,owner) VALUES ((?),(?),(?),(?),(?))")) {
                statement.setString(1, createToken());
                statement.setString(2, rowKSO.getCard());
                statement.setInt(3, (int)balance);
                statement.setString(4, rowKSO.getRegion());
                statement.setString(5, owner);
                statement.executeUpdate();
            } finally {
                connection.close();
            }
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
        }
        deltForCheck(idDB);
    }

    public void deltForCheck(String token)
    {
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM \"dataForKSOCheck\" WHERE token=(?)")) {
                statement.setString(1, token);
                statement.executeUpdate();
            } finally {
                connection.close();
            }
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<RowKSO> getAllDataForKSO(String usr)
    {
        ArrayList<RowKSO> list = new ArrayList<>();
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("SELECT id, token FROM \"dataForKSOCheck\" WHERE balance IS NULL AND \"forUser\"=(?)")) {
                statement.setString(1, usr);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next())
                {
                    list.add(new RowKSO(resultSet.getInt("id"), resultSet.getString("token")));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return list;
    }

    public RowKSO getRowForKSO(String token)
    {
        RowKSO rowDB  = null;
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"dataForKSOCheck\" WHERE token = (?)")) {
                statement.setString(1, token);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next())
                {
                    rowDB = new RowKSO(resultSet.getInt("id"), resultSet.getString("token"),resultSet.getString("card"),
                            resultSet.getFloat("balance"),resultSet.getString("region"));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return rowDB;
    }

    public RowDB getRow(String id)
    {
        RowDB rowDB  = null;
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dataforqr WHERE id = (?)")) {
                statement.setString(1, id);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next())
                {
                    rowDB = new RowDB(resultSet.getString("id"),resultSet.getString("card"), resultSet.getString("token"));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return rowDB;
    }
    private String createToken()
    {
        double k = 0,c = 10 + Math.random()*81;;
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM \"dataWithKSO\" ORDER BY id DESC LIMIT 1")) {
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next())
                {
                    k = resultSet.getInt("id");
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

    private String MD5(String md5) {
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
