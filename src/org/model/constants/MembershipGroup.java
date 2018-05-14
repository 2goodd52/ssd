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
public enum MembershipGroup {
    
    BRONZE,
    SILVER,
    GOLD;
    
    /**
     * Gets the index of specific enum
     * @return the index of the enum
     */
    public int getId()
    {
        for (int index = 0; index < MembershipGroup.values().length; index++)
        {
            if(MembershipGroup.values()[index] == this)
                return index;
        }
        return -1;
    }
    
}
