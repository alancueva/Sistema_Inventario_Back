package com.inventario.inventario.model.Ubigeo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ubigeo")
public class Ubigeo {
    @Id
    @Column(name = "ubigeo_codigo")
    private String ubigeo_codigo;
    @Column(name = "ubigeo_departamento")
    private String ubigeo_departamento;
    @Column(name = "ubigeo_provincia")
    private String ubigeo_provincia;
    @Column(name = "ubigeo_distrito")
    private String ubigeo_distrito;
}
