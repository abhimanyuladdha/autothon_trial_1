package com.autothon.utility;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseUtils {
    private static HikariDataSource dataSource;

    public static void initialize() {
        PropertyManager propertyManager = new PropertyManager();

        String jdbcUrl = propertyManager.getproperty("db.url");
        String username = propertyManager.getproperty("db.username");
        String encryptedPassword = propertyManager.getproperty("db.password");
        String password = EncryptionUtils.decrypt(encryptedPassword); // Decrypt password

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);

        // Read HikariCP properties
        config.setMaximumPoolSize(Integer.parseInt(propertyManager.getproperty("hikari.maximumPoolSize", "10")));
        config.setMinimumIdle(Integer.parseInt(propertyManager.getproperty("hikari.minimumIdle", "2")));
        config.setIdleTimeout(Long.parseLong(propertyManager.getproperty("hikari.idleTimeout", "30000")));
        config.setMaxLifetime(Long.parseLong(propertyManager.getproperty("hikari.maxLifetime", "1800000")));
        config.setConnectionTimeout(Long.parseLong(propertyManager.getproperty("hikari.connectionTimeout", "30000")));

        config.setDriverClassName(getDriverClass(config.getJdbcUrl()));

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new IllegalStateException("DatabaseUtil is not initialized. Call initialize() first.");
        }
        return dataSource.getConnection();
    }

    public static void closePool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    private static String getDriverClass(String jdbcUrl) {
        if (jdbcUrl.startsWith("jdbc:mysql:")) return "com.mysql.cj.jdbc.Driver";
        if (jdbcUrl.startsWith("jdbc:postgresql:")) return "org.postgresql.Driver";
        if (jdbcUrl.startsWith("jdbc:oracle:")) return "oracle.jdbc.OracleDriver";
        if (jdbcUrl.startsWith("jdbc:sqlserver:")) return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        throw new IllegalArgumentException("Unsupported database type in URL: " + jdbcUrl);
    }

    public static List<Map<String, Object>> fetchTableData(String tableName) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        String query = "SELECT * FROM " + tableName; // Ensure tableName is validated to prevent SQL injection

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                resultList.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching data from table " + tableName + ": " + e.getMessage());
            e.printStackTrace();
        }
        return resultList;
    }

    public static List<Map<String, Object>> fetchColumnData(String tableName, String... columns) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        // Validate table name
        if (tableName == null || tableName.trim().isEmpty()) {
            System.err.println("Error: Table name cannot be null or empty.");
            return resultList;
        }
        if (!tableName.matches("^[a-zA-Z0-9_]+$")) {
            System.err.println("Error: Invalid table name format.");
            return resultList;
        }

        // Validate columns
        if (columns == null || columns.length == 0) {
            System.err.println("Error: At least one column name must be provided.");
            return resultList;
        }

        for (String column : columns) {
            if (column == null || column.trim().isEmpty()) {
                System.err.println("Error: Column name cannot be null or empty.");
                return resultList;
            }
            if (!column.matches("^[a-zA-Z0-9_]+$")) {
                System.err.println("Error: Invalid column name format: " + column);
                return resultList;
            }
        }

        // Construct the query
        String columnList = String.join(", ", columns);
        String query = "SELECT " + columnList + " FROM " + tableName;

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (String column : columns) {
                    row.put(column, rs.getObject(column));
                }
                resultList.add(row);
            }

            if (resultList.isEmpty()) {
                System.out.println("Info: No data found in table '" + tableName + "' for columns: " + columnList);
            }

        } catch (SQLSyntaxErrorException e) {
            System.err.println("Error: Table '" + tableName + "' or specified columns do not exist.");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }

        return resultList;
    }

    public static int updateQuery(String query, Object... params) {
        int rowsAffected = 0;

        if (query == null || query.trim().isEmpty()) {
            System.err.println("Error: Query cannot be null or empty.");
            return rowsAffected;
        }

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Set parameters dynamically
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            rowsAffected = pstmt.executeUpdate();
            System.out.println("Query executed successfully. Rows affected: " + rowsAffected);

        } catch (SQLSyntaxErrorException e) {
            System.err.println("Error: Invalid SQL syntax - " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }

        return rowsAffected;
    }

}
