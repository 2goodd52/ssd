/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller.bookings;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controller.AbstractController;
import org.model.Booking;
import org.model.Entity;
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
public class EditBookingController extends AbstractController implements Initializable {
  
    @FXML private ComboBox<User> userInput;
    @FXML private ComboBox<Platform> platformInput;
    @FXML private ComboBox<Game> gameInput;
    @FXML private DatePicker dateInput;
    @FXML private TimeSpinner timeInput;
    @FXML private TextField durationInput; 
    
    private Booking editingBooking;
    
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
     * Sets the View's fields to the specified entity's details
     * @param entity The entity passed from the previous page
     */
    @Override
    public void set(final Entity entity)
    {
        if(!(entity instanceof Booking))
            return;
        Booking booking = (Booking) entity;
        userInput.getSelectionModel().select(booking.getUser());
        platformInput.getSelectionModel().select(booking.getPlatform());
        gameInput.getSelectionModel().select(booking.getGame());
        dateInput.setValue(booking.getDate().toLocalDate());
        timeInput.getValueFactory().setValue(booking.getDate().toLocalTime());
        durationInput.setText("" + booking.getDuration());
        editingBooking = booking;
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
        builder.id(editingBooking.getId());
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
