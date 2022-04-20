package com.project.techworld.Exception;

import com.project.techworld.Entity.Prodotto;

public class ProdottoInsufficenteException extends Exception {

    public int id;

    public ProdottoInsufficenteException(Prodotto p){id=p.getId();}

    public int getId() {
        return id;
    }

}
