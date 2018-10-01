package com.bosowski.menus;

import com.bosowski.tools.Constants;

import javax.swing.*;
import java.awt.*;

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
}
