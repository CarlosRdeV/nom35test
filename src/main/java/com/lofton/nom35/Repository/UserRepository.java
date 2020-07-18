package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author CGCSTF08
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String Username);

    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE email IS NOT NULL")
    List<User> findUsers();

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE email IS NULL")
    List<User> findEmployees();

}
