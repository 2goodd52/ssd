/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller.software;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.controller.AbstractController;
import org.model.Game;
import org.sql.Database;

/**
 *
 * @author Dean
 */
public class SoftwareController extends AbstractController implements Initializable {
    
    @FXML private TableView<Game> gameTable;
    
    @FXML private TableColumn<Game, Integer> idColumn;
    @FXML private TableColumn<Game, String> nameColumn;
    @FXML private TableColumn<Game, String> categoryColumn;
    @FXML private TableColumn<Game, String> platformColumn;
    @FXML private TableColumn<Game, String> playersColumn;
    @FXML private TableColumn<Game, Integer> ageColumn;
    
    private final ObservableList<Game> gameList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(final URL location, final ResourceBundle resources)
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new Callback<CellDataFeatures<Game, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Game, String> p)
            {
                return new ReadOnlyStringWrapper(p.getValue().getGroup().toString());
            }
        });
        platformColumn.setCellValueFactory(new Callback<CellDataFeatures<Game, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Game, String> p)
            {
                return new ReadOnlyStringWrapper(p.getValue().getHardware().getName());
            }
        });
        playersColumn.setCellValueFactory(new Callback<CellDataFeatures<Game, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Game, String> p)
            {
                return new ReadOnlyStringWrapper(p.getValue().getHardware().getName());
            }
        });
        playersColumn.setCellValueFactory(new Callback<CellDataFeatures<Game, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Game, String> p)
            {
                return new ReadOnlyStringWrapper(p.getValue().getPlayerGroup().toString());
            }
        });
        ageColumn.setCellValueFactory(new Callback<CellDataFeatures<Game, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(CellDataFeatures<Game, Integer> p)
            {
                return new SimpleIntegerProperty(p.getValue().getPegiGroup().getAge()).asObject();
            }
        });
        
        for (final Game game : Database.getSingleton().getGames())
            gameList.add(game);
        
        gameTable.setItems(gameList);
        
    }
    
    /**
     * Sets the current page to the add Game page.
     */
    @FXML
    private void addGame()
    {
        showPage("software/add.fxml");
    }
    
    /**
     * Tries to set the current page to the edit game page and passes the selected Game.
     */
    @FXML
    private void editGame()
    {
        final Game game = gameTable.getSelectionModel().getSelectedItem();
        if(game == null)
        {
            showError("Please select the game you want to edit.");
            return;
        }
        showPage("software/edit.fxml", game);
    }
    
    /**
     * Prompts a confirmation to delete the currently selected Game
     */
    @FXML
    private void deleteGame()
    {
        final Game game = gameTable.getSelectionModel().getSelectedItem();
        if(game == null)
        {
            showError("Please select the game you want to delete.");
            return;
        }
        if(delete())
        {
            Database.getSingleton().delete(game);
            showPage("software.fxml");
        }
    }
    
}
