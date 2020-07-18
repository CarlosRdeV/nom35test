/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Entity;

import java.util.ArrayList;
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
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 *
 * @author CGCSTF01
 */
@Entity
@Table(name = "enterprise")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enterprise")
    private Integer id;

    @NotNull
    @Column(name = "name")
    @Size(min = 4, max = 128)
    private String name;

    @NotNull
    @Column(name = "RFC", unique = true)
    @Size(min = 12, max = 13)
    private String rfc;

    @NotNull
    @Size(min = 4, max = 64)
    @Column(name = "business_role")
    private String business_role;

    @NotNull
    @Column(name = "max_branch")
    @Positive
    private Integer max_branch;

    @OneToMany(mappedBy = "enterprise",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                CascadeType.DETACH, CascadeType.REFRESH})
    private List<Branch> branches;

    @NotNull
    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean status;

    public Enterprise() {

    }

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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getBusiness_role() {
        return business_role;
    }

    public void setBusiness_role(String business_role) {
        this.business_role = business_role;
    }

    public Integer getMax_branch() {
        return max_branch;
    }

    public void setMax_branch(Integer max_branch) {
        this.max_branch = max_branch;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "Enterprise{" + "id=" + id + ", name=" + name + ", rfc=" + rfc + ", business_role=" + business_role + ", max_branch=" + max_branch + ", status=" + status + '}';
    }

    // convenience methods
    public void addBranch(Branch theBranch) {
        if (branches == null) {
            branches = new ArrayList<>();
        }
        branches.add(theBranch);
    }

}
