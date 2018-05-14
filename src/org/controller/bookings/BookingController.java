/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controller.bookings;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.controller.AbstractController;
import org.model.Booking;
import org.sql.Database;

/**
 *
 * @author Dean
 */
public class BookingController extends AbstractController implements Initializable {
    
    @FXML private TableView<Booking> bookingTable;
    
    @FXML private TableColumn<Booking, Integer> idColumn;
    @FXML private TableColumn<Booking, String> userColumn;
    @FXML private TableColumn<Booking, String> platformColumn;
    @FXML private TableColumn<Booking, String> softwareColumn;
    @FXML private TableColumn<Booking, String> dateColumn;
    @FXML private TableColumn<Booking, String> durationColumn;
    @FXML private TableColumn<Booking, Double> priceColumn;
    
    private final ObservableList<Booking> bookingList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(final URL location, final ResourceBundle resources)
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Booking, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Booking, String> p)
            {
                return new ReadOnlyStringWrapper(p.getValue().getUser().getName());
            }
        });
        platformColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Booking, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Booking, String> p)
            {
                return new ReadOnlyStringWrapper(p.getValue().getPlatform().getName());
            }
        });
        softwareColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Booking, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Booking, String> p)
            {
                return new ReadOnlyStringWrapper(p.getValue().getGame().getName());
            }
        });
        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Booking, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Booking, String> p)
            {
                return new ReadOnlyStringWrapper(p.getValue().getDate().format(DateTimeFormatter.ofPattern("E, dd MMM yyyy : HH:mm")));
            }
        });
        durationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Booking, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Booking, String> p)
            {
                return new ReadOnlyStringWrapper(p.getValue().getDuration() + " hours");
            }
        });
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        for (final Booking b : Database.getSingleton().getBookings())
            bookingList.add(b);
        
        bookingTable.setItems(bookingList);
        
    }
    
    /**
     * Sets the current page to the add booking page.
     */
    @FXML
    private void addBooking()
    {
        showPage("bookings/add.fxml");
        
    }
    
    /**
     * Tries to set the current page to the edit booking page and passes the selected booking.
     */
    @FXML
    private void editBooking()
    {
        final Booking booking = bookingTable.getSelectionModel().getSelectedItem();
        if(booking == null)
        {
            showError("Please select the booking you want to edit.");
            return;
        }
        showPage("bookings/edit.fxml", booking);
    }
    
    /**
     * Prompts a confirmation to delete the currently selected Booking
     */
    @FXML
    private void deleteBooking()
    {
        final Booking booking = bookingTable.getSelectionModel().getSelectedItem();
        if(booking == null)
        {
            showError("Please select the booking you want to delete.");
            return;
        }
        if(delete())
        {
            Database.getSingleton().delete(booking);
            showPage("bookings.fxml");
        }
    }
    
}
