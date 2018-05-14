/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

import java.util.LinkedList;

/**
 *
 * @author Dean
 */
public class Platform extends Entity {
    
    private final String name;
    private final Hardware consoleType;
    private final LinkedList<Hardware> consoleComponents;
    
    /**
     * Creates a new Platform object with the specified parameters
     * @param id
     * @param name
     * @param consoleType
     * @param consoleComponents
     */
    public Platform(final int id, final String name, final Hardware consoleType, final Hardware... consoleComponents)
    {
        super(id);
        this.name = name;
        this.consoleType = consoleType;
        this.consoleComponents = new LinkedList<>();
        for (final Hardware hw : consoleComponents)
            this.consoleComponents.add(hw);
    }
    
    public String getName()
    {
        return name;
    }
    
    public Hardware getConsoleType()
    {
        return consoleType;
    }
    
    public Hardware[] getComponents()
    {
        return consoleComponents.toArray(new Hardware[consoleComponents.size()]);
    }
    
    @Override
    public String toString()
    {
        return getName();
    }
        
        
}
