package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Domain;
import com.lofton.nom35.Entity.Response;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DomainRepository extends JpaRepository<Domain, Integer> {

    Domain findByName(String name);
    
     @Query(value = "Select domain.* from domain, dimension where dimension.id = ?1 and domain.id = dimension.domain_id", nativeQuery = true)
    Domain findByDimensionId(@Param("dimensionId") int dimensionId);
}
