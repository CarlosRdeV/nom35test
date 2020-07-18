package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CGCSTF08
 */
public interface JobRepository extends JpaRepository<Job, Integer>{
    
    Job findByJobname(String jobname);

}
