/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.process.branch;

/**
 *
 * @author CGCSTF01
 */
public class MinRespondents {
    public static int calculateMin(int employees)
    {
    int salida =0;
    
       
            int N = employees;
            double n = (0.9604 * N) / (0.0025 * (N - 1) + 0.9604);
            salida = (int) Math.round(n);
            
       
    return salida;
    }
    
}
