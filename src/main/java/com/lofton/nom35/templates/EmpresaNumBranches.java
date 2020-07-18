package com.lofton.nom35.templates;

import com.lofton.nom35.Entity.Branch;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CGCSTF08
 */
public class EmpresaNumBranches implements Serializable {

    private Integer id;

    private String name;

    private String rfc;

    private String business_role;

    private Integer max_branch;

    private List<Branch> branches;

    private Integer numBranches;

    private Boolean status;

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

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public Integer getNumBranches() {
        return numBranches;
    }

    public void setNumBranches(Integer numBranches) {
        this.numBranches = numBranches;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public EmpresaNumBranches() {
    }

    public EmpresaNumBranches(Integer id, String name, String rfc, String business_role, Integer max_branch, List<Branch> branches, Integer numBranches, Boolean status) {
        this.id = id;
        this.name = name;
        this.rfc = rfc;
        this.business_role = business_role;
        this.max_branch = max_branch;
        this.branches = branches;
        this.numBranches = numBranches;
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmpresaNumBranches{" + "id=" + id + ", name=" + name + ", rfc=" + rfc + ", business_role=" + business_role + ", max_branch=" + max_branch + ", branches=" + branches + ", numBranches=" + numBranches + ", status=" + status + '}';
    }

}
