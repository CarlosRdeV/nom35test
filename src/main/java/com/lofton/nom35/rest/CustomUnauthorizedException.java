/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.rest;

/**
 *
 * @author CGCSTF08
 */
public class CustomUnauthorizedException extends RuntimeException{

    public CustomUnauthorizedException(String message) {
        super(message);
    }

    public CustomUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomUnauthorizedException(Throwable cause) {
        super(cause);
    }
    
    
    
}
