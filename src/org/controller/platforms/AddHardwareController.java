/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller.platforms;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controller.AbstractController;
import org.model.Hardware;
import org.model.builder.BuilderFactory;
import org.model.builder.HardwareBuilder;
import org.sql.Database;

/**
 *
 * @author Dean
 */
public class AddHardwareController extends AbstractController {
    
    @FXML private TextField nameInput;
    @FXML private TextField descriptionInput;
    
    /**
     * Attempts to save the Hardware and add it to the Database.
     */
    @FXML
    private void saveHardware()
    {
        if(!validateFields(nameInput, descriptionInput))
        {
            showError("Please fill in all fields before saving!");
            return;
        }
        final HardwareBuilder builder = BuilderFactory.createHardwareBuilder();
        builder.id(Database.getSingleton().getNextId(Hardware.class));
        builder.name(nameInput.getText());
        builder.description(descriptionInput.getText());
        Database.getSingleton().add(builder.build());
        showPage("platforms.fxml");
    }
    
    /**
     * Returns to the previous page.
     */
    @FXML
    private void cancel()
    {
        showPage("platforms.fxml");
    }
    
    
}
