package com.project.techworld.RESTController;

import com.project.techworld.Entity.Prodotto;
import com.project.techworld.Exception.CategoriaNotExistException;
import com.project.techworld.Exception.ProdottoAlreadyExistException;
import com.project.techworld.Exception.ProdottoNotExistException;
import com.project.techworld.Service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin("*")
public class ProdottoController {

    @Autowired
    ProdottoService prodottoService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Prodotto prod){
        try{
            Prodotto now=prodottoService.addProduct(prod);
            return new ResponseEntity<>(now, HttpStatus.OK);
        } catch (ProdottoAlreadyExistException e) {
            return new ResponseEntity<>("Il prodotto che stai inserendo ha il nome di un prodotto che già esiste!", HttpStatus.BAD_REQUEST);
        } catch (CategoriaNotExistException e) {
            return new ResponseEntity<>("La categoria del prodotto non esiste!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Prodotto> getAll(){
        return prodottoService.showAllProducts();
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity getOne(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(prodottoService.getOne(id), HttpStatus.OK);
        } catch (ProdottoNotExistException e) {
            return new ResponseEntity<>("Il prodotto che cerchi non esiste!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/aggiorna")
    public ResponseEntity aggiorna (Prodotto prod){
        try{
            Prodotto now=prodottoService.aggiorna(prod);
            return new ResponseEntity<>(now, HttpStatus.OK);
        } catch (ProdottoNotExistException e) {
            return new ResponseEntity<>("Il prodotto che cerchi non esiste!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity delete(@PathVariable("id") int id){
        try{
            prodottoService.delete(id);
            return new ResponseEntity<>("Il prodotto è stato eliminato", HttpStatus.OK);
        }catch (ProdottoNotExistException e){
            return new ResponseEntity<>("Il prodotto che cerchi non esiste!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/categ/{name}")
    public ResponseEntity getByCategoria(@PathVariable("name") String name){
        try{
            List<Prodotto> res=prodottoService.showByCategoria(name);
            if(res.size()==0) return new ResponseEntity<>("Non esistono prodotti con questa marca!", HttpStatus.OK);
            return new ResponseEntity<>(res , HttpStatus.OK);
        } catch (CategoriaNotExistException e) {
            return new ResponseEntity<>("La categoria non esiste!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/ordina/{id}/{qta}")
    public ResponseEntity ordinaNuoviProdotti(@PathVariable("id") int id, @PathVariable("qta") int qta){
        try{
            Prodotto now=prodottoService.aggiungiQta(id, qta);
            return new ResponseEntity<>(now, HttpStatus.OK);
        } catch (ProdottoNotExistException e) {
            return new ResponseEntity<>("Il prodotto non esiste!", HttpStatus.BAD_REQUEST);
        }
    }

}
