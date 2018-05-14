/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

import java.time.LocalDate;
import org.model.constants.MembershipGroup;

/**
 *
 * @author Dean
 */
public class User extends Entity {
    
    private final String name;
    private final String address;
    private final String telephone;
    private final LocalDate dob;
    private final MembershipGroup membership;
    
    /**
     * Creates a new User object with the associated parameters
     * @param id
     * @param name
     * @param address
     * @param telephone
     * @param dob
     * @param membership
     */
    public User(final int id, final String name, final String address, final String telephone, final LocalDate dob, final MembershipGroup membership)
    {
        super(id);
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.dob = dob;
        this.membership = membership;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public String getTelephone()
    {
        return telephone;
    }
 
    public LocalDate getDob()
    {
        return dob;
    }
    
    public MembershipGroup getMembership()
    {
        return membership;
    }
    
    @Override
    public String toString()
    {
        return getId() + ": " + getName();
    }
    
}
