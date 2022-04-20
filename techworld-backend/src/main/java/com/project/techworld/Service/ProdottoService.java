package com.project.techworld.Service;

import com.project.techworld.Entity.Prodotto;
import com.project.techworld.Exception.CategoriaNotExistException;
import com.project.techworld.Exception.ProdottoAlreadyExistException;
import com.project.techworld.Exception.ProdottoNotExistException;
import com.project.techworld.Repository.CategoriaRepository;
import com.project.techworld.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    ProdottoRepository prod;

    @Autowired
    CategoriaRepository categ;

    @Autowired
    EntityManager em;

    @Transactional(readOnly = false)
    public Prodotto addProduct(Prodotto prodotto) throws ProdottoAlreadyExistException,CategoriaNotExistException {
        if(prod.existsByNome(prodotto.getNome()))
            throw new ProdottoAlreadyExistException();
        if(!categ.existsById(prodotto.getCategoria().getNome())) throw new CategoriaNotExistException();
        prodotto.setCategoria(categ.getOne(prodotto.getCategoria().getNome()));
        prodotto.setQta(3);
        prod.save(prodotto);
        return prodotto;
    }

    @Transactional(readOnly = true)
    public List<Prodotto> showAllProducts(){
        return prod.findAll();
    }

    @Transactional(readOnly = false)
    public Prodotto aggiorna(Prodotto prodotto) throws ProdottoNotExistException {
        if(!prod.existsById(prodotto.getId()))
            throw new ProdottoNotExistException();
        em.merge(prodotto);
        return prodotto;
    }

    @Transactional(readOnly = false)
    public void delete(int id) throws ProdottoNotExistException{
        if(!prod.existsById(id))
            throw new ProdottoNotExistException();
        prod.delete(prod.getOne(id));
    }

    @Transactional(readOnly = true)
    public List<Prodotto> showByCategoria (String nomeCat) throws CategoriaNotExistException {
        if(!categ.existsById(nomeCat))
            throw new CategoriaNotExistException();
        return prod.findByCategoria(categ.getOne(nomeCat));
    }

    @Transactional(readOnly = false)
    public Prodotto aggiungiQta(int id, int qta) throws ProdottoNotExistException{
        if(!prod.existsById(id)) throw new ProdottoNotExistException();
        Prodotto product=prod.getOne(id);
        product.setQta(product.getQta()+qta);
        em.merge(product);
        return product;
    }

    @Transactional(readOnly = true)
    public Prodotto getOne(int id) throws ProdottoNotExistException{
        if(!prod.existsById(id)) throw new ProdottoNotExistException();
        return prod.getOne(id);
    }

    @Transactional(readOnly = false)
    public Prodotto shoppa(Prodotto prodotto){
        prodotto.setQta(prodotto.getQta()-1);
        em.merge(prodotto);
        return prodotto;
    }

}
