/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Workday;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CGCSTF08
 */
public interface WorkdayRepository extends JpaRepository<Workday, Integer>{
    Workday findByWorkdayname(String workday);
}
