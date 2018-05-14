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
public enum PEGIGroup {
    
    PEGI_3(3),
    PEGI_7(7),
    PEGI_12(12),
    PEGI_16(16),
    PEGI_18(18);
    
    private final int age;
    
    private PEGIGroup(final int age)
    {
        this.age = age;
    }
    
    /**
     * The minimum age of the PEGI group
     * @return minimum age of the PEGI group
     */
    public int getAge()
    {
        return age;
    }
    
    /**
     * Gets the index of specific enum
     * @return the index of the enum
     */
    public int getId()
    {
        for (int index = 0; index < PEGIGroup.values().length; index++)
        {
            if(PEGIGroup.values()[index] == this)
                return index;
        }
        return -1;
    }
    
    @Override
    public String toString()
    {
        return name().replace("_", " ");
    }
    
}
