/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.process.branch;

import java.util.Random;

/**
 *
 * @author CGCSTF01
 */
public class BranchValidator {
    public static String createCalculator()
    {
    String salida ="";
  
      int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 5;
    Random random = new Random();
 
    String generatedString = random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(targetStringLength)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();
 
    salida= generatedString;
            
       
    return salida;
    }
    
}
