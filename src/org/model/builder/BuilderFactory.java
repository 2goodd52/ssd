/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model.builder;

import org.model.Builder;
import org.model.User;

/**
 *
 * @author Dean
 */
public class BuilderFactory {
    
    /**
     * Returns the Builder associated with the class type
     * @param buildType type of class the Builder should be associated with
     * @return
     */
    public static Builder createBuilder(final Class buildType)
    {
        if(buildType == User.class)
            return createUserBuilder();
        return null;
    }
    
    public static UserBuilder createUserBuilder()
    {
        return new UserBuilder();
    }
    
    public static BookingBuilder createBookingBuilder()
    {
        return new BookingBuilder();
    }
    
    public static HardwareBuilder createHardwareBuilder()
    {
        return new HardwareBuilder();
    }
    
    public static PlatformBuilder createPlatformBuilder()
    {
        return new PlatformBuilder();
    }
    
    public static SoftwareBuilder createSoftwareBuilder()
    {
        return new SoftwareBuilder();
    }
    
}
