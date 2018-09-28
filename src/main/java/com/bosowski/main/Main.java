package com.bosowski.main;

import com.bosowski.tools.DatabaseManager;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.sql.SQLException;
import java.text.NumberFormat;


public class Main implements Runnable {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;

    public static void main(String args[]) throws SQLException {
        SwingUtilities.invokeLater(new Main());
    }


    @Override
    public void run() {
        JFrame frame = new JFrame("Employee Details");

        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setSize(new Dimension(WIDTH, HEIGHT));

        frame.add(verticalPanel);



        JPanel verticalButtonPanel = new JPanel();
        verticalButtonPanel.setLayout(new BoxLayout(verticalButtonPanel, BoxLayout.Y_AXIS));

        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton clearButton = new JButton("Clear");

        verticalButtonPanel.add(previousButton);
        verticalButtonPanel.add(nextButton);
        verticalButtonPanel.add(clearButton);




        JPanel bottomButtonPanel = new JPanel();

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");

        bottomButtonPanel.add(addButton);
        bottomButtonPanel.add(deleteButton);
        bottomButtonPanel.add(updateButton);


        JLabel ssnLabel = new JLabel("Ssn");
        JLabel nameLabel = new JLabel("Name");
        JLabel addressLabel = new JLabel("Address");
        JLabel dobLabel = new JLabel("DoB");
        JLabel salaryLabel = new JLabel("Salary");
        JLabel genderLabel = new JLabel("Gender");


        JTextField dobField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField addressField = new JTextField(20);
        JTextField salaryField = createRestrictedTextField(20);
        JTextField genderField = new JTextField(20);

        Dimension panelDimension = new Dimension(140, 10);

        JPanel ssnPanel = new JPanel();
        ssnPanel.setSize(panelDimension);
        ssnPanel.add(ssnLabel);
        ssnPanel.add(createRestrictedTextField(20));

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


        JPanel verticalLabelPanel = new JPanel();
        verticalLabelPanel.setLayout(new BoxLayout(verticalLabelPanel, BoxLayout.Y_AXIS));
        verticalLabelPanel.add(ssnPanel);
        verticalLabelPanel.add(dobPanel);
        verticalLabelPanel.add(namePanel);
        verticalLabelPanel.add(addressPanel);
        verticalLabelPanel.add(salaryPanel);
        verticalLabelPanel.add(genderPanel);

        JPanel horizontalPanel = new JPanel();
        horizontalPanel.add(verticalLabelPanel);
        horizontalPanel.add(verticalButtonPanel);

        verticalPanel.add(horizontalPanel);
        verticalPanel.add(bottomButtonPanel);


        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }


    private JFormattedTextField createRestrictedTextField(int size) {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setAllowsInvalid(false);
        JFormattedTextField field = new JFormattedTextField(numberFormatter);
        field.setColumns(size);

        return field;
    }
}