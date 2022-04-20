package com.project.techworld.Service;

import com.project.techworld.Entity.Cliente;
import com.project.techworld.Exception.ClienteAlreadyExistException;
import com.project.techworld.Exception.ClienteNotExistException;
import com.project.techworld.Exception.ContoInsufficenteException;
import com.project.techworld.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository client;

    @Autowired
    EntityManager em;

    @Transactional(readOnly = true)
    public boolean login(String username, String password){
        return client.existsByUsernameAndPassword(username, password);
    }

    @Transactional(readOnly = true)
    public boolean esite(String cliente){
        return client.existsById(cliente);
    }

    @Transactional(readOnly = false)
    public Cliente addCliente(Cliente cliente) throws ClienteAlreadyExistException {
        if(cliente.getUsername()!= null && client.existsById(cliente.getUsername()))
            throw new ClienteAlreadyExistException();
        client.save(cliente);
        return cliente;
    }

    @Transactional(readOnly = false)
    public void rimuoviCliente(String user) throws ClienteNotExistException {
        if(!client.existsById(user))
            throw new ClienteNotExistException();
        client.delete(client.getOne(user));
    }

    @Transactional(readOnly = false)
    public Cliente aggiungiCoin(String user, double coin) throws ClienteNotExistException{
        if(!client.existsById(user)) throw new ClienteNotExistException();
        Cliente now=client.getOne(user);
        now.setCoin(now.getCoin()+coin);
        em.merge(now);
        return now;
    }

    @Transactional(readOnly = true)
    public Cliente find (String user) throws ClienteNotExistException{
        if(!client.existsById(user)) throw new ClienteNotExistException();
        return client.getOne(user);
    }

    @Transactional(readOnly = false)
    public Cliente paga(String user, double coin) throws ClienteNotExistException, ContoInsufficenteException {
        if(!client.existsById(user)) throw new ClienteNotExistException();
        Cliente now=client.getOne(user);
        double conto=now.getCoin()-coin;
        if(conto<0) throw new ContoInsufficenteException();
        now.setCoin(conto);
        em.merge(now);
        return now;
    }

}
