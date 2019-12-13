package com.example.javalearner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper extends Config {

    Connection getDbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ";" + dbPort + "/" + dbName;

        Class.forName(".com.mysql.cj.jdbc.Driver");

        getDbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return getDbConnection;
    }

    public void signUpUser(String email, String password, Integer XP, Integer Complete_quest, String username, String Language) {
        User user = new User();
        String insert = "INSERT INTO " + Constante.USER_TABLE + "(" + Constante.USERS_EMAIL + "," + Constante.USERS_USERNAME + ","
                + Constante.USERS_LANGUAGE + "," + Constante.USERS_XP + "," + Constante.USERS_COMPLETED_QUEST + "," + Constante.USERS_PASSWORD + ")" +
                "VALUES(?,?,?,?,?,MD5(?))";
        try {
            PreparedStatement registration = getDbConnection().prepareStatement(insert);
            registration.setString(1, user.getEmail());
            registration.setString(2, user.getUsername());
            registration.setString(3, user.getLanguage());
            registration.setInt(4, user.getXP());
            registration.setInt(5, Complete_quest);
            registration.setString(6, password);

            registration.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
