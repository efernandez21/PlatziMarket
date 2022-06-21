package com.platzi.platzimarket.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase para el item de la compra
 */
@Getter
@Setter
public class PurchaseItem {
    private int productId;
    private int quantity;
    private double total;
    private boolean active;
}
