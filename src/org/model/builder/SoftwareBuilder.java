/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model.builder;

import org.model.Builder;
import org.model.Game;
import org.model.Hardware;
import org.model.constants.ChartGroup;
import org.model.constants.PEGIGroup;
import org.model.constants.PlayerGroup;

/**
 *
 * @author Dean
 */
public class SoftwareBuilder implements Builder {
    
    private int id;
    private String name;
    private Hardware hardware;
    private ChartGroup chart;
    private PlayerGroup players;
    private PEGIGroup age;
    
    public SoftwareBuilder id(final int id)
    {
        this.id = id;
        return this;
    }
    
    public SoftwareBuilder name(final String name)
    {
        this.name = name;
        return this;
    }
    
    public SoftwareBuilder hardware(final Hardware hardware)
    {
        this.hardware = hardware;
        return this;
    }
    
    public SoftwareBuilder chart(final ChartGroup group)
    {
        this.chart = group;
        return this;
    }

    public SoftwareBuilder players(final PlayerGroup group)
    {
        this.players = group;
        return this;
    }
    
    public SoftwareBuilder age(final PEGIGroup age)
    {
        this.age = age;
        return this;
    }
    
    @Override
    public Game build()
    {
        return new Game(id, name, hardware, chart, players, age);
    }
    
}
