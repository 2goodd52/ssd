/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model.constants;

/**
 *
 * @author Dean
 */
public enum PlayerGroup {
    
    SINGLE,
    MULTIPLAYER;
    
    @Override
    public String toString()
    {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }
    
    /**
     * Gets the index of specific enum
     * @return the index of the enum
     */
    public int getId()
    {
        for (int index = 0; index < PlayerGroup.values().length; index++)
        {
            if(PlayerGroup.values()[index] == this)
                return index;
        }
        return -1;
    }
    
}
