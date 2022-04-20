package com.project.techworld.Repository;

import com.project.techworld.Entity.Cliente;
import com.project.techworld.Entity.Ordine;
import com.project.techworld.Entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    List<Ordine> findByUtente (Cliente client);
    List<Ordine> findByListaProdottiContains (Prodotto prod);
    List<Ordine> findByCitta (String citta);

}
