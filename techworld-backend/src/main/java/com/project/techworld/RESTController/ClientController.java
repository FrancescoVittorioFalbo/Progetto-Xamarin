package com.project.techworld.RESTController;

import com.project.techworld.Entity.Cliente;
import com.project.techworld.Exception.ClienteAlreadyExistException;
import com.project.techworld.Exception.ClienteNotExistException;
import com.project.techworld.Service.ClienteService;
import com.project.techworld.Service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    OrdineService ordineService;

    @PostMapping(produces = { "application/json" }, consumes = { "application/json"})
    public ResponseEntity aggiungi(@RequestBody @Valid Cliente client){
        try{
            client.setCoin(40);
            Cliente now=clienteService.addCliente(client);
            return new ResponseEntity<>(now, HttpStatus.OK);
        }catch (ClienteAlreadyExistException c){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @GetMapping("/by_username/{username}")
    public ResponseEntity getOne(@PathVariable("username") String username){
        try{
            return new ResponseEntity<>(clienteService.find(username), HttpStatus.OK);
        }catch (ClienteNotExistException e){
            return new ResponseEntity<>("Il cliente non esiste!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/login/{username}/{password}", produces = { "application/json" })
    public ResponseEntity login(@PathVariable("username") String username, @PathVariable("password") String password) throws ClienteNotExistException {
        if (clienteService.login(username,password)) return new ResponseEntity(clienteService.find(username),HttpStatus.OK);
        return new ResponseEntity(null,HttpStatus.OK);
    }

    @PostMapping(value = "/compra/{username}/{password}/{coin}", produces = { "application/json" })
    public ResponseEntity compraCoin(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("coin") double coin){
        if(clienteService.esite(username)){
            try{
                clienteService.aggiungiCoin(username,coin);
                return new ResponseEntity(clienteService.find(username), HttpStatus.OK);
            } catch (ClienteNotExistException e) {
                return new ResponseEntity("Problema nell'aggiunta delle monete!", HttpStatus.BAD_REQUEST);
            }
        }else return new ResponseEntity("Non esiste un cliente con questo codice!", HttpStatus.BAD_REQUEST);
    }

}