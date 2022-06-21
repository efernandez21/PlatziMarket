package com.platzi.platzimarket.web.controller;

import com.platzi.platzimarket.domain.dto.Product;
import com.platzi.platzimarket.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controlador de mi api REST, aqui se montaran todas las peticiones y definimos el mapping para mi backend con mis
// metodos de productos
@RestController
@RequestMapping(path = "/products")
public class ProductController {
    // Inyectamos el servicio
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        // Creamos un objeto de tipo Response Entity que es lo que devolvemos
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int productId){
        //Si no se ejecuta el map devolvemos que no existe, el notFound no retorna nada
        return productService.getProduct(productId)
                //Podemos usar esta otra opcion para realizar lo mismo del map y el orElse
                //ResponseEntity.of(productService.getProduct(productId));
                .map(product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("id")int categoryId){
        //Usamos este if debido a que si la respuesta es vacia nos devolvera un [], y un status en 200, asi que con
        // esto controlamos que al tratar un valor inexistente podamos retornar un status de NotFound
        // Esta puede ser una condicion alterna: products.isPresent() && !products.get().isEmpty()
        //!productService.getByCategory(categoryId).isPresent()
        if(productService.getByCategory(categoryId).get().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(productService.getByCategory(categoryId), HttpStatus.OK);
        }

        //return productService.getByCategory(categoryId)
        //        .map(products -> new ResponseEntity<>(products,HttpStatus.OK))
        //        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint expuesto para guardar un producto en la base de datos usamos
     * el @RequestBody para enviar el cuerpo de la peticion no en la url, sino en el body
     * @param product producto a guardar en la base de datos
     * @return el DTO del producto guardado en la base de datos
     */
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){

        return ( productService.delete(productId))
                ? new ResponseEntity(HttpStatus.OK) :
                  new  ResponseEntity(HttpStatus.NOT_FOUND) ;
    }



}
