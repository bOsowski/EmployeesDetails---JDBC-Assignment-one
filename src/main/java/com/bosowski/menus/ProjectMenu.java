package com.bosowski.menus;

import com.bosowski.tools.DatabaseManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectMenu extends Menu{

    private JTextField numberField = new JTextField(20);
    private JTextField nameField = new JTextField(20);//createRestrictedTextField(20);
    private JTextField locationField = new JTextField(20);
    private JTextField controlledByField = new JTextField(20);

    private JLabel numberLabel = new JLabel("Number");
    private JLabel nameLabel = new JLabel("Name");
    private JLabel locationLabel = new JLabel("Location");
    private JLabel controlledByLabel = new JLabel("Controller By");

    private int currentNumber = 0;

    public ProjectMenu(JTabbedPane tabWindow) {
        createUI(tabWindow);
        HashMap<String, ArrayList<Object>> result;
        try {
            result = DatabaseManager.instance.executeQuery("select * from Employee where Ssn > "+currentNumber+" order by Ssn asc limit 1;");
            System.out.println(result);
            numberField.setText((String)result.get("Number").get(0));
            nameField.setText((String)result.get("Name").get(0));
            locationField.setText((String)result.get("Location").get(0));
            controlledByField.setText((String)result.get("ControlledBy").get(0));
            currentNumber = Integer.parseInt(numberField.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createUI(JTabbedPane tabWindow) {
        JPanel jpanel = setUpJPanel();

        JPanel numberPanel = new JPanel();
        numberPanel.setSize(panelDimension);
        numberPanel.add(numberLabel);
        numberPanel.add(numberField);

        JPanel namePanel = new JPanel();
        namePanel.setSize(panelDimension);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel locationPanel = new JPanel();
        locationPanel.setSize(panelDimension);
        locationPanel.add(locationLabel);
        locationPanel.add(locationField);

        JPanel controlledByPanel = new JPanel();
        controlledByPanel.setSize(panelDimension);
        controlledByPanel.add(controlledByLabel);
        controlledByPanel.add(controlledByField);

        contentPanel.add(numberPanel);
        contentPanel.add(namePanel);
        contentPanel.add(locationPanel);
        contentPanel.add(controlledByPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        horizontalPanel.add(contentPanel);

        tabWindow.addTab("Project", jpanel);
    }

    public void setUpButtonActionHandlers() {

    }
}
