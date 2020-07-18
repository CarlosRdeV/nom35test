package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Enterprise;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CGCSTF08
 */
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {

    Enterprise findByRfc(String rfc);
}
