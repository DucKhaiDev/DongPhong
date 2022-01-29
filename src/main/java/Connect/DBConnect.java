package Connect;

import java.sql.*;

public class DBConnect {
    private static String dbUrl = "jdbc:sqlserver://localhost:1433;"
                                    + "databaseName=DongPhong;"
                                    + "integratedSecurity=true";
    private static String username = "sa";
    private static String password = "123456";

    //Kết nối với SQLServer sử dụng JDBC
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Kiểm tra kết nối
    public static void main(String[] args) {
        System.out.println(DBConnect.getConnection());
    }
}
