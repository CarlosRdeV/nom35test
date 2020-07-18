package com.lofton.nom35.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Integer id;

    @NotNull
    @Column(name = "jobname")
    private String jobname;

    @NotNull
    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean status;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "job_has_branch",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id")
    )
    private List<Branch> branches;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
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

    public Job() {
    }

    public Job(String jobname, Boolean status) {
        this.jobname = jobname;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", jobname=" + jobname + ", status=" + status + '}';
    }

    // convenience methods
    public void addBranch(Branch theBranch) {
        if (branches == null) {
            branches = new ArrayList<>();
        }
        branches.add(theBranch);
    }

}
