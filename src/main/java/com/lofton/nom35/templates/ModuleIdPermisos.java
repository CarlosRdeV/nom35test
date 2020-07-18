/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.templates;

import java.io.Serializable;

/**
 *
 * @author CGCSTF08
 */
public class ModuleIdPermisos implements Serializable{
    
    private Integer moduleId;
    
    private String permisos;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public ModuleIdPermisos() {
    }

    public ModuleIdPermisos(Integer moduleId, String permisos) {
        this.moduleId = moduleId;
        this.permisos = permisos;
    }
    
    
    
}
