
package com.lofton.nom35.process.reports;

import io.jsonwebtoken.lang.Strings;

public class Results {
    public static String Interpreta(String rangos,int total)
    {
        String salida="";
        String[] var = rangos.split("@");
        
        if (total<Integer.parseInt(var[0])){
        salida = "Nulo o despreciable";
        }else if(Integer.parseInt(var[0])<=total && total< Integer.parseInt(var[1])){
        salida = "Bajo";
        
        }else if(Integer.parseInt(var[1])<=total && total< Integer.parseInt(var[2])){
        salida = "Medio";
        
        }else if(Integer.parseInt(var[2])<=total && total< Integer.parseInt(var[3])){
        salida = "Alto";
        
        }else if( total >= Integer.parseInt(var[3])){
        salida = "Muy Alto";
        
        }
        
    return salida;
    }
}
