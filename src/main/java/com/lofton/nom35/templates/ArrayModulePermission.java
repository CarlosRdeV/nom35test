/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.templates;

import java.util.ArrayList;

/**
 *
 * @author CGCSTF08
 */
public class ArrayModulePermission {

    private Integer roleId;

    private ArrayList<Integer> modules;

    private ArrayList<String> permisos;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public ArrayList<Integer> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Integer> modules) {
        this.modules = modules;
    }

    public ArrayList<String> getPermisos() {
        return permisos;
    }

    public void setPermisos(ArrayList<String> permisos) {
        this.permisos = permisos;
    }

    public ArrayModulePermission() {
    }

    public ArrayModulePermission(Integer roleId, ArrayList<Integer> modules, ArrayList<String> permisos) {
        this.roleId = roleId;
        this.modules = modules;
        this.permisos = permisos;
    }
 
    @Override
    public String toString() {
        return "ArrayModulePermission{" + "roleId=" + roleId + ", modules=" + modules + ", permisos=" + permisos + '}';
    }

}
