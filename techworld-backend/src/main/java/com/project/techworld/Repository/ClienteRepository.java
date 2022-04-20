package com.project.techworld.Repository;

import com.project.techworld.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,String> {

    boolean existsByEmail (String email);
    boolean existsByCell (Long cell);
    boolean existsByUsernameAndPassword(String username, String password);
    Cliente findByEmail (String email);
    Cliente findByCell (long cell);
}
