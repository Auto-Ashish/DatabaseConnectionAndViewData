package org.example;



import java.sql.*;

/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class JdbcSQLServerConnection {

    private static Connection conn;

    public static void main(String[] args) throws SQLException {
        JdbcSQLServerConnection jdbcSQLServerConnection = new JdbcSQLServerConnection();



        try {

            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=msdb";
            String user = "sa";
            String pass = "DB_Password";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
            viewTable();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
 }


    public static void viewTable()  {
        String query = "SELECT * FROM jobs";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String job_title = rs.getString("JOB_TITLE");
                int job_id = rs.getInt("JOB_ID");
                float min_salary = rs.getFloat("MIN_SALARY");
                float max_salary = rs.getFloat("MAX_SALARY");
                System.out.println(job_id + ", " + job_title + ", " + min_salary +
                        ", " + max_salary);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}