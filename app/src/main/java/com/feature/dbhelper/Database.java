package com.feature.dbhelper;

import com.feature.prefs.Prefs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    private static final Database INSTANCE = new Database();

    private Connection connection;

    private Database(){
        try {
            String connectionUrl = new Prefs().getString(Prefs.DB_JDBC_CONNECTION_URL);
            connection = DriverManager.getConnection(connectionUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Database getInstance(){
        return INSTANCE;
    }

    public Connection getConnection(){
        return connection;
    }

    public int executeUpdate(String sql){
        try(Statement st = connection.createStatement()){
            return st.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
