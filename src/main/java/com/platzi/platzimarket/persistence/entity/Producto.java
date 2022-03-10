package com.platzi.platzimarket.persistence.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Montaje de la entity que representa a un producto en mi base de datos
 * con sus respectivas anotaciones
 */
@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto {
    //Definicion de mi clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    private String nombre;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;
    //Atraves de esta relacion no se borrara ni se insertara una categoria, para hacerlo sera directamente en categoria
    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Categoria categoria;


}
