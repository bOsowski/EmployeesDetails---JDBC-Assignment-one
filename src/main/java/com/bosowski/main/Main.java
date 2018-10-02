package com.bosowski.main;

import com.bosowski.menus.Department;
import com.bosowski.menus.Employee;
import com.bosowski.menus.Project;
import com.bosowski.tools.Constants;
import javax.swing.*;

public class Main implements Runnable {

    public Employee employee;
    public Department department;
    public Project project;

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Main());
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Employee Details");
        JTabbedPane tabWindow = new JTabbedPane();

        employee = new Employee(tabWindow);
        department = new Department(tabWindow);
        project = new Project(tabWindow);

        tabWindow.addChangeListener(l -> {
            if(tabWindow.getSelectedIndex() == 0){
                Department.refreshDepartmentsField(employee.works_forField);
            }
            else if(tabWindow.getSelectedIndex() == 2){
                Department.refreshDepartmentsField(project.controlled_ByField);
            }
        });

        frame.add(tabWindow);
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        frame.setVisible(true);
    }
}