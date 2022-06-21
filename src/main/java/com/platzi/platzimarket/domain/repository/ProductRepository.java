package com.platzi.platzimarket.domain.repository;

import com.platzi.platzimarket.domain.dto.Product;

import java.util.List;
import java.util.Optional;

//Repository para controlar objetos del dominio y no acoplada la solucion a la base de datos,
public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);
}
