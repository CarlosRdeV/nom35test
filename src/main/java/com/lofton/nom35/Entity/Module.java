package com.lofton.nom35.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CGCSTF08
 */
@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module")
    private Integer id;

    @NotNull
    @Column(name = "")
    private String name;

    @NotNull
    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean status;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(mappedBy = "module", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
        CascadeType.REFRESH})
    private List<ModulePermiso> modulesPermisos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ModulePermiso> getModulesPermisos() {
        return modulesPermisos;
    }

    public void setModulesPermisos(List<ModulePermiso> modulesPermisos) {
        this.modulesPermisos = modulesPermisos;
    }

    public Module() {
    }

    public Module(String name, Boolean status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Module{" + "id=" + id + ", name=" + name + '}';
    }

}
