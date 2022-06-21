package com.platzi.platzimarket.persistence.mapper;

import com.platzi.platzimarket.domain.dto.PurchaseItem;
import com.platzi.platzimarket.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    /**
     * Mapeamos desde el compras producto el id del producto elegido para trabajar con este en mi lista de compras,
     * como tenemos un total del mismo nombre en el dto y en la entidad no es necesario implementarlo
     * @param comprasProducto la entindad de compras producto que se convertira a un item de la canasta
     * @return un PurchaseItem mapeado
     */
    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            //@Mapping(source = "total", target = "total"),
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto comprasProducto);

    /**
     * Mostramos que elementos ignoraremos al hacer la conversion contraria
     * @param purchaseItem item de la compra
     * @return un ComprasProducto en base a un purchase item
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true),
    })
    ComprasProducto toComprasProducto(PurchaseItem purchaseItem);
}
