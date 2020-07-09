package com.example.siteautobarcode.DAO;

import com.example.siteautobarcode.POJO.RowKSO;
import com.example.siteautobarcode.POJO.User;

import java.net.URISyntaxException;
import java.sql.*;

public class UsersDAO {
    private Connection getConnection() throws URISyntaxException, SQLException {
        String sslMode = ""; //sslmode=require&
        String dbUrl = "jdbc:postgresql://ec2-3-248-4-172.eu-west-1.compute.amazonaws.com:5432/d5c4vphe1vsm5o?"+ sslMode +"user=mhzlihprhnmclt&password=ef67173560124532e6733872393b1f10664358fbab94c2aac8a947d2938b856f";
        return DriverManager.getConnection(dbUrl);
    }

    public void updateUserForCheck(String username, int balance)
    {
        User user = getUser(username);
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("UPDATE users SET viewed=(?), earned=(?) WHERE username=(?)")) {
                statement.setInt(1,user.getViewed() + 1);
                statement.setDouble(2, (user.getEarned() + (balance * 0.2)));
                statement.setString(3, username);
                statement.executeUpdate();
            } finally {
                connection.close();
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String username)
    {
        User user = null;
        try {
            Connection connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement("SELECT id, username, viewed, earned FROM users WHERE username=(?)")) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next())
                {
                    user = new User(resultSet.getInt("id"), resultSet.getString("username"),
                            resultSet.getInt("viewed"), resultSet.getDouble("earned"));
                }
            } finally {
                connection.close();
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return user;
    }

}
