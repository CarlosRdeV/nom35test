/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Entity;

import java.io.Serializable;

/**
 *
 * @author CGCSTF08
 */
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;
    private String email;
    private String name;
    private Boolean status;

    // need default constructor for JSON Parsing
    public JwtRequest() {

    }

    public JwtRequest(String username, String password, Boolean status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public JwtRequest(String username, String password, String email, String name, Boolean status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.status = status;
    }
    
    

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

}
