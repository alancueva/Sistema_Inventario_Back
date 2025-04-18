package com.inventario.inventario.model.Unidad_Medida;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "unidad_medida")
public class UnidadMedidaDTO {
    @Id
    @Column(name = "id_unidadmedida")
    private Integer idunidadmedida;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "simbolo")
    private String simbolo;


}
