package com.platzi.platzimarket.persistence;

import com.platzi.platzimarket.domain.dto.Purchase;
import com.platzi.platzimarket.domain.repository.PurchaseRepository;
import com.platzi.platzimarket.persistence.crud.CompraCrudRepository;
import com.platzi.platzimarket.persistence.entity.Compra;
import com.platzi.platzimarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private  CompraCrudRepository compraCrudRepository;
    @Autowired
    private  PurchaseMapper purchaseMapper;

    /*public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper purchaseMapper) {
        this.compraCrudRepository = compraCrudRepository;
        this.purchaseMapper = purchaseMapper;
    }*/

    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        //Retornara un Optional.Empty() en caso de no contener informacion
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        // Debemos aplicar la informacion guardada en cascada, es decir que compra conoce los productos y los
        // productos saben a que compra pertenecen
        compra.getComprasProductos().forEach(comprasProducto -> comprasProducto.setCompra(compra));
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
