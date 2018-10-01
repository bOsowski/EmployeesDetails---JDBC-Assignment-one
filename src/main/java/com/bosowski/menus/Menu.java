package com.bosowski.menus;

import com.bosowski.main.Main;
import com.bosowski.tools.Constants;
import com.bosowski.tools.DatabaseManager;

import java.lang.reflect.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

abstract class Menu {

    Main parent;

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
    protected String indexColumnName;

    protected Menu(Main parent){
        this.parent = parent;

        loadNext();

        previousButton.addActionListener(a -> {
            loadPrevious();
        });
        nextButton.addActionListener(a -> {
            loadNext();
        });
        addButton.addActionListener(a -> {
            try {
                save();
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    abstract void createUI(JTabbedPane tabWindow);

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
        try {
            String query = "select * from " + getClass().getSimpleName() + " where " + indexColumnName + " > "+index+" order by 1 limit 1";
            load(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPrevious(){
        try {
            String query = "select * from " + getClass().getSimpleName() + " where " + indexColumnName + " < "+index+" order by 1 desc limit 1";
            load(query);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | SQLException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method dynamically loads in information into the parent class' buttons
     * based on the parent's fields.
     * @param query
     */
    private void load(String query) throws NoSuchMethodException, IllegalAccessException, SQLException, InvocationTargetException, IndexOutOfBoundsException {
        LinkedHashMap<String, ArrayList<Object>> results = DatabaseManager.instance.executeQuery(query);

        System.out.println(this.getClass().getSimpleName()+ " -> Results from load = "+results.toString());

        Field[] fields = getClass().getFields();
        for(int i = 0; i<fields.length; i++){

            ArrayList<Object> valueToSet = results.get(fieldToColumnName(fields[i]));
            if(valueToSet != null && !valueToSet.isEmpty() && valueToSet.get(0) != null){
                if(i == 0){index = Integer.parseInt(valueToSet.get(0).toString());}


                if(fields[i].get(this) instanceof JTextField){
                    JTextField.class.getMethod("setText", String.class).invoke(fields[i].get(this), valueToSet.get(0).toString());
                }
                else if(fields[i].get(this) instanceof JComboBox){
                    JComboBox.class.getMethod("setSelectedItem", Object.class).invoke(fields[i].get(this), valueToSet.get(0).toString());
                }

            }
        }

        System.out.println(query);
        System.out.println("Index = "+index);
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
            valueNames.append(fieldToColumnName(fields[i]));

            if(fields[i].get(this) instanceof JTextField){
                values.append("'").append(JTextField.class.getMethod("getText").invoke(fields[i].get(this))).append("'");
            }
            else if(fields[i].get(this) instanceof JComboBox){
                String dropdownValue = JComboBox.class.getMethod("getSelectedItem").invoke(fields[i].get(this)).toString().split(" - ")[0];
                values.append("'").append(dropdownValue).append("'");
            }
            else{
                continue;
            }


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
