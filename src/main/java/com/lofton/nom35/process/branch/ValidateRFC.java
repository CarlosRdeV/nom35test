/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.process.branch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author CGCSTF08
 */
public class ValidateRFC {
    
    public final static String REGEX_CURP
            = "[A-Z][A,E,I,O,U,X][A-Z]{2}[0-9]{2}[0-1][0-9][0-3][0-9][M,H][A-Z]{2}[B,C,D,F,G,H,J,K,L,M,N,Ñ,P,Q,R,S,T,V,W,X,Y,Z]{3}[0-9,A-Z][0-9]";
    public final static String REGEX_PM
            = "^(([A-ZÑ&]{3})([0-9]{2})([0][13578]|[1][02])(([0][1-9]|[12][\\d])|[3][01])([A-Z0-9]{3}))|"
            + "(([A-ZÑ&]{3})([0-9]{2})([0][13456789]|[1][012])(([0][1-9]|[12][\\d])|[3][0])([A-Z0-9]{3}))|"
            + "(([A-ZÑ&]{3})([02468][048]|[13579][26])[0][2]([0][1-9]|[12][\\d])([A-Z0-9]{3}))|"
            + "(([A-ZÑ&]{3})([0-9]{2})[0][2]([0][1-9]|[1][0-9]|[2][0-8])([A-Z0-9]{3}))$";
    public final static String REGEX_PF
            = "^(([A-ZÑ&]{4})([0-9]{2})([0][13578]|[1][02])(([0][1-9]|[12][\\\\d])|[3][01])([A-Z0-9]{3}))|\" +\n"
            + "                       \"(([A-ZÑ&]{4})([0-9]{2})([0][13456789]|[1][012])(([0][1-9]|[12][\\\\d])|[3][0])([A-Z0-9]{3}))|\" +\n"
            + "                       \"(([A-ZÑ&]{4})([02468][048]|[13579][26])[0][2]([0][1-9]|[12][\\\\d])([A-Z0-9]{3}))|\" +\n"
            + "                       \"(([A-ZÑ&]{4})([0-9]{2})[0][2]([0][1-9]|[1][0-9]|[2][0-8])([A-Z0-9]{3}))$";
    public final static String REGEX_PE
            = "[A-Z][A,E,I,O,U,X][A-Z]{2}[0-9]{2}[0-1][0-9][0-3][0-9]";

    public static boolean validarCurp(String textoCurp) {
        boolean curpValido = false;
        //System.out.println("Curp a validar; "+textoCurp);
        Pattern pattern = Pattern.compile(REGEX_CURP);
        Matcher matcher = pattern.matcher(textoCurp);
        if (textoCurp.length() == 18) {

            curpValido = matcher.find();
        }

        matcher = null;
        pattern = null;

        //System.out.println(curpValido);
        return curpValido;
    }

    public static boolean validarRFC(String textoCurp) {
        //System.out.println("tmaño " + textoCurp.length());
        boolean rfcValido = false;
        String textoCurp2=textoCurp.toUpperCase();
        //System.out.println(textoCurp);
        //System.out.println(textoCurp2);
        Pattern pattern1 = Pattern.compile(REGEX_PF);
        Pattern pattern2 = Pattern.compile(REGEX_PM);
        //Pattern pattern3 = Pattern.compile(REGEX_PE);
        Matcher matcher1 = pattern1.matcher(textoCurp2);
        Matcher matcher2 = pattern2.matcher(textoCurp2);
        //Matcher matcher3 = pattern3.matcher(textoCurp2);

        if ((matcher2.find() || matcher1.find()) && textoCurp2.length() <= 13) {
          //  System.out.println("Valid");
            rfcValido = true;
        }

        matcher1 = null;
        matcher2 = null;
        pattern1 = null;
        pattern2 = null;

        //System.out.println("RFC valido; " + rfcValido);
        return rfcValido;
    }
    
}
