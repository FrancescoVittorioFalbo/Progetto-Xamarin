package com.project.techworld.RESTController;

import com.project.techworld.Entity.Ordine;
import com.project.techworld.Exception.ClienteNotExistException;
import com.project.techworld.Exception.ContoInsufficenteException;
import com.project.techworld.Exception.ProdottoInsufficenteException;
import com.project.techworld.Exception.ProdottoNotExistException;
import com.project.techworld.Service.ClienteService;
import com.project.techworld.Service.OrdineService;
import com.project.techworld.Service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ordini")
@CrossOrigin("*")
public class OrdiniController {

    @Autowired
    OrdineService ordineService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProdottoService prodottoService;

    @GetMapping(value = "/byUser/{username}", produces = { "application/json" })
    public ResponseEntity orderByUser(@PathVariable("username") String username){
        List<Ordine> res= null;
        try {
            res = ordineService.showByUser(username);
        } catch (ClienteNotExistException e) {
            return new ResponseEntity("Il cliente non esiste!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity doOrdine(@Valid @RequestBody Ordine ordine){
        try {
            java.sql.Date oggi=new java.sql.Date(System.currentTimeMillis());
            ordine.setData(oggi);
            Ordine now=ordineService.purchase(ordine);
            return new ResponseEntity(now, HttpStatus.OK);
        } catch (ClienteNotExistException e) {
            return new ResponseEntity("Prima di processare l'ordine devi registrarti!", HttpStatus.BAD_REQUEST);
        } catch (ProdottoNotExistException e) {
            return new ResponseEntity("Il prodotto che stai comprando non si trova sul sito!", HttpStatus.BAD_REQUEST);
        } catch (ContoInsufficenteException e) {
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (ProdottoInsufficenteException e) {
            return new ResponseEntity(null, HttpStatus.OK);
        }
    }

}