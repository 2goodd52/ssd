/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller.users;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.controller.AbstractController;
import org.model.User;
import org.model.constants.MembershipGroup;
import org.sql.Database;

/**
 *
 * @author Dean
 */
public class UserController extends AbstractController implements Initializable {
    
    @FXML
    private TableView<User> userTable;
    
    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> addressColumn;
    @FXML
    private TableColumn<User, String> telephoneColumn;
    @FXML
    private TableColumn<User, String> dobColumn;
    @FXML
    private TableColumn<User, MembershipGroup> typeColumn;
    
    @FXML
    private TextField searchInput;
    @FXML
    private ComboBox searchCriteria;
   
    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<User> displayList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(final URL location, final ResourceBundle resources)
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        dobColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> p)
            {
                return new ReadOnlyStringWrapper(p.getValue().getDob().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        });
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("membership"));
        
        for (final User user : Database.getSingleton().getUsers())
        {
            userList.add(user);
            displayList.add(user);
        }
        
        userTable.setItems(userList);
        
        searchInput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            search();
            
        });
        
    }
    
    /**
     * Sets the current page to the add User page.
     */
    @FXML
    private void addUser()
    {
        showPage("users/add.fxml");
    }
    
    /**
     * Tries to set the current page to the edit user page and passes the selected User.
     */
    @FXML
    private void editUser()
    {
        final User user = userTable.getSelectionModel().getSelectedItem();
        if(user == null)
        {
            showError("Please select the user you want to edit.");
            return;
        }
        showPage("users/edit.fxml", user);
    }
    
    @FXML
    private void viewUser(ActionEvent e)
    {
        System.out.println("View user");
    }
    
    /**
     * Prompts a confirmation to delete the currently selected User
     */
    @FXML
    private void deleteUser()
    {
        final User user = userTable.getSelectionModel().getSelectedItem();
        if(user == null)
        {
            showError("Please select the user you want to delete.");
            return;
        }
        if(delete())
            Database.getSingleton().delete(user);
    }
    
    @FXML
    private void search()
    {
        System.out.println("Searching for: " + searchInput.getText() + " in " + searchCriteria.getSelectionModel().getSelectedItem());
    }
    
    
    
}
