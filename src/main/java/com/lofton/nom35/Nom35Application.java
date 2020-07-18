package com.lofton.nom35;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Nom35Application {
    // extends SpringBootServletInitializer

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Nom35Application.class);
//    }
    public static void main(String[] args) {
        SpringApplication.run(Nom35Application.class, args);
//        for(int x=1; x<=5; x++){
//        for (int y=119;y<=132;y++ ){
//            System.out.println("( 0,"+x+","+y+"),");
//        }
//        }
    }

}
