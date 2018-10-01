package com.bosowski.menus;

import com.bosowski.tools.Constants;
import com.bosowski.tools.DatabaseManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Department extends Menu{

    public JTextField numberField = new JTextField(20);//createRestrictedTextField(20);
    public JTextField nameField = new JTextField(20);
    public JTextField locationsField = new JTextField(20);

    public Department(JTabbedPane tabWindow) {
        createUI(tabWindow);
//        HashMap<String, ArrayList<Object>> result;
//        try {
//            result = DatabaseManager.instance.executeQuery("select * from Department where number > "+currentNumber+" order by number asc limit 1;");
//            System.out.println(result);
//            numberField.setText((String)result.get("Number").get(0));
//            nameField.setText((String)result.get("Name").get(0));
//            locationsField.setText((String)result.get("Locations").get(0));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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

    public void setUpButtonActionHandlers() {
        addButton.addActionListener(e -> {
            try{
                DatabaseManager.instance.executeUpdate(
                        "insert into department(Name, Number, Locations) " +
                                "values ("+nameField.getText() +
                                ", "+numberField.getText()+", " +
                                ", '"+locationsField.getText()+"');");
            }catch (Exception exception){
                System.out.println(exception.toString());
            }
        });
    }
}
