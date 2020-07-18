/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author CGCSTF08
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    @NotNull
    @Column(name = "username", unique = true)
    @NotEmpty(message = "Not empty") // vacios ""
    @Pattern(
            regexp = "^[a-zA-Z0-9-\\u00f1\\u00d1]*$",
            message = "Only chars accepted") // no espacios "a a a"
    @Length(min = 4, max = 12)
    private String username;

    @NotNull
    @Column(name = "password")
    @NotEmpty(message = "Not empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min = 4)
    private String password;

    @Email(message = "Ingresa un email valido")
    @Column(name = "email")
    private String email;

    @Size(min = 4)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(String username, String password, Boolean status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", status=" + status + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
