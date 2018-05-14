/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model.builder;

import java.time.LocalDate;
import org.model.Builder;
import org.model.User;
import org.model.constants.MembershipGroup;

/**
 *
 * @author Dean
 */
public class UserBuilder implements Builder {
    
    private int id;
    private String name;
    private String address;
    private String telephone;
    private LocalDate dob;
    private MembershipGroup membership;
        
    public UserBuilder id(final int id)
    {
        this.id = id;
        return this;
    }
    
    public UserBuilder name(final String name)
    {
        this.name = name;
        return this;
    }
    
    public UserBuilder address(final String address)
    {
        this.address = address;
        return this;
    }
    
    public UserBuilder telephone(final String telephone)
    {
        this.telephone = telephone;
        return this;
    }

    public UserBuilder dob(final LocalDate dob)
    {
        this.dob = dob;
        return this;
    }

    public UserBuilder membership(final MembershipGroup membership)
    {
        this.membership = membership;
        return this;
    }
    
    @Override
    public User build()
    {
        return new User(id, name, address, telephone, dob, membership);
    }
    
}
