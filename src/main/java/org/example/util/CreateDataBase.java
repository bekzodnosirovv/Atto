package org.example.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDataBase {
    public void start() {
        createCardDB();
        createProfileDB();
        createTerminalDB();
        createTransactionDB();
    }

    private  void createProfileDB() {
        try {
            Connection connection = DataBaseConnection.getCon();
            String sql = "create table if not exists profile( " +
                    "    id serial primary key, " +
                    "    name varchar(50), " +
                    "    surname varchar(50), " +
                    "    phone varchar(15) unique," +
                    "    password varchar(10) not null," +
                    "    created_date timestamp default now()," +
                    "    status varchar(20)," +
                    "    profileRole varchar(20) )";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private  void createCardDB() {
        try {
            Connection connection = DataBaseConnection.getCon();
            String sql = "create table if not exists card( " +
                    "    id serial primary key, " +
                    "    card_number integer unique, " +
                    "    exp_date date, " +
                    "    balance double precision ," +
                    "    status varchar(20)," +
                    "    phone varchar(15)," +
                    "    created_date timestamp default now() )";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private  void createTerminalDB() {
        try {
            Connection connection = DataBaseConnection.getCon();
            String sql = "create table if not exists terminal( " +
                    "    id serial primary key, " +
                    "    code integer unique, " +
                    "    address varchar(225), " +
                    "    status varchar(20)," +
                    "    created_date timestamp default now() )";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private  void createTransactionDB() {
        try {
            Connection connection = DataBaseConnection.getCon();
            String sql = "create table if not exists transaction( " +
                    "    id serial primary key, " +
                    "    card_number Integer ," +
                    "    amount double precision, " +
                    "    terminal_code integer ," +
                    "    tr_type varchar(20)," +
                    "    created_date timestamp default now() )";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
