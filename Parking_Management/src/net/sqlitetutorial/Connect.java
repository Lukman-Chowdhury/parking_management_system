package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public static final String SQCONN = "jdbc:sqlite:out/production/Homepage/records.sqlite";
    public static Connection getConnection() throws SQLException {
        try{

            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQCONN);
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return  null;
    }
}
