/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller.platforms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import org.model.Hardware;
import org.model.Platform;
import org.sql.Database;

/**
 *
 * @author Dean
 */
public class PlatformController extends AbstractController implements Initializable {
    
    @FXML private TableView<Platform> platformTable;
    @FXML private TableView<Hardware> hardwareTable;
    
    @FXML private TableColumn<Platform, Integer> idColumnPlatform;
    @FXML private TableColumn<Platform, String> nameColumnPlatform;
    @FXML private TableColumn<Platform, String> typeColumnPlatform;
    @FXML private TableColumn<Platform, String> componentsColumnPlatform;
    
    @FXML private TableColumn<Hardware, Integer> idColumnHardware;
    @FXML private TableColumn<Hardware, String> nameColumnHardware;
    @FXML private TableColumn<Hardware, String> descriptionColumnHardware;
    
    private final ObservableList<Platform> platformList = FXCollections.observableArrayList();
    private final ObservableList<Hardware> hardwareList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(final URL location, final ResourceBundle resources)
    {
        idColumnPlatform.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumnPlatform.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumnPlatform.setCellValueFactory(new Callback<CellDataFeatures<Platform, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Platform, String> p)
            {
                return new ReadOnlyStringWrapper(p.getValue().getConsoleType().getName());
            }
        });
        componentsColumnPlatform.setCellValueFactory(new Callback<CellDataFeatures<Platform, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Platform, String> p)
            {
                final StringBuilder sb = new StringBuilder();
                for (final Hardware component : p.getValue().getComponents())
                {
                    sb.append(component.getName());
                    sb.append("\n");
                }
                return new ReadOnlyStringWrapper(sb.toString());
            }
        });
        idColumnHardware.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumnHardware.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumnHardware.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        for (final Platform p : Database.getSingleton().getPlatforms())
            platformList.add(p);
        for (final Hardware h : Database.getSingleton().getHardware())
            hardwareList.add(h);
        
        platformTable.setItems(platformList);
        hardwareTable.setItems(hardwareList);
        
    }
    
    /**
     * Sets the current page to the add platform page.
     */
    @FXML
    private void addPlatform()
    {
        showPage("platforms/add.fxml");
    }
    
    /**
     * Tries to set the current page to the edit plat page and passes the selected Platform.
     */
    @FXML
    private void editPlatform()
    {
        final Platform platform = platformTable.getSelectionModel().getSelectedItem();
        if(platform == null)
        {
            showError("Please select the platform you want to edit.");
            return;
        }
        showPage("platforms/edit.fxml", platform);
    }
    
    /**
     * Prompts a confirmation to delete the currently selected Platform
     */
    @FXML
    private void deletePlatform()
    {
        final Platform platform = platformTable.getSelectionModel().getSelectedItem();
        if(platform == null)
        {
            showError("Please select the platform you want to delete.");
            return;
        }
        if(delete())
        {
            Database.getSingleton().delete(platform);
            update();
        }
    }
    
    /**
     * Sets the current page to the add Hardware page.
     */
    @FXML
    private void addComponent()
    {
        showPage("hardware/add.fxml");
    }
    
    /**
     * Tries to set the current page to the edit hardware page and passes the selected Hardware.
     */
    @FXML
    private void editComponent()
    {
        final Hardware hardware = hardwareTable.getSelectionModel().getSelectedItem();
        if(hardware == null)
        {
            showError("Please select the hardware you want to edit.");
            return;
        }
        showPage("hardware/edit.fxml", hardware);
    }
    
    /**
     * Prompts a confirmation to delete the currently selected Hardware
     */
    @FXML
    private void deleteComponent()
    {
        final Hardware hardware = hardwareTable.getSelectionModel().getSelectedItem();
        if(hardware == null)
        {
            showError("Please select the hardware you want to delete.");
            return;
        }
        if(delete())
        {
            Database.getSingleton().delete(hardware);
            update();
        }
            
    }
        
    /**
     * Refreshes the current screen
     */
    private void update()
    {
        showPage("platforms.fxml");
    }
    
}

