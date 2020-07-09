package com.example.siteautobarcode.DAO;

import com.example.siteautobarcode.POJO.Product;
import com.example.siteautobarcode.POJO.User;

import java.net.URISyntaxException;
import java.sql.*;

public class DBMagnit {
    private Connection getConnection() throws URISyntaxException, SQLException {
        String sslMode = ""; //sslmode=require&
        String dbUrl = "jdbc:postgresql://ec2-3-248-4-172.eu-west-1.compute.amazonaws.com:5432/d5c4vphe1vsm5o?"+ sslMode +"user=mhzlihprhnmclt&password=cddb239d5d1708ca93521645f468132ee1719f603d22bf4b51cf779b8253c08f";
        return DriverManager.getConnection(dbUrl);
    }

    public void insertProduct(String token, int balance, String region)
    {
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("UPDATE magnit"+ "_" + region
                    + " SET viewed=(?), earned=(?) WHERE username=(?)")) {

                statement.executeUpdate();
            } finally {
                connection.close();
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public Product getData(String nameOfTable, int id)
    {
        Product product = null;
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + nameOfTable +
                    " WHERE id = (?)")) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next())
                {
                   // product = new Product(resultSet.getString("id"),resultSet.get);
                }
                //ret = statement.executeQuery().next();
            } finally {
                connection.close();
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return product;
    }
}
