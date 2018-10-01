package com.bosowski.main;

import com.bosowski.menus.DepartmentMenu;
import com.bosowski.menus.EmployeeMenu;
import com.bosowski.menus.ProjectMenu;
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

        new EmployeeMenu(tabWindow);
        new DepartmentMenu(tabWindow);
        new ProjectMenu(tabWindow);

        frame.add(tabWindow);
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        frame.setVisible(true);
    }
}