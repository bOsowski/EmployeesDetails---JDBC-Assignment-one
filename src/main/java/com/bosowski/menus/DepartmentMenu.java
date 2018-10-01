package com.bosowski.menus;

import com.bosowski.tools.Constants;
import com.bosowski.tools.DatabaseManager;

import javax.swing.*;

public class DepartmentMenu implements Menu{

    private JTextField numberField = new JTextField(20);//createRestrictedTextField(20);
    private JTextField nameField = new JTextField(20);
    private JTextField locationsField = new JTextField(20);

    private int currentNumber = 0;

    public void createUI(JTabbedPane tabWindow) {
        JPanel jpanel = setUpJPanel();

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

        tabWindow.addTab("Department", jpanel);
    }

    @Override
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
