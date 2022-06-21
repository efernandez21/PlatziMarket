package com.platzi.platzimarket.domain.service;

import com.platzi.platzimarket.domain.dto.Product;
import com.platzi.platzimarket.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// con @Service agregamos una separacion semantica de que este es un servicio, el servicio trabaja en terminos de
// dominio y desconoce los procesos internos de conversion
@Service
public class ProductService {
    // Se inyecta la interfaz no la clase concreta
    @Autowired
    private ProductRepository productRepository;

    /**
     * Metodo que retorna toda la lista de productos disponibles en mi base de datos
     * @return lista de productsDTO
     */
    public List<Product> getAll(){
        return productRepository.getAll();
    }

    /**
     * Obtenemos el producto a traves de un id
     * @param productId id del producto a obtener
     * @return un productoDTO si lo hay, sino un opcional con empty values
     */
    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    /**
     * Obtener una lista de productos por categoria definida
     * @param categoryId el id que representa una categoria
     * @return la lista de productos dentro de un Optional
     */
    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    /**
     * Guardamos un productDTO en la base de datos
     * @param product productDto como un objeto
     * @return el productoDTO almacenado
     */
    public Product save(Product product){
        return productRepository.save(product);
    }

    /**
     * Eliminamos un producto en base a un id de producto de mi base de datos
     * @param productId id del producto a eliminar
     */
    public boolean delete(int productId){
        //Reviso si existe el producto, si no existe entonces borremos el producto con el productId
        if (getProduct(productId).isPresent()){
            productRepository.delete(productId);
            return true;
        } else {
            return false;
        }
        //Reviso si existe el producto, si no existe entonces borremos el producto con el productId
        /* return  getProduct(productId).map( product -> {
           productRepository.delete(productId);
           return true;
        }).orElse(false); */
    }


}
