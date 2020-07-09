package com.example.siteautobarcode.DAO;

import com.example.siteautobarcode.POJO.RowKSO;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

public class ChekedDAO {
        private Connection getConnection() throws URISyntaxException, SQLException {
            String sslMode = ""; //sslmode=require&
            String dbUrl = "jdbc:postgresql://ec2-3-248-4-172.eu-west-1.compute.amazonaws.com:5432/d5c4vphe1vsm5o?" + sslMode + "user=mhzlihprhnmclt&password=cddb239d5d1708ca93521645f468132ee1719f603d22bf4b51cf779b8253c08f";
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
