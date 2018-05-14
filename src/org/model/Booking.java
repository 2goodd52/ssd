/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

import java.time.LocalDateTime;

/**
 *
 * @author Dean
 */
public class Booking extends Entity {
    
    private final User user;
    private final Platform platform;
    private final Game game;
    private final LocalDateTime date;
    private final int duration;
    
    /**
     * Creates a new Booking with the specified parameters
     * @param id The Booking id
     * @param user The user the booking is for
     * @param platform The platform the booking is for
     * @param game The game software the booking is for
     * @param date The date of the booking
     * @param duration The duration of the booking (in hours)
     */
    public Booking(final int id, final User user, final Platform platform, final Game game, final LocalDateTime date, final int duration)
    {
        super(id);
        this.user = user;
        this.platform = platform;
        this.game = game;
        this.date = date;
        this.duration = duration;
    }

    /**
     * Returns the User associated with this Booking
     * @return the User associated with this Booking
     */
    public User getUser()
    {
        return user;
    }
 
    /**
     * Returns the Platform associated with this Booking
     * @return the Platform associated with this Booking
     */
    public Platform getPlatform()
    {
        return platform;
    }

    /**
     * Returns the Game associated with this Booking
     * @return the Game associated with this Booking
     */
    public Game getGame()
    {
        return game;
    }

    /**
     * Returns the Date associated with this Booking
     * @return the Date associated with this Booking
     */
    public LocalDateTime getDate()
    {
        return date;
    }

    /**
     * Returns the duration of this Booking
     * @return The duration of the Booking in hours
     */
    public int getDuration()
    {
        return duration;
    }

    /**
     * Returns the price of the Booking
     * @return the price of the Booking
     */
    public double getPrice()
    {
        return duration * 1.50;
    }
    
    
    
}
