package com.platzi.platzimarket.domain.repository;


import com.platzi.platzimarket.domain.dto.Purchase;

import java.util.List;
import java.util.Optional;

//Repository para controlar objetos del dominio y no acoplada la solucion a la base de datos,
public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);
}
