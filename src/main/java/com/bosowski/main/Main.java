package com.bosowski.main;

import com.bosowski.tools.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String args[]) throws SQLException {
        System.out.println(DatabaseManager.instance.executeQuery("desc Employee"));
    }



}
