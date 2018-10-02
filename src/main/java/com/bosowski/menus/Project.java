package com.bosowski.menus;

import javax.swing.*;

public class Project extends Menu {

    public JTextField numberField = new JTextField(20);
    public JTextField nameField = new JTextField(20);//createRestrictedTextField(20);
    public JTextField locationField = new JTextField(20);
    public JComboBox<String> controlled_ByField = new JComboBox<>();

    public Project(JTabbedPane tabWindow) {
        super("number");
        createUI(tabWindow);
        Department.refreshDepartmentsField(controlled_ByField);
        loadNext();
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
        controlledByPanel.add(controlled_ByField);

        contentPanel.add(numberPanel);
        contentPanel.add(namePanel);
        contentPanel.add(locationPanel);
        contentPanel.add(controlledByPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        horizontalPanel.add(contentPanel);

        tabWindow.addTab("Project", jpanel);
    }

}


