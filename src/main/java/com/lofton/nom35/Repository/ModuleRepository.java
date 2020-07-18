package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CGCSTF08
 */
public interface ModuleRepository extends JpaRepository<Module, Integer> {

    Module findByName(String name);
}
