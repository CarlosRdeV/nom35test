/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Role;
import com.lofton.nom35.Entity.User;
import com.lofton.nom35.Repository.RoleRepository;
import com.lofton.nom35.Repository.TokenRepository;
import com.lofton.nom35.Repository.UserRepository;
import com.lofton.nom35.service.JwtUserDetailsService;
import com.lofton.nom35.springsecurity.config.JwtTokenUtil;
import com.lofton.nom35.templates.UserPassword;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    // quick use of repository directly
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final TokenRepository tokenRepo;

    private final PasswordEncoder bcryptEncoder;

    private final RoleRepository roleRepo;

    // constructor injection
    @Autowired
    public UserController(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, TokenRepository tokenRepo, PasswordEncoder bcryptEncoder, RoleRepository roleRepo) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.tokenRepo = tokenRepo;
        this.bcryptEncoder = bcryptEncoder;
        this.roleRepo = roleRepo;
    }

    // add mapping for GET /users - return list of all users
    @GetMapping("/users")
    public List<User> getFindAllUsers(@RequestHeader String Authorization) {
        List<User> all = userRepository.findUsers();
        //remover el primero
        all.remove(0);
        return all;
    }

    @GetMapping("/users/employees")
    public List<User> getFindAllEmployeeUsers(@RequestHeader String Authorization) {
        return userRepository.findEmployees();
    }

    // add mapping for GET /users/{userId} - return user if exists
    @GetMapping("/users/{userId}")
    public User getUser(@RequestHeader String Authorization, @PathVariable int userId) {

        if (userId == 1) {
            return new User();
        }
        Optional<User> result = userRepository.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }

        return theUser;

    }

    // // add mapping for POST /users - add new user 
    @PutMapping("/users")
    public User updateUser(@RequestHeader String Authorization, @RequestBody UserPassword theUser) throws Exception {

        //solicitar el usuario
        User tempUser = userRepository.findByUsername(theUser.getUsername());

        if (tempUser.getId() == 1) {
            return new User();
        }

        //solicitar password anterior para modificar contrasena
        java.util.regex.Pattern pat = java.util.regex.Pattern.compile("^[a-zA-Z0-9!@#$%^&*()-=,.:\\{}<>\\\"']*$");
        Matcher mat = pat.matcher(theUser.getNewPassword());

        if (!mat.matches()) {
            throw new RuntimeException("La contrasena contiene caracteres invalidos");
        }

        if (theUser.getNewPassword().length() < 4 || theUser.getNewPassword().length() > 16) {
            throw new RuntimeException("La contrasena debe estar entre 4 y 16 caracteres");
        }

        tempUser.setPassword(bcryptEncoder.encode(theUser.getNewPassword()));
        userRepository.save(tempUser);

        return tempUser;

    }

    @PutMapping("/users/roles/{userId}/{roleId}")
    public User addRole(
            @RequestHeader String Authorization,
            @PathVariable int userId,
            @PathVariable int roleId) {

        //traer el usuario
        Optional<User> result = userRepository.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
            if (theUser.getId() == 1) {
                return new User();
            }
        } else {
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }

        //traer el role
        Optional<Role> resultRole = roleRepo.findById(roleId);

        Role theRole = null;

        if (resultRole.isPresent()) {
            theRole = resultRole.get();
        } else {
            throw new CustomNotFoundException("Did not find role id - " + roleId);
        }

        //asignar el role al usuario
        theUser.setRole(theRole);

        //guardar el usuario
        userRepository.save(theUser);

        Optional<User> result2 = userRepository.findById(userId);

        User theUser2 = null;

        if (result2.isPresent()) {
            theUser2 = result2.get();
        } else {
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }

        return theUser2;
    }

    // add mapping for DELETE /users/{userId} - disable existing user
    @PutMapping("/users/{userId}")
    public User updateEnableUser(@RequestHeader String Authorization, @PathVariable int userId) {

        Optional<User> tempUser = userRepository.findById(userId);

        User theUser = null;

        if (tempUser.isPresent()) {
            theUser = tempUser.get();
        } else {
            throw new CustomNotFoundException("User id not found - " + userId);
        }

        theUser.setStatus(Boolean.TRUE);

        userRepository.save(theUser);

        return theUser;
    }

    // add mapping for DELETE /users/{userId} - disable existing user
    @DeleteMapping("/users/{userId}")

    public User disableUser(@RequestHeader String Authorization, @PathVariable int userId) {

        Optional<User> tempUser = userRepository.findById(userId);

        User theUser = null;

        if (tempUser.isPresent()) {
            theUser = tempUser.get();
            if (theUser.getId() == 1) {
                return new User();
            }
        } else {
            throw new CustomNotFoundException("User id not found - " + userId);
        }

        theUser.setStatus(false);

        userRepository.save(theUser);

        return theUser;
    }

    //add exception handler 
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(CustomNotFoundException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // add exception handler to catch all
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(Exception exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
