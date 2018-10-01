package com.bosowski.menus;

import com.bosowski.tools.Constants;
import com.bosowski.tools.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeMenu implements Menu{

    private JTextField ssnField = new JTextField(20);//createRestrictedTextField(20);
    private JTextField dobField = new JTextField(20);
    private JTextField nameField = new JTextField(20);
    private JTextField addressField = new JTextField(20);
    private JTextField salaryField = new JTextField(20);//createRestrictedTextField(20);
    private JTextField genderField = new JTextField(20);

    private int currentEmployeeSsn = 0;

    public EmployeeMenu(){
//        HashMap<String, ArrayList<Object>> result;
//        try {
//            result = DatabaseManager.instance.executeQuery("select * from Employee where Ssn > "+currentEmployeeSsn+" order by Ssn asc limit 1;");
//            System.out.println(result);
//            dobField.setText((String)result.get("Bdate").get(0));
//            nameField.setText((String)result.get("Name").get(0));
//            addressField.setText((String)result.get("Address").get(0));
//            salaryField.setText((String)result.get("Salary").get(0));
//            genderField.setText((String)result.get("Gender").get(0));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void createUI(JTabbedPane tabWindow) {
//        JPanel verticalPanel = setUpJPanel();
        JPanel verticalPanel = new JPanel();
        tabWindow.addTab("Employee", verticalPanel);

        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));


        //Add buttons to panels
        bottomButtonPanel.add(addButton);
        bottomButtonPanel.add(deleteButton);
        bottomButtonPanel.add(updateButton);

        verticalButtonPanel.setLayout(new BoxLayout(verticalButtonPanel, BoxLayout.Y_AXIS));
        verticalButtonPanel.add(previousButton);
        verticalButtonPanel.add(nextButton);
        verticalButtonPanel.add(clearButton);


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

    @Override
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
