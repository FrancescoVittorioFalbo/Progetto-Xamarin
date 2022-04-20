package com.project.techworld.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

@Entity
@Table(name = "categoria")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Categoria implements Serializable {

    @Id
    @Column(name = "nome", nullable = false)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(nome, categoria.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                ", listaProdotti=" + listaProdotti +
                '}';
    }

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private Collection<Prodotto> listaProdotti=new LinkedList<>();

    public Collection<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(Collection<Prodotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }
}
