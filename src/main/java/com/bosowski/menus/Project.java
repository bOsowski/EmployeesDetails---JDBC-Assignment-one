package com.bosowski.menus;

import com.bosowski.main.Main;

import javax.swing.*;

public class Project extends Menu{

    public JTextField numberField = new JTextField(20);
    public JTextField nameField = new JTextField(20);//createRestrictedTextField(20);
    public JTextField locationField = new JTextField(20);
    public JTextField controlledByField = new JTextField(20);

    public Project(JTabbedPane tabWindow, Main parent) {
        super(parent);
        createUI(tabWindow);
        indexColumnName = "number";
    }

    public void createUI(JTabbedPane tabWindow) {
        JPanel jpanel = setUpJPanel();

        JLabel numberLabel = new JLabel("Number");
        JLabel nameLabel = new JLabel("Name");
        JLabel locationLabel = new JLabel("Location");
        JLabel controlledByLabel = new JLabel("Controller By");

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
}
