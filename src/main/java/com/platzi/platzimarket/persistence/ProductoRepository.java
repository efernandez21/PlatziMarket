package com.platzi.platzimarket.persistence;

import com.platzi.platzimarket.persistence.crud.ProductoCrudRepository;
import com.platzi.platzimarket.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;


    //Metodo para recobrar la lista de productos
    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    /**
     * Metodo para obtener un producto basado en un id de categoría
     * @param idCategoria categoria a buscar
     * @return la lista de productos que cumplen la condición
     */
    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    /**
     * Metodo para obtener los productos escasos
     * @param cantidad cantidad minima de productos para revisar
     * @return los productos escasos de los que se debe hacer pedido
     */
    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad,true);
    }

    /**
     * Metodo para obtener un producto con base en el id especificado
     * @param idProducto id del producto a buscar
     * @return un Optional del producto en caso de que no haya tendremos un producto, tendremos un empty
     */
    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    /**
     * Metodo para guardar un producto en la base de datos
     * @param producto un Producto que sera almacenado
     * @return los datos del producto almacenado
     */
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    /**
     * Metodo que elimina un producto
     * @param idProducto el id del producto a eliminar
     */
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
