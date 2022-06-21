package com.platzi.platzimarket.persistence.mapper;

import com.platzi.platzimarket.domain.dto.Product;
import com.platzi.platzimarket.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

//Indicamos que componente basarse para convertir al tipo category, ya que tenemos dentro un Mapping que transforma
// una categoria a category por eso debemos indicar el Mapper que realiza esto para que MapStruct lo tenga en cuenta,
// ya que es un objeto de ese tipo dentro de otro  y para que use el mapper ya implementado
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category")
    })
    Product toProduct(Producto producto);
    //No se define el mapping debido a que MapStruct se encarga de analizar el comportamiento del mismo tipo de
    // conversion
    List<Product> toProducts(List<Producto> productos);
    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
}
