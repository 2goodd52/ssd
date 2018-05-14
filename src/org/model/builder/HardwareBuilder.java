/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model.builder;

import org.model.Builder;
import org.model.Hardware;

/**
 *
 * @author Dean
 */
public class HardwareBuilder implements Builder {
    
    private int id;
    private String name;
    private String description;
    
    public HardwareBuilder id(final int id)
    {
        this.id = id;
        return this;
    }
    
    public HardwareBuilder name(final String name)
    {
        this.name = name;
        return this;
    }
    
    public HardwareBuilder description(final String description)
    {
        this.description = description;
        return this;
    }
    
    @Override
    public Hardware build()
    {
        return new Hardware(id, name, description);
    }
    
}
