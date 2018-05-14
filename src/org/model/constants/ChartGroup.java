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
public enum ChartGroup {
    
    ACTION,
    CLASSIC,
    FAMILY,
    FIGHTING,
    HORROR,
    RACING,
    RPG,
    SHOOTER,
    SIMULATION,
    SPORT,
    STRATEGY;
    
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
        for (int index = 0; index < ChartGroup.values().length; index++)
        {
            if(ChartGroup.values()[index] == this)
                return index;
        }
        return -1;
    }
    
}
