package com.example.siteautobarcode.DAO;

import com.example.siteautobarcode.POJO.RowKSO;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

public class ChekedDAO {
        private Connection getConnection() throws URISyntaxException, SQLException {
            String sslMode = ""; //sslmode=require&
            String dbUrl = "jdbc:postgresql://ec2-54-217-213-79.eu-west-1.compute.amazonaws.com:5432/d7k7v3h3fooao?" + sslMode + "user=twalvkyanjnxuz&password=ef67173560124532e6733872393b1f10664358fbab94c2aac8a947d2938b856f";
            return DriverManager.getConnection(dbUrl);
        }


        public RowKSO getRowForKSO(String token) {
            RowKSO rowDB = null;
            try {
                Connection connection = getConnection();
                try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"dataWithKSO\" WHERE token = (?)")) {
                    statement.setString(1, token);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        rowDB = new RowKSO(resultSet.getInt("id"), resultSet.getString("token"), resultSet.getString("card"),
                                resultSet.getFloat("balance"), resultSet.getString("region"));
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
