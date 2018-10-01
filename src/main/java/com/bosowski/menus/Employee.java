package com.bosowski.menus;

import com.bosowski.main.Main;
import com.bosowski.tools.DatabaseManager;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Employee extends Menu{

    public JTextField ssnField = new JTextField(20);//createRestrictedTextField(20);
    public JTextField bdateField = new JTextField(20);
    public JTextField nameField = new JTextField(20);
    public JTextField addressField = new JTextField(20);
    public JTextField salaryField = new JTextField(20);//createRestrictedTextField(20);
    public JTextField sexField = new JTextField(20);
    public JComboBox<String> works_forField = new JComboBox<>();

    public Employee(JTabbedPane tabWindow, Main parent) {
        super(parent);
        createUI(tabWindow);
        refreshDepartmentsField();
        indexColumnName = "ssn";
    }


    public void createUI(JTabbedPane tabWindow) {
        JPanel verticalPanel = setUpJPanel();
        tabWindow.addTab("Employee", verticalPanel);

        horizontalPanel.add(contentPanel);
        horizontalPanel.add(verticalButtonPanel);

        verticalPanel.add(horizontalPanel);
        verticalPanel.add(bottomButtonPanel);

        JLabel ssnLabel = new JLabel("Ssn");
        JLabel nameLabel = new JLabel("Name");
        JLabel addressLabel = new JLabel("Address");
        JLabel dobLabel = new JLabel("DoB");
        JLabel salaryLabel = new JLabel("Salary");
        JLabel genderLabel = new JLabel("Gender");
        JLabel departmentsLabel = new JLabel("Works for");

        //ssnField.setText();

        JPanel ssnPanel = new JPanel();
        ssnPanel.setSize(panelDimension);
        ssnPanel.add(ssnLabel);
        ssnPanel.add(ssnField);

        JPanel dobPanel = new JPanel();
        dobPanel.setSize(panelDimension);
        dobPanel.add(dobLabel);
        dobPanel.add(bdateField);

        JPanel namePanel = new JPanel();
        namePanel.setSize(panelDimension);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel addressPanel = new JPanel();
        addressPanel.setSize(panelDimension);
        addressPanel.add(addressLabel);
        addressPanel.add(addressField);

        JPanel salaryPanel = new JPanel();
        salaryPanel.setSize(panelDimension);
        salaryPanel.add(salaryLabel);
        salaryPanel.add(salaryField);

        JPanel genderPanel = new JPanel();
        genderPanel.setSize(panelDimension);
        genderPanel.add(genderLabel);
        genderPanel.add(sexField);

        JPanel departmentsPanel = new JPanel();
        departmentsPanel.setSize(panelDimension);
        departmentsPanel.add(departmentsLabel);
        departmentsPanel.add(works_forField);

        contentPanel.add(ssnPanel);
        contentPanel.add(dobPanel);
        contentPanel.add(namePanel);
        contentPanel.add(addressPanel);
        contentPanel.add(salaryPanel);
        contentPanel.add(genderPanel);
        contentPanel.add(departmentsPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        horizontalPanel.add(contentPanel);
    }

    public void refreshDepartmentsField(){
        works_forField.removeAllItems();
        try {
            LinkedHashMap<String, ArrayList<Object>> results = DatabaseManager.instance.executeQuery("select * from department");
            for(int i = 0; i<results.get("number").size(); i++){
                works_forField.addItem(results.get("number").get(i) + " - " + results.get("name").get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
