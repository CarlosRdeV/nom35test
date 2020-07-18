/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author CGCSTF08
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);
     @Query(value = "Select category.* from domain, category where domain.id = ?1 and category.id = domain.category_id", nativeQuery = true)
    Category findByDomainId(@Param("domainId") int domainId);
}
