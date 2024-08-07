package com.crudquarkus.service;

import com.crudquarkus.dto.ProductDto;
import com.crudquarkus.entity.Product;
import com.crudquarkus.filter.PriceFilter;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ProductService {

    public List<Product> listAll() {
        return Product.listAll(Sort.by("id"));
    }

    public Product findByName(String nome) {
        return Product.findByNameIgnoreCase(nome);
    }

    public List<Product> filterByPrice(PriceFilter filter) {
        return Product.filterByPrice(filter);
    }

    @Transactional
    public Product saveProduct(ProductDto dto) {
        Product product = productMapper(dto);
        product.persist();
        return product;
    }

    @Transactional
    public void deleteProduct(ProductDto dto) {
        productMapper(dto).delete();
    }

    @Transactional
    public Product updateProduct(ProductDto dto){
        return productMapper(dto).updateProduct(dto.getId());
    }

    private static Product productMapper(ProductDto dto) {
        Product product = new Product();
        product.setNome(dto.getNome());
        product.setPreco(dto.getPreco());
        product.setStock(dto.getStock());
        if(!Objects.isNull(dto.getId())){
            product.id = dto.getId();
        }
        return product;
    }

}
