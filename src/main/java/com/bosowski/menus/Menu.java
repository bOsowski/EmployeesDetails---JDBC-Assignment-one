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

    private JButton previousButton = new JButton("Previous");
    private JButton nextButton = new JButton("Next");
    private JButton clearButton = new JButton("Clear");
    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete");
    private JButton updateButton = new JButton("Update");

    JPanel verticalButtonPanel = new JPanel();
    JPanel bottomButtonPanel = new JPanel();
    JPanel horizontalPanel = new JPanel();
    JPanel contentPanel = new JPanel();
    Dimension panelDimension = new Dimension(140, 10);

    private int index = Integer.MIN_VALUE;
    protected String indexColumnName;

    protected Menu(Main parent){
        this.parent = parent;

        //Below adds actions to buttons.
        previousButton.addActionListener(a -> {
            loadPrevious();
        });
        nextButton.addActionListener(a -> {
            loadNext();
        });
        clearButton.addActionListener(a -> {
            try {
                clear();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        deleteButton.addActionListener(a -> {
            try {
                delete();
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
        addButton.addActionListener(a -> {
            try {
                save();
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        updateButton.addActionListener(a -> {
            try {
                update();
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    abstract void createUI(JTabbedPane tabWindow);

    protected JPanel setUpJPanel(){
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

    protected void loadNext(){
        try {
            String query = "select * from " + getClass().getSimpleName() + " where " + indexColumnName + " > "+index+" order by 1 limit 1";
            load(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void loadPrevious(){
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

    protected void clear() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field[] fields = getClass().getFields();
        for (Field field : fields) {

            if (field.get(this) instanceof JTextField) {
                JTextField.class.getMethod("setText", String.class).invoke(field.get(this), "");
            }

            //do not clear the dropdowns
            //else if(fields[i].get(this) instanceof JComboBox){
            //}
        }
    }

    protected void update() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int oldIndex = index;
        StringBuilder setStatement = new StringBuilder();
        Field[] fields = getClass().getFields();

        for(int i = 0; i<fields.length; i++){
            setStatement.append(fieldToColumnName(fields[i]));
            setStatement.append(" = ");

            if (fields[i].get(this) instanceof JTextField) {
                String value = JTextField.class.getMethod("getText").invoke(fields[i].get(this)).toString();
                //get the new index in case it has changed
                if(i == 0){index = Integer.parseInt(value);}
                setStatement.append("'").append(value).append("'");
            } else if (fields[i].get(this) instanceof JComboBox) {
                String dropdownValue = JComboBox.class.getMethod("getSelectedItem").invoke(fields[i].get(this)).toString();
                setStatement.append("'").append(dropdownValue).append("'");
            }

            if(i != fields.length-1){
                setStatement.append(", ");
            }
        }

        String query = "update " + getClass().getSimpleName() + " set " + setStatement + " where " + indexColumnName + " = " +oldIndex ;
        DatabaseManager.instance.executeUpdate(query);
    }


    /**
     * This method dynamically retrieves values saved in the parent object's
     * public fields and dynamically creates an insert statement based on
     * the parent class name and parent public fields.
     */
    protected void save() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringBuilder valueNames = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        Field[] fields = getClass().getFields();

        for(int i = 0; i<fields.length; i++){
            valueNames.append(fieldToColumnName(fields[i]));

            if(fields[i].get(this) instanceof JTextField){
                values.append("'").append(JTextField.class.getMethod("getText").invoke(fields[i].get(this))).append("'");
            }
            else if(fields[i].get(this) instanceof JComboBox){
                String dropdownValue = JComboBox.class.getMethod("getSelectedItem").invoke(fields[i].get(this)).toString();
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

    protected void delete() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        DatabaseManager.instance.executeUpdate("delete from "+getClass().getSimpleName()+" where "+indexColumnName + " = '" +
        JTextField.class.getMethod("getText").invoke(getClass().getFields()[0].get(this)) +"'");
        clear();
    }

    private String fieldToColumnName(Field field){
        return field.getName().substring(0, field.getName().length()-"Field".length()).toLowerCase();
    }
}
