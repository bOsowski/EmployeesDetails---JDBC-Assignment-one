package com.bosowski.menus;

import com.bosowski.tools.Constants;
import com.bosowski.tools.DatabaseManager;

import java.lang.reflect.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.*;

abstract class Menu {

    JButton previousButton = new JButton("Previous");
    JButton nextButton = new JButton("Next");
    JButton clearButton = new JButton("Clear");
    JButton addButton = new JButton("Add");
    JButton deleteButton = new JButton("Delete");
    JButton updateButton = new JButton("Update");

    JPanel verticalButtonPanel = new JPanel();
    JPanel bottomButtonPanel = new JPanel();
    JPanel horizontalPanel = new JPanel();
    JPanel contentPanel = new JPanel();
    Dimension panelDimension = new Dimension(140, 10);

    int index = -1;

    protected Menu(){
        loadNext();

        previousButton.addActionListener(a -> {
            loadPrevious();
        });
        nextButton.addActionListener(a -> {
            loadNext();
        });
        addButton.addActionListener(a -> {
            save();
        });
    }

    abstract void createUI(JTabbedPane tabWindow);

    abstract void setUpButtonActionHandlers();

    JPanel setUpJPanel(){
        JPanel verticalPanel = new JPanel();
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

        return verticalPanel;
    }

    public void loadNext(){
        String query = "select * from " + getClass().getSimpleName() + " where 1 > "+index+" limit 1";
        try {
            load(query);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadPrevious(){
        String query = "select * from " + getClass().getSimpleName() + " where 1 < "+index+" limit 1";
        try {
            load(query);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method dynamically loads in information into the parent class' buttons
     * based on the parent's fields.
     * @param query
     */
    private void load(String query) throws NoSuchMethodException, IllegalAccessException, SQLException, InvocationTargetException {
        LinkedHashMap<String, ArrayList<Object>> results = DatabaseManager.instance.executeQuery(query);
        Field[] fields = getClass().getFields();
        for(Field field: fields){
            if(!field.getName().contains("Field")){continue;}
            JTextField.class.getMethod("setText", String.class).invoke(field.get(this), results.get(fieldToColumnName(field)).get(0).toString());
        }

        index = Integer.parseInt(results.entrySet().iterator().next().toString());

        System.out.println(query);
        System.out.println(Arrays.toString(fields));
    }

    /**
     * This method dynamically retrieves values saved in the parent object's
     * public fields and dynamically creates an insert statement based on
     * the parent class name and parent public fields.
     */
    private void save() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringBuilder valueNames = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        Field[] fields = getClass().getFields();

        for(int i = 0; i<fields.length; i++){
            if(!fields[i].getName().contains("Field")){continue;}

            valueNames.append(fieldToColumnName(fields[i]));

            values.append("'").append(JTextField.class.getMethod("getText").invoke(fields[i].get(this))).append("'");


            if(i != fields.length-1){
                valueNames.append(", ");
                values.append(", ");
            }
            else {
                valueNames.append(")");
                values.append(")");
            }
        }

        String query = "insert into " + getClass().getSimpleName() + valueNames + " values " + values;
        DatabaseManager.instance.executeUpdate(query);
    }

    private String fieldToColumnName(Field field){
        return field.getName().substring(0, field.getName().length()-"Field".length()).toLowerCase();
    }
}
