package com.crudquarkus.controller;

import com.crudquarkus.dto.ProductDto;
import com.crudquarkus.entity.Product;
import com.crudquarkus.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Objects;

@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    private ProductService productService;

    @GET
    public Response listProducts() {
        return Response.ok(productService.listAll()).build();
    }

    @POST
    public Response addProduct(ProductDto dto) {
        return Response.ok(productService.saveProduct(dto)).build();
    }

    @DELETE
    public Response deleteProduct(ProductDto dto) {
        productService.deleteProduct(dto);
        return Response.ok("Produto removido com sucesso").build();
    }

    @PUT
    public Response updateProduct(ProductDto dto) {
        return Response.ok(productService.updateProduct(dto)).build();
    }
}
