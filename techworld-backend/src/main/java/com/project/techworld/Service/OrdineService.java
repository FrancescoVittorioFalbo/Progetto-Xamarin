package com.project.techworld.Service;

import com.project.techworld.Entity.Ordine;
import com.project.techworld.Entity.Prodotto;
import com.project.techworld.Exception.ClienteNotExistException;
import com.project.techworld.Exception.ContoInsufficenteException;
import com.project.techworld.Exception.ProdottoInsufficenteException;
import com.project.techworld.Exception.ProdottoNotExistException;
import com.project.techworld.Repository.ClienteRepository;
import com.project.techworld.Repository.OrdineRepository;
import com.project.techworld.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


@Service
public class OrdineService {

    @Autowired
    OrdineRepository ord;

    @Autowired
    ClienteRepository client;

    @Autowired
    ProdottoRepository prod;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProdottoService prodottoService;

    @Transactional(readOnly = true)
    public List<Ordine> showAll (){
        return ord.findAll();
    }

    @Transactional(readOnly = true)
    public List<Ordine> showByUser (String username) throws ClienteNotExistException {
        if(!client.existsById(username)) throw new ClienteNotExistException();
        return ord.findByUtente(client.getOne(username));
    }

    @Transactional(readOnly = false)
    public Ordine purchase (Ordine ordine) throws ClienteNotExistException,
            ProdottoNotExistException, ContoInsufficenteException, ProdottoInsufficenteException {
        if(!client.existsById(ordine.getUtente().getUsername())) throw new ClienteNotExistException();
        ordine.setUtente(client.getOne(ordine.getUtente().getUsername()));
        List<Prodotto> lista=new LinkedList<>();
        int prezzo=0;
        for(Prodotto p : ordine.getListaProdotti()){
            if(!prod.existsById(p.getId())) throw new ProdottoNotExistException();
            Prodotto now=prod.getOne(p.getId());
            if(now.getQta()<=0) throw new ProdottoInsufficenteException(now);
            lista.add(now);
            prodottoService.shoppa(now);
            prezzo+=now.getPrezzo();
        }
        clienteService.paga(ordine.getUtente().getUsername(),prezzo);
        ordine.setListaProdotti(lista);
        ord.save(ordine);
        return ordine;
    }
}
