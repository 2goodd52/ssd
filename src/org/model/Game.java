/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

import org.model.constants.ChartGroup;
import org.model.constants.PEGIGroup;
import org.model.constants.PlayerGroup;

/**
 *
 * @author Dean
 */
public class Game extends Entity {
    
    private final String name;
    private final Hardware hardware;
    private final ChartGroup chartGroup;
    private final PlayerGroup playerGroup;
    private final PEGIGroup pegiGroup;
    
    /**
     * Creates a new Game object with the specified parameters
     * @param id
     * @param name
     * @param hardware
     * @param chartGroup
     * @param playerGroup
     * @param pegiGroup
     */
    public Game(final int id, final String name, final Hardware hardware, final ChartGroup chartGroup, final PlayerGroup playerGroup, final PEGIGroup pegiGroup)
    {
        super(id);
        this.name = name;
        this.hardware = hardware;
        this.chartGroup = chartGroup;
        this.playerGroup = playerGroup;
        this.pegiGroup = pegiGroup;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Hardware getHardware()
    {
        return hardware;
    }
    
    public ChartGroup getGroup()
    {
        return chartGroup;
    }
    
    public PlayerGroup getPlayerGroup()
    {
        return playerGroup;
    }
    
    public PEGIGroup getPegiGroup()
    {
        return pegiGroup;
    }
    
    @Override
    public String toString()
    {
        return getHardware().getName() + ": " + getName();
    }
    
}
