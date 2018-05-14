/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model.builder;

import java.util.LinkedList;
import java.util.List;
import org.model.Builder;
import org.model.Hardware;
import org.model.Platform;

/**
 *
 * @author Dean
 */
public class PlatformBuilder implements Builder {
    
    private int id;
    private String name;
    private Hardware consoleType;
    private List<Hardware> componentList;
    
    public PlatformBuilder id(final int id)
    {
        this.id = id;
        return this;
    }

    public PlatformBuilder name(final String name)
    {
        this.name = name;
        return this;
    }

    public PlatformBuilder consoleType(final Hardware consoleType)
    {
        this.consoleType = consoleType;
        return this;
    }
    
    public PlatformBuilder components(final Hardware... hardware)
    {
        if(hardware == null || hardware.length < 1)
            return this;
        componentList = new LinkedList<>();
        for (final Hardware h : hardware)
            componentList.add(h);
        return this;
    }

    @Override
    public Platform build()
    {
        return new Platform(id, name, consoleType, componentList.toArray(new Hardware[componentList.size()]));
    }
    
}
