package com.project.techworld.Repository;

import com.project.techworld.Entity.Categoria;
import com.project.techworld.Entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto,Integer> {

    List<Prodotto> findByMarca (String marca);
    List<Prodotto> findByQtaIsLessThan (int x);
    List<Prodotto> findByCategoria (Categoria cat);
    List<Prodotto> findByNomeContaining(String parola);
    boolean existsByNome(String nome);

}
