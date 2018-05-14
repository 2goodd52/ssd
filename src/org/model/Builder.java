/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.model;

/**
 *
 * @author Dean
 * @param <T>
 */
public interface Builder<T> {
    
    /**
     * Abstract build method
     * @return T type of class
     */
    T build();
    
}
