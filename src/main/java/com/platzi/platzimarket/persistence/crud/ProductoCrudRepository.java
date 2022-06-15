package com.platzi.platzimarket.persistence.crud;

import com.platzi.platzimarket.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//La interfaz recibe la entidad que manejara y el tipo de valor de su clave primaria
public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {
    //Trabajando con Query Methods
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    //Busqueda de productos que se estan agotando y que tengan un estado activo
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
    //Con un Query podemos hacer esto también
    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> encontrarPorCategoria(int idCategoria);
}
