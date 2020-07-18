/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Module;
import com.lofton.nom35.Entity.ModulePermiso;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CGCSTF08
 */
public interface ModulePermisoRepository extends JpaRepository<ModulePermiso, Integer> {

    Optional<ModulePermiso> findByModuleAndPermisos(Module module, String permisos);

}
