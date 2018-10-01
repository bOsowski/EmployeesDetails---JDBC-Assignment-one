package com.bosowski.menus;

import com.bosowski.tools.Constants;
import com.bosowski.tools.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Employee extends Menu{

    public JTextField ssnField = new JTextField(20);//createRestrictedTextField(20);
    public JTextField dobField = new JTextField(20);
    public JTextField nameField = new JTextField(20);
    public JTextField addressField = new JTextField(20);
    public JTextField salaryField = new JTextField(20);//createRestrictedTextField(20);
    public JTextField genderField = new JTextField(20);

    public Employee(JTabbedPane tabWindow) {
        createUI(tabWindow);
//        HashMap<String, ArrayList<Object>> result;
//        try {
//            result = DatabaseManager.instance.executeQuery("select * from Employee where Ssn > "+currentEmployeeSsn+" order by Ssn asc limit 1;");
//            System.out.println(result);
//            ssnField.setText((String)result.get("Ssn").get(0));
//            dobField.setText((String)result.get("Bdate").get(0));
//            nameField.setText((String)result.get("Name").get(0));
//            addressField.setText((String)result.get("Address").get(0));
//            salaryField.setText((String)result.get("Salary").get(0));
//            genderField.setText((String)result.get("Gender").get(0));
//            currentEmployeeSsn = Integer.parseInt(ssnField.getText());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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

        //ssnField.setText();

        JPanel ssnPanel = new JPanel();
        ssnPanel.setSize(panelDimension);
        ssnPanel.add(ssnLabel);
        ssnPanel.add(ssnField);

        JPanel dobPanel = new JPanel();
        dobPanel.setSize(panelDimension);
        dobPanel.add(dobLabel);
        dobPanel.add(dobField);

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
        genderPanel.add(genderField);

        contentPanel.add(ssnPanel);
        contentPanel.add(dobPanel);
        contentPanel.add(namePanel);
        contentPanel.add(addressPanel);
        contentPanel.add(salaryPanel);
        contentPanel.add(genderPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        horizontalPanel.add(contentPanel);
    }

    public void setUpButtonActionHandlers() {
        addButton.addActionListener(e -> {
            try{
                DatabaseManager.instance.executeUpdate(
                        "insert into employee(Ssn, Name, Address, Bdate, Salary, Sex) " +
                                "values ("+ssnField.getText() +
                                ", '"+nameField.getText()+"', " +
                                "'"+addressField.getText()+"', "+
                                "'"+dobField.getText()+"', "+
                                salaryField.getText()+
                                ", '"+genderField.getText()+"');");
            }catch (Exception exception){
                System.out.println(exception.toString());
            }
        });
    }


}
