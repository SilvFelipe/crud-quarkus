package com.crudquarkus.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends PanacheEntity {

    private String nome;
    private Long stock;
    private Double preco;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getStock() {
        return stock;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getPreco() {
        return preco;
    }
}
