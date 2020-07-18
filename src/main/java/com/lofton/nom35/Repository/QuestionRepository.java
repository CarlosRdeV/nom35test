/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Question;
import com.lofton.nom35.Entity.Survey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CGCSTF08
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
    Question findByText(String text);
    
    List<Question> findBySurvey(Survey survey);
    
}
