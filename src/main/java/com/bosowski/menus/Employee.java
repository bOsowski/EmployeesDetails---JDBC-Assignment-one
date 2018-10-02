package com.bosowski.menus;

import com.bosowski.main.Main;
import com.bosowski.tools.DatabaseManager;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
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
    public JComboBox<String> managesField = new JComboBox<>();
    public JComboBox<String> supervisesField = new JComboBox<>();

    public Employee(JTabbedPane tabWindow, Main parent) {
        super(parent);
        createUI(tabWindow);
        Department.refreshDepartmentsField(works_forField);
        refreshEmployeeFields();
        indexColumnName = "ssn";
        loadNext();
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
        JLabel managesLabel = new JLabel("Manages");
        JLabel supervisesLabel = new JLabel("Supervises");


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

        JPanel managesPanel = new JPanel();
        managesPanel.setSize(panelDimension);
        managesPanel.add(managesLabel);
        managesPanel.add(managesField);

        JPanel supervisesPanel = new JPanel();
        supervisesPanel.setSize(panelDimension);
        supervisesPanel.add(supervisesLabel);
        supervisesPanel.add(supervisesField);

        contentPanel.add(ssnPanel);
        contentPanel.add(dobPanel);
        contentPanel.add(namePanel);
        contentPanel.add(addressPanel);
        contentPanel.add(salaryPanel);
        contentPanel.add(genderPanel);
        contentPanel.add(departmentsPanel);
        contentPanel.add(managesPanel);
        contentPanel.add(supervisesPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        horizontalPanel.add(contentPanel);
    }

    public void refreshEmployeeFields(){
        String currentManagesFieldValue = (String)managesField.getSelectedItem();
        String currentSupervisesFieldValue = (String)supervisesField.getSelectedItem();

        managesField.removeAllItems();
        supervisesField.removeAllItems();
        try {
            LinkedHashMap<String, ArrayList<Object>> results = DatabaseManager.instance.executeQuery("select * from employee");
            for(int i = 0; i<results.get("ssn").size(); i++){
                String valueToAdd = results.get("ssn").get(i).toString();
                managesField.addItem(valueToAdd);
                supervisesField.addItem(valueToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(currentManagesFieldValue != null){
            managesField.setSelectedItem(currentManagesFieldValue);
        }
        if(currentSupervisesFieldValue != null){
            supervisesField.setSelectedItem(currentSupervisesFieldValue);
        }

    }

    @Override
    protected void save() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        super.save();
        refreshEmployeeFields();
    }

    @Override
    protected void delete() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        super.delete();
        refreshEmployeeFields();
    }

    @Override
    protected void update() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        super.update();
        refreshEmployeeFields();
    }
}
