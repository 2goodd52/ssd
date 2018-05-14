/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.model.Entity;

/**
 *
 * @author Dean
 */
public abstract class AbstractController {
    
    @FXML
    private AnchorPane contentPane;

    /**
     * Displays a confirmation dialogue asking if the user wants to delete an entry
     * @return whether or not the user clicks yes or ok
     */
    public boolean delete()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setContentText("Are you sure you want to delete this entry?");
        return alert.showAndWait().get() == ButtonType.OK;
    }
    
    /**
     * Displays an Error Alert on the screen with the given message
     * @param message The message to display in the error dialogue
     */
    protected void showError(final String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
        return;
    }
    
    /**
     * Checks if all input fields are valid (not empty or null)
     * @param controls The array of different Controls to check
     * @return true if none of the controls are empty
     */
    protected boolean validateFields(final Control... controls)
    {
        for (final Control control : controls)
        {
            if(control instanceof TextField)
            {
                if(((TextField) control).getText().trim().isEmpty())
                {
                    control.setStyle("-fx-border-color:red;");
                    return false;
                }
            }
            if(control instanceof TextArea)
            {
                if(((TextArea) control).getText().trim().isEmpty())
                {
                    control.setStyle("-fx-border-color:red;");
                    return false;
                }
            }
            if(control instanceof DatePicker)
            {
                if(((DatePicker) control).getValue() == null)
                {
                    control.setStyle("-fx-border-color:red;");
                    return false;
                }
            }
            if(control instanceof ListView)
            {
                if(((ListView) control).getSelectionModel().isEmpty())
                {
                    control.setStyle("-fx-border-color:red;");
                    return false;
                }
            }
            if(control instanceof ComboBox)
            {
                if(((ComboBox) control).getSelectionModel().getSelectedItem() == null)
                {
                    control.setStyle("-fx-border-color:red;");
                    return false;
                }
            }
            if(!control.getStyle().isEmpty())
                control.setStyle("");
        }
        return true;
    }
    
    /**
     * Loads a new FXML view from the layout and switches it with the current contentPane
     * @param layout The FXML layout to load
     */
    protected void showPage(final String layout)
    {
        try {
            final AnchorPane addUser = FXMLLoader.load(getClass().getResource("/layout/" + layout));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(addUser);
        } catch(final Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Loads a new FXML view from the layout and switches it with the current contentPane
     * Also gets the AbstractController and calls the 'set(Entity)' method
     * @param layout The FXML layout to load
     * @param set The Entity to pass to the loaded controller
     */
    protected void showPage(final String layout, final Entity set)
    {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/" + layout));
            final Parent root = (Parent) loader.load();
            AbstractController controller = loader.<AbstractController>getController();
            controller.set(set);
            contentPane.getChildren().clear();
            contentPane.getChildren().add(root);
        } catch(final Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Abstract function to be overridden if needed to assign an Entity to a controller
     * @param entity 
     */
    protected void set(final Entity entity)
    {
        System.out.println("Set is not available for this controller!");
    }
    
}
