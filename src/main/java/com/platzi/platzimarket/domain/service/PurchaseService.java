package com.platzi.platzimarket.domain.service;

import com.platzi.platzimarket.domain.dto.Purchase;
import com.platzi.platzimarket.persistence.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private CompraRepository compraRepository;

    /**
     * Metodo de servicio que permite obtener la informacion de todas las compras realizadas en un establecimiento
     * @return las compras realizadas
     */
    public List<Purchase> getAll(){
        return compraRepository.getAll();
    }

    /**
     * Metodo para devolver la lista de compras de un cliente en especifico
     * @param clientId el codigo del cliente
     * @return un optional que puede ser empty si es el caso de que no se encuentre informacion acerca de compras
     * realizadas por el id del cliente enviado
     */
    public Optional<List<Purchase>> getByClient(String clientId){
        return compraRepository.getByClient(clientId);
    }

    /**
     * Metodo para almacenar una compra en la base de datos, teniendo encuenta que interiormente realiza el
     * procedimiento en cascada de la actualizacion de las tablas afectadas
     * @param purchase DTO de la compra a almacenar
     * @return DTO con la informacion de la compra y su respectivo Id
     */
    public Purchase save(Purchase purchase){
        return compraRepository.save(purchase);
    }
}
