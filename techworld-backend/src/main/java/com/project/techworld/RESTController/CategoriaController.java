package com.project.techworld.RESTController;

import com.project.techworld.Entity.Categoria;
import com.project.techworld.Entity.Prodotto;
import com.project.techworld.Exception.CategoriaAlreadyExistException;
import com.project.techworld.Exception.CategoriaNotExistException;
import com.project.techworld.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/categ")
@CrossOrigin("*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Categoria cat){
        try{
            Categoria now=categoriaService.aggiungiCategoria(cat);
            return new ResponseEntity<>(now, HttpStatus.OK);
        } catch (CategoriaAlreadyExistException e){
            return new ResponseEntity<>("La categoria che stai creando esiste già!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by_name/{name}")
    public ResponseEntity getByName(@PathVariable("name") String name){
        List<Prodotto> res;
        try{
            res=categoriaService.showListProdottiByCategoria(name);
        }catch (CategoriaNotExistException e){
            return new ResponseEntity<>("La categoria che cerchi non esiste!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/by_name/{name}")
    public ResponseEntity delete(@PathVariable("name") String name){
        try {
            categoriaService.remove(name);
        } catch (CategoriaNotExistException e) {
            return new ResponseEntity<>("La categoria che vuoi eliminare non esiste", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("La categoria "+name+" è stata rimossa correttamente!", HttpStatus.OK);
    }

}
