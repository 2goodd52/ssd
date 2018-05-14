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
public abstract class Entity {
    
    /**
     * The id associated with this entity
     */
    protected final int id;
    
    /**
     * The id associated with this entity
     * @param id associated with this entity
     */
    public Entity(final int id)
    {
        this.id = id;
    }
    
    /**
     * Returns the id of this entity
     * @return the id of this entity
     */
    public int getId()
    {
        return id;
    }
    
}
