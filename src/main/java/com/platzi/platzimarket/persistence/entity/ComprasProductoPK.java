package com.platzi.platzimarket.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Clase que sera embebida en la clase principal ya que esto es una clave primaria compuesta
 */
@Getter
@Setter
@Embeddable
public class ComprasProductoPK implements Serializable {
    @Column(name = "id_compra")
    private Integer idCompra;
    @Column(name = "id_producto")
    private Integer idProducto;
}
