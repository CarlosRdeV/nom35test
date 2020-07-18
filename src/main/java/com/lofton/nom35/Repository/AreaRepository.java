package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CGCSTF08
 */
public interface AreaRepository extends JpaRepository<Area, Integer> {

    Area findByAreaname(String areaname);

   
    
}
