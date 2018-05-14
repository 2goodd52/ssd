/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller.users;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controller.AbstractController;
import org.model.Entity;
import org.model.User;
import org.model.builder.BuilderFactory;
import org.model.builder.UserBuilder;
import org.model.constants.MembershipGroup;
import org.sql.Database;

/**
 *
 * @author Dean
 */
public class EditUserController extends AbstractController implements Initializable {
    
    @FXML
    private TextField nameInput;
    @FXML
    private TextArea addressInput;
    @FXML
    private TextField telephoneInput;
    @FXML
    private DatePicker dobInput;
    @FXML
    private ComboBox memberInput;
    
    private User editingUser;
    
     @Override
    public void initialize(final URL location, final ResourceBundle resources)
    {
        nameInput.setText("");
        addressInput.setText("");
        telephoneInput.setText("");
        dobInput.setValue(null);
        memberInput.getSelectionModel().select(0);
    }
    
    /**
     * Sets the View's fields to the specified entity's details
     * @param entity The entity passed from the previous page
     */
    @Override
    public void set(final Entity entity)
    {
        if(!(entity instanceof User))
        {
            System.out.println("Set can only be applied to a user.");
            return;
        }
        User user = (User) entity;
        nameInput.setText(user.getName());
        addressInput.setText(user.getAddress());
        telephoneInput.setText(user.getTelephone());
        dobInput.setValue(user.getDob());
        memberInput.getSelectionModel().select(user.getMembership().getId());
        editingUser = user;
    }
    
    /**
     * Attempts to save the User and add it to the Database.
     */
    @FXML
    private void saveUser()
    {
        if(!validateFields(nameInput, addressInput, telephoneInput, dobInput))
        {
            showError("Please fill in all fields.");
            return;
        }
        UserBuilder builder = BuilderFactory.createUserBuilder().id(editingUser.getId());
        builder.name(nameInput.getText());
        builder.address(addressInput.getText());
        builder.telephone(telephoneInput.getText());
        builder.dob(dobInput.getValue());
        builder.membership(MembershipGroup.values()[memberInput.getSelectionModel().getSelectedIndex()]);
        Database.getSingleton().add(builder.build());
        showPage("users.fxml");
    }
    
    /**
     * Returns to the previous page.
     */
    @FXML
    private void cancel()
    {
        showPage("users.fxml");
    }
    
}
