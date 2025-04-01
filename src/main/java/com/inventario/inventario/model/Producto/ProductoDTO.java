package com.inventario.inventario.model.Producto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "almacen_productos")
public class ProductoDTO {
    @Id
    @Column(name = "id_producto")
    private int idProducto;

    @Column(name = "id_Proveedores")
    private Integer idProveedores;

    @Column(name = "id_Categoria")
    private Integer idCategoria;

    @Column(name = "id_unidadmedida")
    private Integer idUnidadMedida;

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

    @Column(name = "peso")
    private Double peso;

    @Column(name = "cantidad_entrada")
    private Double cantidadEntrada;

    @Column(name = "cantidad_salida")
    private Double cantidadSalida;

    @Column(name = "cantidad_final")
    private Double cantidadFinal;

    @Column(name = "Vigencia")
    private String vigencia;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "usuario_baja")
    private String usuarioBaja;

    @Column(name = "fecha_baja")
    private Date fechaBaja;


}
