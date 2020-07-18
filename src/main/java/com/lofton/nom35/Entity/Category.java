package com.lofton.nom35.Entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author CGCSTF08
 */
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "name", unique = true)
    @Size(min = 4, max = 128, message = "El nombre de la categoria debe estar entre 4 y 128 caracteres")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private List<Domain> domains;
    
    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "rangos")
    private String rangos;

    public String getRangos() {
        return rangos;
    }

    public void setRangos(String rangos) {
        this.rangos = rangos;
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

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }
    
    

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + '}';
    }

    // convenience methods domains
    public void addDomain(Domain theDomain) {
        if (domains == null) {
            domains = new ArrayList<>();
        }
        domains.add(theDomain);
    }

}
