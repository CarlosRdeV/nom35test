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
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    private Role role;

    private String username;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public JwtResponse(String jwttoken, String username) {
        this.jwttoken = jwttoken;
        this.username = username;
    }

    public JwtResponse(String jwttoken, Role role, String username) {
        this.jwttoken = jwttoken;
        this.role = role;
        this.username = username;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
