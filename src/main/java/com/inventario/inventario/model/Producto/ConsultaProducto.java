package com.inventario.inventario.model.Producto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "almacen_productos")
public class ConsultaProducto {

    @Id
    @Column(name = "id_producto")
    private int idProducto;

    @Column(name = "numero_serie" , columnDefinition = "VARCHAR(12)")
    private String numeroSerie;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "FechaAdquisicion")
    private Date fechaAdquisicion;

    @Column(name = "FechaVencimiento")
    private Date fechaVencimiento;

    @Column(name = "Marca")
    private String marca;

    @Column(name = "Modelo")
    private String modelo;

    @Column(name = "Estado")
    private String estado;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "cantidad_final")
    private Double cantidadFinal;

    @Column(name = "Vigencia")
    private String vigencia;



}
