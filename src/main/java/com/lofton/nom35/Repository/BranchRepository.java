package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Branch;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CGCSTF01
 */
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    Branch findByName(String name);
    Optional<Branch> findByValidator(String validator);
}
