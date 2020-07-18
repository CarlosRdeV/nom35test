package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Module;
import com.lofton.nom35.Entity.ModulePermiso;
import com.lofton.nom35.Entity.Role;
import com.lofton.nom35.Repository.ModulePermisoRepository;
import com.lofton.nom35.Repository.ModuleRepository;
import com.lofton.nom35.Repository.RoleRepository;
import com.lofton.nom35.templates.ArrayModulePermission;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CGCSTF08
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class RoleController {

    private final RoleRepository roleRepo;

    private final ModuleRepository moduleRepo;

    private final ModulePermisoRepository mpRepo;

    @Autowired
    public RoleController(RoleRepository roleRepo, ModuleRepository moduleRepo, ModulePermisoRepository mpRepo) {
        this.roleRepo = roleRepo;
        this.moduleRepo = moduleRepo;
        this.mpRepo = mpRepo;
    }

    @GetMapping("/roles")
    public List<Role> getFindAll(@RequestHeader String Authorization) {
        return roleRepo.findAll();
    }

    @GetMapping("/roles/{roleId}")
    public Role getRole(@RequestHeader String Authorization, @PathVariable int roleId) {
        Optional<Role> tempRole = roleRepo.findById(roleId);
        Role theRole = null;
        if (tempRole.isPresent()) {
            theRole = tempRole.get();
        } else {
            throw new CustomNotFoundException("Role id not found " + roleId);
        }
        return theRole;
    }

    @PostMapping("/roles")
    public Role addRole(@RequestHeader String Authorization, @RequestBody Role theRole) {
        theRole.setId(0);
        theRole.setStatus(Boolean.TRUE);
        theRole.setName(theRole.getName().toUpperCase());
        roleRepo.save(theRole);
        Optional<Role> GetRole = roleRepo.findByName(theRole.getName());
        Role tempRole = null;
        if (GetRole.isPresent()) {
            tempRole = GetRole.get();
        } else {
            throw new CustomNotFoundException("Role id not found " + tempRole.getId());
        }

        return tempRole;
    }

    @PutMapping("/roles")
    public Role updateRole(@RequestHeader String Authorization, @RequestBody Role theRole) {
        theRole.getName().toUpperCase();
        roleRepo.save(theRole);

        return theRole;
    }

    @DeleteMapping("/roles/{roleId}")
    public Role disableRole(@RequestHeader String Authorization, @PathVariable int roleId) {

        Optional<Role> tempRole = roleRepo.findById(roleId);
        Role theRole = null;
        if (tempRole.isPresent()) {
            theRole = tempRole.get();
            theRole.setStatus(Boolean.FALSE);

            roleRepo.save(theRole);
        } else {
            throw new CustomNotFoundException("Role id not found " + roleId);
        }

        return theRole;
    }

    // agregar modulos/permisos a roles
    @PutMapping("/roles/permissions")
    public Role addModulePermissionArray(@RequestHeader String Authorization, @RequestBody ArrayModulePermission arrayMP) {

//        get role
        Optional<Role> tempRole = roleRepo.findById(arrayMP.getRoleId());
        Role theRole = null;
        if (tempRole.isPresent()) {
            theRole = tempRole.get();
        } else {
            throw new CustomNotFoundException("Role id not found " + arrayMP.getRoleId());
        }

        for (int i = 0; i < arrayMP.getModules().size(); i++) {
            //            //        get Module
            Optional<Module> tempModule = moduleRepo.findById(arrayMP.getModules().get(i));
            System.out.println(arrayMP.getModules().get(i));
            Module theModule = null;
            if (tempModule.isPresent()) {
                theModule = tempModule.get();
            } else {
                throw new CustomNotFoundException("module id not found " + arrayMP.getModules().get(i));
            }

            //        verificar existencia de un modulo en un  rol
            ValidarPermisos(theRole, theModule, arrayMP.getPermisos().get(i));

            //crear o buscar module repo
            Optional<ModulePermiso> tempMp = mpRepo.findByModuleAndPermisos(theModule, arrayMP.getPermisos().get(i));

            ModulePermiso theModulePermiso = null;
            if (tempMp.isPresent()) {
//            si existe guardarlo
                theModulePermiso = tempMp.get();
                theRole.addModulePermiso(theModulePermiso);

            } else {
//            si no existe crearlo, guardar y asignarlo
                ModulePermiso mpelse = new ModulePermiso(theModule, arrayMP.getPermisos().get(i));
                mpRepo.save(mpelse);

                Optional<ModulePermiso> tempMp1 = mpRepo.findByModuleAndPermisos(theModule, arrayMP.getPermisos().get(i));
                ModulePermiso theModulePermiso1 = null;
                if (tempMp1.isPresent()) {
                    theModulePermiso1 = tempMp1.get();
                } else {
                    throw new CustomNotFoundException("Module - module id not found " + theModule.getId());
                }

                theRole.addModulePermiso(theModulePermiso1);

            }

//        save role
            roleRepo.save(theRole);

        }

        return theRole;

    }

    // agregar modulos/permisos a roles
    @PutMapping("/roles/permissions/update")
    public Role updateModulePermissionArray(@RequestHeader String Authorization, @RequestBody ArrayModulePermission arrayMP) {

//        get role
        Optional<Role> tempRole = roleRepo.findById(arrayMP.getRoleId());
        Role theRole = null;
        if (tempRole.isPresent()) {
            theRole = tempRole.get();
            System.out.println("ESTE ES TU ROL: " + theRole);
            System.out.println("TIENE ESTOS MODULOS: " + theRole.getModulePermisos());
        } else {
            throw new CustomNotFoundException("Role id not found " + arrayMP.getRoleId());
        }

        List<ModulePermiso> nuevos = new ArrayList<>();

        System.out.println("MODULOS DENTRO DEL ARRAY");
        for (int i = 0; i < arrayMP.getModules().size(); i++) {
            //            //        get Module
            Optional<Module> tempModule = moduleRepo.findById(arrayMP.getModules().get(i));

            System.out.println(arrayMP.getModules().get(i));
            Module theModule = null;
            if (tempModule.isPresent()) {
                theModule = tempModule.get();
                System.out.println(theModule);
            } else {
                throw new CustomNotFoundException("module id not found " + arrayMP.getModules().get(i));
            }

            //        verificar existencia de un modulo en un  rol
            ValidarPermisos(theRole, theModule, arrayMP.getPermisos().get(i));

            //crear o buscar module repo
            Optional<ModulePermiso> tempMp = mpRepo.findByModuleAndPermisos(theModule, arrayMP.getPermisos().get(i));

            ModulePermiso theModulePermiso = null;
            if (tempMp.isPresent()) {
                System.out.println("YA EXISTE");
//            si existe guardarlo
                theModulePermiso = tempMp.get();
                System.out.println("ES ESTE " + theModulePermiso);
                nuevos.add(theModulePermiso);
//theRole.addModulePermiso(theModulePermiso);

            } else {
//            si no existe crearlo, guardar y asignarlo
                ModulePermiso mpelse = new ModulePermiso(theModule, arrayMP.getPermisos().get(i));
                System.out.println("ESTE ES EL PERMISO CREADO");
                System.out.println(mpelse);
                mpRepo.save(mpelse);

                Optional<ModulePermiso> tempMp1 = mpRepo.findByModuleAndPermisos(theModule, arrayMP.getPermisos().get(i));
                ModulePermiso theModulePermiso1 = null;
                if (tempMp1.isPresent()) {
                    theModulePermiso1 = tempMp1.get();
                    System.out.println("ESTE ES EL MODULO CON LOS PERMISOS");
                    System.out.println(theModulePermiso1);
                } else {
                    throw new CustomNotFoundException("Module - module id not found " + theModule.getId());
                }

                nuevos.add(theModulePermiso1);

            }
            System.out.println("ESTOS SON LOS NUEVOS PERMISOS");
            System.out.println(nuevos);
            theRole.setModulePermisos(nuevos);
//        save role
            roleRepo.save(theRole);

        }

        return theRole;

    }

    public void ValidarPermisos(Role theRole, Module theModule, String permisos) {

        List<ModulePermiso> modulos = theRole.getModulePermisos();
        ArrayList<ModulePermiso> nueva = new ArrayList<>();

        for (ModulePermiso modulo : modulos) {
            if (modulo.getModule() != theModule) {
                nueva.add(modulo);
            }

        }

        theRole.setModulePermisos(nueva);

    }

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
