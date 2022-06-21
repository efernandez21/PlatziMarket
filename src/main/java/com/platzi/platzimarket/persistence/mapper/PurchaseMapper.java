package com.platzi.platzimarket.persistence.mapper;

import com.platzi.platzimarket.domain.dto.Purchase;
import com.platzi.platzimarket.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Interface para el mapeo de la compra a mi objeto del dominio, podemos ignorar los elementos no mapeados con la
 * propiedad unmappedTargetPolicy = ReportingPolicy.IGNORE en nuestro @Mapper, y asi no hacer los ignore de forma
 * explicita
 */
@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    //Siempre en la clase destino debemos tener todos los mapeos, si no debemos ignorarlos en mi clase fuente u origen
    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            //Mapping usado por es el cual  convertira los productos uno a uno
            @Mapping(source = "comprasProductos", target = "items"),
    })
    Purchase toPurchase(Compra compra);
    List<Purchase> toPurchases( List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase);
}
