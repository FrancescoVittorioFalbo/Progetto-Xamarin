package com.project.techworld.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "prodotto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Prodotto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "nome", nullable = false)
    private String nome;

    @Basic
    @Column(name = "marca", nullable = false)
    private String marca;

    @Basic
    @Column(name = "quantita", nullable = false)
    private int qta;

    @Basic
    @Column(name = "prezzo", nullable = false)
    private double prezzo;

    @Basic
    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @Basic
    @Column(name = "link", nullable = true)
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    @Version
    @Column(name = "versione")
    private int versione;

    public int getVersione() {
        return versione;
    }

    public void setVersione(int versione) {
        this.versione = versione;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return id == prodotto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "Id=" + id +
                ", marca='" + marca + '\'' +
                ", qta=" + qta +
                ", prezzo=" + prezzo +
                ", categoria=" + categoria +
                '}';
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
