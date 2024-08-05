package com.crudquarkus.service;

import com.crudquarkus.dto.ProductDto;
import com.crudquarkus.entity.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductService {

    public List<Product> listAll() {
        return Product.listAll();
    }

    @Transactional
    public Product saveProduct(ProductDto dto) {
        Product product = new Product();
        product.setNome(dto.getNome());
        product.setPreco(dto.getPreco());
        product.setStock(dto.getStock());
        product.persist();

        return product;
    }
}
