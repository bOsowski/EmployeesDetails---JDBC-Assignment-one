package com.bosowski.menus;

import com.bosowski.main.Main;
import com.bosowski.tools.DatabaseManager;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Department extends Menu{

    public JTextField numberField = new JTextField(20);//createRestrictedTextField(20);
    public JTextField nameField = new JTextField(20);
    public JTextField locationsField = new JTextField(20);

    public Department(JTabbedPane tabWindow, Main parent) {
        super(parent);
        createUI(tabWindow);
        indexColumnName = "number";
        loadNext();
    }

    public void createUI(JTabbedPane tabWindow) {
        JPanel tabPanel = setUpJPanel();
        tabWindow.addTab("Department", tabPanel);

        JLabel nameLabel = new JLabel("Name");
        JLabel numberLabel = new JLabel("Number");
        JLabel locationsLabel = new JLabel("Locations");

        JPanel numberPanel = new JPanel();
        numberPanel.setSize(panelDimension);
        numberPanel.add(numberLabel);
        numberPanel.add(numberField);

        JPanel namePanel = new JPanel();
        namePanel.setSize(panelDimension);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel locationsPanel = new JPanel();
        locationsPanel.setSize(panelDimension);
        locationsPanel.add(locationsLabel);
        locationsPanel.add(locationsField);

        contentPanel.add(numberPanel);
        contentPanel.add(namePanel);
        contentPanel.add(locationsPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        horizontalPanel.add(contentPanel);
    }

    public static void refreshDepartmentsField(JComboBox dropdown){
        System.out.println("Refreshing employee's departments field..");
        String currentValue = (String)dropdown.getSelectedItem();

        dropdown.removeAllItems();
        try {
            LinkedHashMap<String, ArrayList<Object>> results = DatabaseManager.instance.executeQuery("select * from department");
            for(int i = 0; i<results.get("number").size(); i++){
                dropdown.addItem(results.get("number").get(i).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(currentValue != null){
            dropdown.setSelectedItem(currentValue);
        }
    }

}
