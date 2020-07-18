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
public class CustomForbiddenException extends RuntimeException{

    public CustomForbiddenException(String message) {
        super(message);
    }

    public CustomForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomForbiddenException(Throwable cause) {
        super(cause);
    }
    
    
    
}
