package com.example.siteautobarcode.DAO;

import com.example.siteautobarcode.POJO.RowDB;

import java.net.URISyntaxException;
import java.sql.*;

public class DBConnection {
    private Connection getConnection() throws URISyntaxException, SQLException {
        String sslMode = ""; //sslmode=require&
        String dbUrl = "jdbc:postgresql://ec2-54-217-213-79.eu-west-1.compute.amazonaws.com:5432/d7k7v3h3fooao?"+ sslMode +"user=twalvkyanjnxuz&password=ef67173560124532e6733872393b1f10664358fbab94c2aac8a947d2938b856f";
        return DriverManager.getConnection(dbUrl);
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

}
