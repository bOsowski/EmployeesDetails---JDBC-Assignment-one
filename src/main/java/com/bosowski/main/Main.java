package com.bosowski.main;

import com.bosowski.menus.Department;
import com.bosowski.menus.Employee;
import com.bosowski.menus.Project;
import com.bosowski.tools.Constants;
import javax.swing.*;
import java.sql.SQLException;


public class Main implements Runnable {

    public static void main(String args[]) throws SQLException {
        SwingUtilities.invokeLater(new Main());
    }


    @Override
    public void run() {
        JFrame frame = new JFrame("Employee Details");
        JTabbedPane tabWindow = new JTabbedPane();

        new Employee(tabWindow);
        new Department(tabWindow);
        new Project(tabWindow);

        frame.add(tabWindow);
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        frame.setVisible(true);
    }
}