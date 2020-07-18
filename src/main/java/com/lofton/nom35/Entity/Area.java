package com.lofton.nom35.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Integer id;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "areaname")
    @Size(min = 4, max = 128, message = "El nombre del area debe ser entre 4 y 128 caracteres")
    private String areaname;

    @NotNull
    @Column(name = "status")
    private Boolean status;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "area_has_branch",
            joinColumns = @JoinColumn(name = "area_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id")
    )
    private List<Branch> branches;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
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

    public Area() {
    }

    public Area(String areaname, Boolean status) {
        this.areaname = areaname;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Area{" + "id=" + id + ", areaname=" + areaname + ", status=" + status + '}';
    }

    // convenience methods
    public void addBranch(Branch theBranch) {
        if (branches == null) {
            branches = new ArrayList<>();
        }
        branches.add(theBranch);
    }

}
