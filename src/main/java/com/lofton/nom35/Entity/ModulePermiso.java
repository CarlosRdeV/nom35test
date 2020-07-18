package com.lofton.nom35.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author CGCSTF08
 */
@Entity
@Table(name = "module_permiso")
public class ModulePermiso implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @Column(name = "permisos")
    private String permisos;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToMany
    @JoinTable(name = "Role_has_ModulePermiso", joinColumns = @JoinColumn(name = "modulePermiso_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public ModulePermiso() {

    }

    public ModulePermiso(Module module, String permisos) {
        this.module = module;
        this.permisos = permisos;
    }

    @Override
    public String toString() {
        return "ModulePermiso{" + "id=" + id + ", module=" + module + ", permisos=" + permisos + ", roles=" + roles + '}';
    }

}
