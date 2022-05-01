package Connect;

import java.sql.*;

public class DBConnect {
    private static final String dbUrl = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=DongPhong;"
            + "integratedSecurity=true";
    private static final String username = "sa";
    private static final String password = "123456";

    //Connect to SQL Server using JDBC
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}