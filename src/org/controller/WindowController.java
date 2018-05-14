/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.sql.Database;

/**
 *
 * @author Dean
 */
public class WindowController implements Initializable {
    
    private final HashMap<ToggleButton, String> BUTTON_MAP = new HashMap<>();
    
    @FXML
    private Pane contentPane;
    @FXML
    private GridPane navigationGrid;
    
    @Override
    public void initialize(final URL location, final ResourceBundle resources)
    {
        addButton("Users", "users.fxml");
        addButton("Platforms", "platforms.fxml");
        addButton("Software", "software.fxml");
        addButton("Bookings", "bookings.fxml");
    }
     
    /**
     * Adds a button to the navigation grid on the left side of the window
     * @param buttonText The text for the button
     * @param fxmlLayout The FXML file to associate the button with
     */
    private void addButton(final String buttonText, final String fxmlLayout)
    {
        final ToggleButton button = new ToggleButton(buttonText);
        button.getStyleClass().add("navigation_button");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e)
            {
                onButtonClick(e);
            }
        });
        if(navigationGrid.getChildren().isEmpty())
        {
            setContentLayout(fxmlLayout);
            button.setSelected(true);
        }
        BUTTON_MAP.put(button, fxmlLayout);
        navigationGrid.add(button, 0, navigationGrid.getChildren().size());
    }
    
    /**
     * Handles all button clicks within this View
     * @param event 
     */
    @FXML
    private void onButtonClick(ActionEvent event)
    {
        if(event.getSource() instanceof Button)
        {
            final String text = ((Button) event.getSource()).getText();
            if(text.equalsIgnoreCase("exit"))
            {
                Database.getSingleton().save();
                System.out.println("Closing application.");
                System.exit(-1);
            }
        }
        if(event.getSource() instanceof ToggleButton)
        {
            final ToggleButton clickedButton = (ToggleButton) event.getSource();
            if(BUTTON_MAP.containsKey(clickedButton))
            {
                setContentLayout(BUTTON_MAP.get(clickedButton));
                for (final Map.Entry<ToggleButton, String> entry : BUTTON_MAP.entrySet())
                {
                    if(!entry.getKey().equals(clickedButton))
                        entry.getKey().setSelected(false);
                }
                clickedButton.setSelected(true);
            }
        }        
    }
    
    /**
     * Removes all children from the contentPane and adds the specified layout
     * @param fxmlDocument The path to the FXML file to load
     */
    private void setContentLayout(final String fxmlDocument)
    {
        try {
            final Pane loadedPane = FXMLLoader.load(getClass().getResource("/layout/" + fxmlDocument));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(loadedPane);
        } catch(final IOException e) {
            e.printStackTrace();
        }
    }
        
    
}
