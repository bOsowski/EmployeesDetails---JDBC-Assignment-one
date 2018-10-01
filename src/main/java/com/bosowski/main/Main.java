package com.bosowski.main;

import com.bosowski.menus.DepartmentMenu;
import com.bosowski.menus.EmployeeMenu;
import com.bosowski.menus.ProjectMenu;
import com.bosowski.tools.Constants;
import com.bosowski.tools.DatabaseManager;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class Main implements Runnable {

    private DepartmentMenu departmentMenu = new DepartmentMenu();
    private EmployeeMenu employeeMenu = new EmployeeMenu();
    private ProjectMenu projectMenu = new ProjectMenu();


    public static void main(String args[]) throws SQLException {
        SwingUtilities.invokeLater(new Main());
    }


    @Override
    public void run() {
        JFrame frame = new JFrame("Employee Details");
        JTabbedPane tabWindow = new JTabbedPane();

        employeeMenu.createUI(tabWindow);
        departmentMenu.createUI(tabWindow);
        projectMenu.createUI(tabWindow);

        frame.add(tabWindow);
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        frame.setVisible(true);
    }

//    private JFormattedTextField createRestrictedTextField(int size) {
//        NumberFormat format = NumberFormat.getInstance();
//        NumberFormatter numberFormatter = new NumberFormatter(format);
//        numberFormatter.setAllowsInvalid(false);
//        JFormattedTextField field = new JFormattedTextField(numberFormatter);
//        field.setColumns(size);
//
//        return field;
//    }
}