package com.project.techworld.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

@Entity
@Table(name = "ordine")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ordine implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "citta",nullable = false)
    private String citta;

    @Basic
    @Column(name = "via",nullable = false)
    private String via;

    @Basic
    @Column(name = "nr",nullable = false)
    private int nr;

    @Column(name = "data_ordine", nullable = true)
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getCitta() { return citta; }

    public void setCitta(String citta) { this.citta = citta; }

    public String getVia() { return via; }

    public void setVia(String via) { this.via = via; }

    public int getNr() { return nr; }

    public void setNr(int nr) { this.nr = nr; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordine ordine = (Ordine) o;
        return id == ordine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "id=" + id +
                ", listaProdotti=" + listaProdotti +
                ", utente=" + utente +
                ", data dell'ordine= " + data.toString()+
                '}';
    }

    @ManyToMany
    @JoinTable(name = "ordine_prodotto",
                joinColumns = {@JoinColumn(name = "id_ordine")},
                inverseJoinColumns = {@JoinColumn(name = "id_prodotto")})
    private Collection<Prodotto> listaProdotti=new LinkedList<>();

    public Collection<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(Collection<Prodotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    @ManyToOne (optional = false)
    @JoinColumn(name = "id_cliente")
    private Cliente utente;

    public Cliente getUtente() {
        return utente;
    }

    public void setUtente(Cliente utente) {
        this.utente = utente;
    }

}
