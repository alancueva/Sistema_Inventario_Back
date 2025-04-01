package com.inventario.inventario.model.Categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "categoria")
public class InsertarCategoria {

    @Id
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "nombre_categoria", nullable = false, length = 100)
    private String nombreCategoria;

    @Column(name = "descripcion", columnDefinition = "MEDIUMTEXT")
    private String descripcion;

    @Column(name = "vigencia", length = 2)
    private String vigencia;

    @Column(name = "usuario_creacion", length = 45)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_modificacion", length = 45)
    private String usuarioModificacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_baja", length = 45)
    private String usuarioBaja;

    @Column(name = "fecha_baja")
    private LocalDateTime fechaBaja;
}
