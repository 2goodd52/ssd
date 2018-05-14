/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model.builder;

import java.time.LocalDateTime;
import org.model.Booking;
import org.model.Builder;
import org.model.Game;
import org.model.Platform;
import org.model.User;

/**
 *
 * @author Dean
 */
public class BookingBuilder implements Builder {
    
    private int id;
    private User user;
    private Platform platform;
    private Game game;
    private LocalDateTime time;
    private int duration;
    
    public BookingBuilder id(final int id)
    {
        this.id = id;
        return this;
    }
    
    public BookingBuilder user(final User user)
    {
        this.user = user;
        return this;
    }
    
    public BookingBuilder platform(final Platform platform)
    {
        this.platform = platform;
        return this;
    }
    
    public BookingBuilder game(final Game game)
    {
        this.game = game;
        return this;
    }
    
    public BookingBuilder time(final LocalDateTime time)
    {
        this.time = time;
        return this;
    }

    public BookingBuilder duration(final int hours)
    {
        this.duration = hours;
        return this;
    }
    
    @Override
    public Booking build()
    {
        return new Booking(id, user, platform, game, time, duration);
    }
    
}
