package com.platzi.platzimarket.persistence.mapper;

import com.platzi.platzimarket.domain.Category;
import com.platzi.platzimarket.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//Indicamos la integracion con spring, que es un componente de tipo spring
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //Indicar la forma de traducir los objetos, es decir del nombre del atributo en la entidad a como se maneja en el
    // objeto de dominio

    @Mapping(source = "idCategoria", target = "categoryId")
    @Mapping(source = "descripcion", target = "category")
    @Mapping(source = "estado", target = "active")
    Category toCategory(Categoria categoria);
    // Realizamos la conversion de manera inversa, ademas le indicamos que ignore la lista de elementos que es la que
    // no contiene mi category que es mi dto
    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
