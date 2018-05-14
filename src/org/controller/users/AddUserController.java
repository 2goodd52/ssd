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
import org.model.User;
import org.model.builder.BuilderFactory;
import org.model.builder.UserBuilder;
import org.model.constants.MembershipGroup;
import org.sql.Database;

/**
 *
 * @author Dean
 */
public class AddUserController extends AbstractController implements Initializable {
    
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
        UserBuilder builder = BuilderFactory.createUserBuilder().id(Database.getSingleton().getNextId(User.class));
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
