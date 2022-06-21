package com.platzi.platzimarket.web.controller;

import com.platzi.platzimarket.domain.dto.Purchase;
import com.platzi.platzimarket.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/purchases")
public class PurchaseController {
    //Inyectamos el servicio
    @Autowired
    private PurchaseService purchaseService;

    /**
     * RestController que me retorna la informacion de todas las compras registradas en mi BD
     * @return ResponseEntity con la informacion respectiva ded las compras disponibles en la BD
     */
    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAllPurchases(){
        // Creamos un objeto de tipo Response Entity que es lo que devolvemos
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    /**
     * RestController que me permite consultar la lista de compras realizadas por un cliente en especifico
     * @param clientId el id del cliente a consultar las compras realizadas
     * @return Lista de compras realizada por un cliente en especifico
     */
    @GetMapping("/client/{id}")
    public ResponseEntity<List<Purchase>> getPurchasesByClient(@PathVariable("id") String clientId){
        if(purchaseService.getByClient(clientId).get().isEmpty()){
            return new ResponseEntity(Optional.empty(), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(purchaseService.getByClient(clientId), HttpStatus.OK);
        }
        //Soluciones posibles, pero se determino por trabajar con el if
        /*
        return (purchaseService.getByClient(clientId).isPresent() && !purchaseService.getByClient(clientId).get().isEmpty()) ?
                new ResponseEntity(purchaseService.getByClient(clientId),HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);*/
        /*return purchaseService.getByClient(clientId)
                .map(purchases -> new ResponseEntity<>(purchases,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));*/
    }

    /**
     * RestController Post para guardar la informacion acerca de una compra realizada
     * @param purchase la compra a guardar
     * @return El ResponseEntity con la informacion de la compra realizada
     */
    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
