/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller.platforms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import org.controller.AbstractController;
import org.model.Entity;
import org.model.Hardware;
import org.model.Platform;
import org.model.builder.BuilderFactory;
import org.model.builder.PlatformBuilder;
import org.sql.Database;

/**
 *
 * @author Dean
 */
public class EditPlatformController extends AbstractController implements Initializable {
    
    @FXML private TextField nameInput;
    @FXML private ComboBox<Hardware> typeInput;
    @FXML private ListView<Hardware> componentList;
    
    private Platform editingPlatform;
    
    @Override
    public void initialize(final URL location, final ResourceBundle resources)
    {
        componentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (final Hardware hardware : Database.getSingleton().getHardware())
        {
            typeInput.getItems().add(hardware);
            componentList.getItems().add(hardware);
        }
    }
    
    /**
     * Sets the View's fields to the specified entity's details
     * @param entity The entity passed from the previous page
     */
    @Override
    public void set(final Entity entity)
    {
        if(!(entity instanceof Platform))
            return;
        Platform platform = (Platform) entity;
        nameInput.setText(platform.getName());
        for (int index = 0; index < typeInput.getItems().size(); index++)
        {
            if(typeInput.getItems().get(index).equals(platform.getConsoleType()))
            {
                typeInput.getSelectionModel().select(index);
                break;
            }
        }
        for (int index = 0; index < platform.getComponents().length; index++)
        {
            for (int sub = 0; sub < componentList.getItems().size(); sub++)
            {
                if(componentList.getItems().get(sub).equals(platform.getComponents()[index]))
                {
                    componentList.getSelectionModel().select(sub);
                    continue;
                }
            }
        }
        editingPlatform = platform;
    }
    
    /**
     * Attempts to save the Platform and add it to the Database.
     */
    @FXML
    private void savePlatform()
    {
        if(!validateFields(nameInput, typeInput, componentList))
        {
            showError("Please fill in all fields before saving!");
            return;
        }
        final PlatformBuilder builder = BuilderFactory.createPlatformBuilder();
        builder.id(editingPlatform.getId());
        builder.name(nameInput.getText());
        builder.consoleType(typeInput.getValue());
        builder.components(componentList.getSelectionModel().getSelectedItems().toArray(new Hardware[componentList.getSelectionModel().getSelectedItems().size()]));
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
