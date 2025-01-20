
package org.example;

import com.feature.dbhelper.DatabaseInitService;
import com.feature.prefs.Prefs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        String connectionUrl = new Prefs().getString(Prefs.DB_JDBC_CONNECTION_URL);
        try {
            new DatabaseInitService().initDb(connectionUrl);
            Connection connection = DriverManager
                    .getConnection(connectionUrl);
        }catch (SQLException e){
            System.out.println("Error getConnection "+connectionUrl);
            e.printStackTrace();
        }
    }
}
