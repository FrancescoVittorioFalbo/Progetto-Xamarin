package com.project.techworld.Service;

import com.project.techworld.Entity.Categoria;
import com.project.techworld.Entity.Prodotto;
import com.project.techworld.Exception.CategoriaAlreadyExistException;
import com.project.techworld.Exception.CategoriaNotExistException;
import com.project.techworld.Repository.CategoriaRepository;
import com.project.techworld.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository cat;

    @Autowired
    ProdottoRepository prod;

    @Transactional(readOnly = true)
    public List<Prodotto> showListProdottiByCategoria(String nome) throws CategoriaNotExistException{
        if(!cat.existsById(nome)) throw new CategoriaNotExistException();
        return new LinkedList<>(cat.getOne(nome).getListaProdotti());
    }

    @Transactional(readOnly = true)
    public List<Categoria> showAll(){
        return cat.findAll();
    }

    @Transactional(readOnly = false)
    public Categoria aggiungiCategoria(Categoria categ) throws CategoriaAlreadyExistException{
        if(cat.existsById(categ.getNome())) throw new CategoriaAlreadyExistException();
        cat.save(categ);
        return categ;
    }

    @Transactional(readOnly = false)
    public void remove(String name) throws CategoriaNotExistException{
        if(!cat.existsById(name)) throw new CategoriaNotExistException();
        cat.delete(cat.getOne(name));
    }
}
