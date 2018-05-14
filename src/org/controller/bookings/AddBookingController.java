/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller.bookings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controller.AbstractController;
import org.model.Booking;
import org.model.Game;
import org.model.Platform;
import org.model.User;
import org.model.builder.BookingBuilder;
import org.model.builder.BuilderFactory;
import org.sql.Database;
import org.view.component.TimeSpinner;

/**
 *
 * @author Dean
 */
public class AddBookingController extends AbstractController implements Initializable {
  
    @FXML private ComboBox<User> userInput;
    @FXML private ComboBox<Platform> platformInput;
    @FXML private ComboBox<Game> gameInput;
    @FXML private DatePicker dateInput;
    @FXML private TimeSpinner timeInput;
    @FXML private TextField durationInput; 
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        for (final User user : Database.getSingleton().getUsers())
            userInput.getItems().add(user);
        for (final Platform platform : Database.getSingleton().getPlatforms())
            platformInput.getItems().add(platform);
        for (final Game game : Database.getSingleton().getGames())
            gameInput.getItems().add(game);
    }
    
    /**
     * Attempts to save the Booking and add it to the Database.
     */
    @FXML
    private void saveBooking()
    {
        if(!validateFields(userInput, platformInput, gameInput, dateInput, durationInput))
        {
            showError("Please fill in all required fields.");
            return;
        }
        final BookingBuilder builder = BuilderFactory.createBookingBuilder();
        builder.id(Database.getSingleton().getNextId(Booking.class));
        builder.user(userInput.getSelectionModel().getSelectedItem());
        builder.platform(platformInput.getSelectionModel().getSelectedItem());
        builder.game(gameInput.getSelectionModel().getSelectedItem());
        builder.time(dateInput.getValue().atTime(timeInput.getValue().getHour(), timeInput.getValue().getMinute()));
        builder.duration(Integer.parseInt(durationInput.getText()));
        Database.getSingleton().add(builder.build());
        showPage("bookings.fxml");
    }
    
    /**
     * Returns to the previous page.
     */
    @FXML
    private void cancel()
    {
        showPage("bookings.fxml");
    }
    
}
