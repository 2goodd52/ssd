/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

/**
 *
 * @author Dean
 */
public class Hardware extends Entity {
    
    private final String name;
    private final String description;
    
    /**
     * Creates a new Hardware object with the specified parameters
     * @param id
     * @param name
     * @param description
     */
    public Hardware(final int id, final String name, final String description)
    {
        super(id);
        this.name = name;
        this.description = description;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    @Override
    public String toString()
    {
        return getName();
    }
    
}
