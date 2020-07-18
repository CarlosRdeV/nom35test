package com.lofton.nom35.process.branch;

import com.lofton.nom35.Entity.Token;
import com.lofton.nom35.Repository.TokenRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CGCSTF08
 */
@Service
public class BuscarToken {

    @Autowired
    public  static TokenRepository tokenRepo;

    public static Boolean validad(String token) {

        System.out.println(">>DENTRO: " + token);

        Optional<Token> tempToken = tokenRepo.findByToken(token);

        System.out.println(">>DENTRO OPTIONAL: " + token);

        return tempToken.isPresent();
    }

}
