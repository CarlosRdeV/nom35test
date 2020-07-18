/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.templates;

/**
 *
 * @author CGCSTF08
 */
public class UserPassword {

    private String username;

   // private String oldPassword;

    private String newPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//
//    public String getOldPassword() {
//        return oldPassword;
//    }
//
//    public void setOldPassword(String oldPassword) {
//        this.oldPassword = oldPassword;
//    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public UserPassword() {
    }

//    public UserPassword(String username, String oldPassword, String newPassword) {
//        this.username = username;
//        this.oldPassword = oldPassword;
//        this.newPassword = newPassword;
//    }

    public UserPassword(String username, String newPassword) {
        this.username = username;
        this.newPassword = newPassword;
    }
    
    

}
