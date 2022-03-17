package com.platzi.platzimarket.persistence;

import com.platzi.platzimarket.domain.Product;
import com.platzi.platzimarket.domain.repository.ProductRepository;
import com.platzi.platzimarket.persistence.crud.ProductoCrudRepository;
import com.platzi.platzimarket.persistence.entity.Producto;
import com.platzi.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    //Inyeccion del Repository original
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper productMapper;

    @Override
    //Metodo para recobrar la lista de productos
    public List<Product> getAll(){
        List<Producto> productosList = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productosList);
    }
    /**
     * Metodo para obtener un producto basado en un id de categoría
     * @param categoryId categoria a buscar
     * @return la lista de productos que cumplen la condición
     */
    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productoList = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productoList));
    }
    /**
     * Metodo para obtener los productos escasos
     * @param quantity cantidad minima de productos para revisar
     * @return los productos escasos de los que se debe hacer pedido
     */
    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>>  productosList = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,
                true);
        //Mapeamos los productos a un Optional de Products
        return productosList.map(productos -> productMapper.toProducts(productos));
    }
    /**
     * Metodo para obtener un producto con base en el id especificado
     * @param productId id del producto a buscar
     * @return un Optional del producto en caso de que no haya tendremos un producto, tendremos un empty
     */
    @Override
    public Optional<Product> getProduct(int productId) {
        Optional<Producto> prod = productoCrudRepository.findById(productId);
        return prod.map(producto -> productMapper.toProduct(producto));
    }
    /**
     * Metodo para guardar un producto en la base de datos
     * @param product un Producto que sera almacenado
     * @return los datos del producto almacenado
     */
    @Override
    public Product save(Product product) {
        Producto producto = productMapper.toProducto(product);
        return  productMapper.toProduct(productoCrudRepository.save(producto));
    }



    /**
     * Metodo que elimina un producto
     * @param productId el id del producto a eliminar
     */
    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}
