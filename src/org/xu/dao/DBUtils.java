package org.xu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.jdbc.Driver");
    	return DriverManager.getConnection("jdbc:mysql://localhost:3306/meeting", "root", "123");
    }
    public static void close(Connection conn) {
    	if(conn != null) {
    		try {
    			conn.close();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    public static void close(PreparedStatement conn) {
    	if(conn != null) {
    		try {
    			conn.close();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    public static void close(ResultSet conn) {
    	if(conn != null) {
    		try {
    			conn.close();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
}

