package com.bosowski.main;

import com.bosowski.menus.Department;
import com.bosowski.menus.Employee;
import com.bosowski.menus.Project;
import com.bosowski.tools.Constants;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.sql.SQLException;


public class Main implements Runnable {

    public Employee employee;
    public Department department;
    public Project project;

    public static void main(String args[]) throws SQLException {
        SwingUtilities.invokeLater(new Main());
    }


    @Override
    public void run() {
        JFrame frame = new JFrame("Employee Details");
        JTabbedPane tabWindow = new JTabbedPane();

        employee = new Employee(tabWindow, this);
        department = new Department(tabWindow, this);
        project = new Project(tabWindow, this);

        tabWindow.addChangeListener(l -> {
            if(tabWindow.getSelectedIndex() == 0){
                System.out.println("Refreshing employee's departments field..");
                employee.refreshDepartmentsField();
            }
        });



        frame.add(tabWindow);
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        frame.setVisible(true);
    }
}