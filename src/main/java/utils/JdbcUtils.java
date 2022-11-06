package utils;

import java.sql.*;

public class JdbcUtils {
    private static volatile JdbcUtils instance;
    private static Connection connection = null;
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public JdbcUtils() {
    }

    private static Connection getInstance() {
        if (instance == null) {
            synchronized (JdbcUtils.class) {
                if (instance == null) {
                    instance = new JdbcUtils();
                    installConnection();
                }
            }
        }
        return connection;
    }

    private static Connection installConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static PreparedStatement getPreparedStatement(String sql) {
        try {
            return getInstance().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Statement getStatement() {
        try {
            return getInstance().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
