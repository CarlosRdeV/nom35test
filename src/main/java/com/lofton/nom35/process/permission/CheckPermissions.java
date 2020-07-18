/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.process.permission;

import com.lofton.nom35.Entity.ModulePermiso;
import com.lofton.nom35.Entity.User;
import com.lofton.nom35.Repository.UserRepository;
import com.lofton.nom35.rest.CustomErrorResponse;
import com.lofton.nom35.rest.CustomNotFoundException;
import com.lofton.nom35.rest.CustomUnauthorizedException;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author CGCSTF08
 */
@Service
public class CheckPermissions {

    private final UserRepository userRepo;

    @Autowired
    public CheckPermissions(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public static void checkPermissions(List<ModulePermiso> mp, User theUser, String nameModule, Integer indexPermiso) {

        Boolean permiso = Boolean.FALSE;
        for (ModulePermiso modulePermiso : mp) {

            if (nameModule.equals(modulePermiso.getModule().getName()) && "1".equals(String.valueOf(modulePermiso.getPermisos().charAt(indexPermiso)))) {
                permiso = Boolean.TRUE;
            }
        }

        if (!permiso) {
            // throw new CustomUnauthorizedException("Acceso no permitido");
        }

        /*
        @RequestHeader String User
        User theUser = userRepo.findByUsername(User);
        List<ModulePermiso> mp = theUser.getRole().getModulePermisos();
        checkPermissions(mp, theUser, "Module", 1);
        
         */
    }

    public void getUserFromToken(String jwtToken, String nameModule, Integer indexPermiso) {
        String[] split_string = jwtToken.split("\\.");

        String base64EncodedBody = split_string[1];

        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        body = body.replace("{", "");
        body = body.replace("}", "");
        String[] array = body.split(",");
        String[] array2 = array[0].split(":");
        String usuarioToken = array2[1] = array2[1].replace("\"", "");

        User user = userRepo.findByUsername(usuarioToken);

        List<ModulePermiso> mp = user.getRole().getModulePermisos();

        Boolean permiso = Boolean.FALSE;
        for (ModulePermiso modulePermiso : mp) {

            if (nameModule.equals(modulePermiso.getModule().getName())) {
                permiso = Boolean.TRUE;
            }
        }

        if (!permiso) {
            // throw new CustomUnauthorizedException("Acceso no permitido");
        }

    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(CustomUnauthorizedException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
