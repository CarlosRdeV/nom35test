package com.lofton.nom35.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CGCSTF08
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer id;

    @NotNull
    @Column(name = "role")
    private String name;

    @NotNull
    @Column(name = "status")
    private Boolean status;

    @ManyToMany
    @JoinTable(name = "Role_has_ModulePermiso", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "modulePermiso_id"))
    private List<ModulePermiso> modulePermisos;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(mappedBy = "role", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
        CascadeType.REFRESH})
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Role() {
    }

    public Role(String role) {
        this.name = role;
    }

    public List<ModulePermiso> getModulePermisos() {
        return modulePermisos;
    }

    public void setModulePermisos(List<ModulePermiso> modulePermisos) {
        this.modulePermisos = modulePermisos;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", role=" + name + ", status=" + status + '}';
    }

    // convenience methods
    public void addUser(User theUser) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(theUser);
    }

    public void addModulePermiso(ModulePermiso theModulePermiso) {
        if (modulePermisos == null) {
            modulePermisos = new ArrayList<>();
        }

        modulePermisos.add(theModulePermiso);
        
    }
    
    public void removeModulePermiso(ModulePermiso theModulePermiso){
        modulePermisos.remove(theModulePermiso);
    }
    
    

}
