package com.lofton.nom35.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "workday")
public class Workday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_workday")
    private Integer id;

    @NotNull
    @Column(name = "workdayname")
    private String workdayname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkdayname() {
        return workdayname;
    }

    public void setWorkdayname(String workdayname) {
        this.workdayname = workdayname;
    }

    public Workday() {
    }

    public Workday(String workdayname) {
        this.workdayname = workdayname;
    }

    @Override
    public String toString() {
        return "Workday{" + "id=" + id + ", workdayname=" + workdayname + '}';
    }

}
