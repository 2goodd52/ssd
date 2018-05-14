/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sql;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.HashMap;
import org.model.Booking;
import org.model.Entity;
import org.model.Game;
import org.model.Hardware;
import org.model.Platform;
import org.model.User;
import org.model.builder.BookingBuilder;
import org.model.builder.BuilderFactory;
import org.model.builder.HardwareBuilder;
import org.model.builder.PlatformBuilder;
import org.model.builder.SoftwareBuilder;
import org.model.builder.UserBuilder;
import org.model.constants.ChartGroup;
import org.model.constants.MembershipGroup;
import org.model.constants.PEGIGroup;
import org.model.constants.PlayerGroup;

/**
 *
 * @author Dean
 */
public class Database {
    
    private final HashMap<Integer, User> users;
    private final HashMap<Integer, Platform> platforms;
    private final HashMap<Integer, Hardware> hardware;
    private final HashMap<Integer, Game> games;
    private final HashMap<Integer, Booking> bookings;
    
    private static Database singleton = null;
    
    /**
     * Gets, or creates if null, the Singleton instance of the database
     * @return the singleton instance of the database
     */
    public static Database getSingleton()
    {
        if(singleton == null)
            singleton = new Database();
        return singleton;
    }
    
    /**
     * Database constructor
     * Initialises and clears all HashMaps and then loads the database file
     */
    public Database()
    {
        
        users = new HashMap<>();
        hardware = new HashMap<>();
        platforms = new HashMap<>();
        games = new HashMap<>();
        bookings = new HashMap<>();
        
        try {
            users.clear();
            hardware.clear();
            platforms.clear();
            games.clear();
            bookings.clear();
            load();
        } catch(final Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * An array of Users in the database
     * @return array of Users in the database
     */
    public User[] getUsers()
    {
        return users.values().toArray(new User[users.size()]);
    }
    
    /**
     * An array of Hardware in the database
     * @return array of Hardware in the database
     */
    public Hardware[] getHardware()
    {
        return hardware.values().toArray(new Hardware[hardware.size()]);
    }
    
    /**
     * An array of Platforms in the database
     * @return array of Platforms in the database
     */
    public Platform[] getPlatforms()
    {
        return platforms.values().toArray(new Platform[platforms.size()]);
    }
    
    /**
     * An array of Games in the database
     * @return array of Games in the database
     */
    public Game[] getGames()
    {
        return games.values().toArray(new Game[games.size()]);
    }
    
    /**
     * An array of Bookings in the database
     * @return array of Bookings in the database
     */
    public Booking[] getBookings()
    {
        return bookings.values().toArray(new Booking[bookings.size()]);
    }
    
    /**
     * Adds an entity to the identified database table
     * @param entity The Entity to add to the database
     */
    public void add(final Entity entity)
    {
        if(entity instanceof User)
            users.put(entity.getId(), (User) entity);
        else
        if(entity instanceof Hardware)
            hardware.put(entity.getId(), (Hardware) entity);
        else
        if(entity instanceof Platform)
            platforms.put(entity.getId(), (Platform) entity);
        else
        if(entity instanceof Game)
            games.put(entity.getId(), (Game) entity);
        else
        if(entity instanceof Booking)
            bookings.put(entity.getId(), (Booking) entity);
    }    
    
    /**
     * Replaces, or updates, an Entity in the database
     * @param entity The Entity to update in the database
     */
    public void update(final Entity entity)
    {
        delete(entity);
        add(entity);
    }
    
    /**
     * Deletes an Entity from the database
     * @param entity The Entity to delete from the database
     */
    public void delete(final Entity entity)
    {
        if(entity instanceof User)
            users.remove(entity.getId());
        else
        if(entity instanceof Hardware)
            hardware.remove(entity.getId());
        else
        if(entity instanceof Platform)
            platforms.remove(entity.getId());
        else
        if(entity instanceof Game)
            games.remove(entity.getId());
        else
        if(entity instanceof Booking)
            bookings.remove(entity.getId());
    }
    
    /**
     * Gets the next unique id for an Entity in the database
     * @param type The class type of the Entity
     * @return The next available unique id for the entity
     */
    public int getNextId(final Class type)
    {
        HashMap map = null;
        if(type == User.class)
        {
            map = users;
        } else
        if(type == Hardware.class)
        {
            map = hardware;
        } else
        if(type == Platform.class)
        {
            map = platforms;
        } else
        if(type == Game.class)
        {
            map = games;
        } else
        if(type == Booking.class)
        {
            map = bookings;
        }
        if(map != null)
        {
            int index = 0;
            for (final Integer key : (Integer[]) map.keySet().toArray(new Integer[map.keySet().size()]))
            {
                if(key >= index)
                    index = key + 1;
            }
            return index;
        }
        return -1;
    }
    
    /**
     * Saves the database tables to a local file
     */
    public void save()
    {
        try {
            DataOutputStream output = new DataOutputStream(new FileOutputStream("db.dat"));
            output.writeInt(users.size());
            for (final User user : users.values())
            {
                output.writeInt(user.getId());
                output.writeUTF(user.getName());
                output.writeUTF(user.getAddress());
                output.writeUTF(user.getTelephone());
                output.writeLong(user.getDob().atStartOfDay().atOffset(ZoneOffset.UTC).toInstant().getEpochSecond());
                output.writeInt(user.getMembership().getId());
            }
            output.writeInt(hardware.size());
            for (final Hardware hard : hardware.values())
            {
                output.writeInt(hard.getId());
                output.writeUTF(hard.getName());
                output.writeUTF(hard.getDescription());
            }
            output.writeInt(platforms.size());
            for (final Platform platform : platforms.values())
            {
                output.writeInt(platform.getId());
                output.writeUTF(platform.getName());
                output.writeInt(platform.getConsoleType().getId());
                output.writeInt(platform.getComponents().length);
                for (final Hardware h : platform.getComponents())
                    output.writeInt(h.getId());
            }
            output.writeInt(games.size());
            for (final Game game : games.values())
            {
                output.writeInt(game.getId());
                output.writeUTF(game.getName());
                output.writeInt(game.getHardware().getId());
                output.writeInt(game.getGroup().getId());
                output.writeInt(game.getPlayerGroup().getId());
                output.writeInt(game.getPegiGroup().getId());
            }
            output.writeInt(bookings.size());
            for (final Booking booking : bookings.values())
            {
                output.writeInt(booking.getId());
                output.writeInt(booking.getUser().getId());
                output.writeInt(booking.getPlatform().getId());
                output.writeInt(booking.getGame().getId());
                output.writeLong(booking.getDate().atOffset(ZoneOffset.UTC).toEpochSecond());
                output.writeInt(booking.getDuration());
            }
            output.close();
            System.out.println("Saved");
        } catch (Exception ex) {
            System.out.println("Unable to create database file.");
        }
    }
    
    /**
     * Loads the database entries from a local file
     */
    public void load()
    {
        try {
            DataInputStream input = new DataInputStream(new FileInputStream("db.dat"));
            final int userCount = input.readInt();
            System.out.println("Users: " + userCount);
            for (int index = 0; index < userCount; index++)
            {
                final UserBuilder builder = BuilderFactory.createUserBuilder();
                builder.id(input.readInt());
                builder.name(input.readUTF());
                builder.address(input.readUTF());
                builder.telephone(input.readUTF());
                builder.dob(Instant.ofEpochSecond(input.readLong()).atZone(ZoneId.systemDefault()).toLocalDate());
                builder.membership(MembershipGroup.values()[input.readInt()]);
                add(builder.build());
            }
            final int hardwareCount = input.readInt();
            System.out.println("Hardware: " + hardwareCount);
            for (int index = 0; index < hardwareCount; index++)
            {
                final HardwareBuilder builder = BuilderFactory.createHardwareBuilder();
                builder.id(input.readInt());
                builder.name(input.readUTF());
                builder.description(input.readUTF());
                add(builder.build());
            }
            final int platformCount = input.readInt();
            System.out.println("Platforms: " + platformCount);
            for (int index = 0; index < platformCount; index++)
            {
                final PlatformBuilder builder = BuilderFactory.createPlatformBuilder();
                builder.id(input.readInt());
                builder.name(input.readUTF());
                builder.consoleType(hardware.get(input.readInt()));
                int count = input.readInt();
                Hardware[] components = new Hardware[count];
                for (int sub = 0; sub < count; sub++)
                    components[sub] = hardware.get(input.readInt());
                builder.components(components);
                add(builder.build());
            }
            final int gameCount = input.readInt();
            System.out.println("Games: " + gameCount);
            for (int index = 0; index < gameCount; index++)
            {
                final SoftwareBuilder builder = BuilderFactory.createSoftwareBuilder();
                builder.id(input.readInt());
                builder.name(input.readUTF());
                builder.hardware(hardware.get(input.readInt()));
                builder.chart(ChartGroup.values()[input.readInt()]);
                builder.players(PlayerGroup.values()[input.readInt()]);
                builder.age(PEGIGroup.values()[input.readInt()]);
                add(builder.build());
            }  
            final int bookingCount = input.readInt();
            System.out.println("Bookings: " + bookingCount);
            for (int index = 0; index < bookingCount; index++)
            {
                final BookingBuilder builder = BuilderFactory.createBookingBuilder();
                builder.id(input.readInt());
                builder.user(users.get(input.readInt()));
                builder.platform(platforms.get(input.readInt()));
                builder.game(games.get(input.readInt()));
                builder.time(Instant.ofEpochSecond(input.readLong()).atZone(ZoneId.systemDefault()).toLocalDateTime());
                builder.duration(input.readInt());
                add(builder.build());
            }   
            input.close();
            System.out.println("Loaded.");
        } catch (Exception ex) {
            System.out.println("Database file does not exist.");
        }
    }
    
}
