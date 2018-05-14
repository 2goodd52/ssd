/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller.software;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controller.AbstractController;
import org.model.Entity;
import org.model.Game;
import org.model.Hardware;
import org.model.builder.BuilderFactory;
import org.model.builder.SoftwareBuilder;
import org.model.constants.ChartGroup;
import org.model.constants.PEGIGroup;
import org.model.constants.PlayerGroup;
import org.sql.Database;

/**
 *
 * @author Dean
 */
public class EditGameController extends AbstractController implements Initializable {
    
    @FXML private TextField nameInput;
    @FXML private ComboBox<ChartGroup> categoryInput;
    @FXML private ComboBox<Hardware> platformInput;
    @FXML private ComboBox<PlayerGroup> playersInput;
    @FXML private ComboBox<PEGIGroup> ageInput;

    private Game editingGame;
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        nameInput.setText("");
        for (final ChartGroup category : ChartGroup.values())
            categoryInput.getItems().add(category);
        for (final Hardware hardware : Database.getSingleton().getHardware())
            platformInput.getItems().add(hardware);
        for (final PlayerGroup players : PlayerGroup.values())
            playersInput.getItems().add(players);
        for (final PEGIGroup rating : PEGIGroup.values())
            ageInput.getItems().add(rating);
        categoryInput.getSelectionModel().select(0);
        platformInput.getSelectionModel().select(0);
        playersInput.getSelectionModel().select(0);
        ageInput.getSelectionModel().select(0);
    }
    
    /**
     * Sets the View's fields to the specified entity's details
     * @param entity The entity passed from the previous page
     */
    @Override
    public void set(final Entity entity)
    {
        if(!(entity instanceof Game))
            return;
        Game game = (Game) entity;
        nameInput.setText(game.getName());
        categoryInput.getSelectionModel().select(game.getGroup());
        platformInput.getSelectionModel().select(game.getHardware());
        playersInput.getSelectionModel().select(game.getPlayerGroup());
        ageInput.getSelectionModel().select(game.getPegiGroup());
        editingGame = game;
    }
    
    /**
     * Attempts to save the Game and add it to the Database.
     */
    @FXML
    private void saveGame()
    {
        if(!validateFields(nameInput, categoryInput, platformInput, playersInput, ageInput))
        {
            showError("Please fill in all fields before saving!");
            return;
        }
        final SoftwareBuilder builder = BuilderFactory.createSoftwareBuilder();
        builder.id(editingGame.getId());
        builder.name(nameInput.getText());
        builder.chart(categoryInput.getSelectionModel().getSelectedItem());
        builder.hardware(platformInput.getSelectionModel().getSelectedItem());
        builder.players(playersInput.getSelectionModel().getSelectedItem());
        builder.age(ageInput.getSelectionModel().getSelectedItem());
        Database.getSingleton().add(builder.build());
        showPage("software.fxml");
    }
    
    /**
     * Returns to the previous page.
     */
    @FXML
    private void cancel()
    {
        showPage("software.fxml");
    }
    
    
    
}
