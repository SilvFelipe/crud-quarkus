package com.crudquarkus.entity;

import com.crudquarkus.filter.PriceFilter;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "products")
@NamedQueries(value = {
        @NamedQuery(name = "Product.updateNomeById", query = "update Product p set p.nome = :nome where p.id = :id"),
        @NamedQuery(name = "Product.updatePrecoById", query = "update Product p set p.preco = :preco where p.id = :id"),
        @NamedQuery(name = "Product.updateStockById", query = "update Product p set p.stock = :stock where p.id = :id"),
        @NamedQuery(name = "Product.findByNameIgnoreCase", query = "from Product where UPPER(nome) = UPPER(:name)"),
        @NamedQuery(name = "Product.filterByPrice", query = "from Product where preco between :priceMin and :priceMax")
})
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

    public Product updateProduct(Long id) {
        updateName(id);
        updatePreco(id);
        updateStock(id);
        return findById(id);
    }

    private void updateName(Long id) {
        update(
                "#Product.updateNomeById",
                Parameters.with("nome", this.nome).and("id", id)
        );
    }

    private void updatePreco(Long id) {
        update(
                "#Product.updatePrecoById",
                Parameters.with("preco", this.preco).and("id", id)
        );
    }

    private void updateStock(Long id) {
        update(
                "#Product.updateStockById",
                Parameters.with("stock", this.stock).and("id", id)
        );
    }

    public static Product findByNameIgnoreCase(String name) {
        return find(
                "#Product.findByNameIgnoreCase",
                Parameters.with("name", name)
        ).firstResult();
    }

    public static List<Product> filterByPrice(PriceFilter filter) {
        return find(
                "#Product.filterByPrice",
                Parameters.with("priceMin", filter.priceMin).and("priceMax", filter.priceMax)
        ).list();
    }
}
