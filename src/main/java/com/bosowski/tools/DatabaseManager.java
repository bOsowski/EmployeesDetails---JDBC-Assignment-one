package com.bosowski.tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager {
    public static final DatabaseManager instance = new DatabaseManager();

    private Connection conn;

    private DatabaseManager() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment1?user=root&password=");
            System.out.println("Successfully Connected!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     *
     * @param sqlQuery -> the query to execute.
     * @return A map with column names as keys containing an arraylist of values for each key.
     * @throws SQLException
     */
    public HashMap<String, ArrayList<Object>> executeQuery(String sqlQuery) throws SQLException {
        HashMap<String, ArrayList<Object>> result = new HashMap<>();
        ResultSet rs = conn.prepareStatement(sqlQuery).executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();

        for(int i = 1; i < rsmd.getColumnCount(); i++){
            result.put(rsmd.getColumnLabel(i), new ArrayList<>());
        }

        while(rs.next()){
            for(String columnName: result.keySet()){
                result.get(columnName).add(rs.getString(columnName));
            }
        }
        return result;
    }


    /**
     *
     * @param sqlUpdate -> the query to execute.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 for SQL statements that return nothing.
     * @throws SQLException
     */
    public int executeUpdate(String sqlUpdate) {
        System.out.println(sqlUpdate);
        try {
            conn.createStatement().executeUpdate(sqlUpdate);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
