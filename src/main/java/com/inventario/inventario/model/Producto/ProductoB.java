package com.inventario.inventario.model.Producto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductoB {
    @NotBlank(message = "El número de serie no debe estar vacío")
    private String numeroSerie;
    @NotBlank(message = "El nombre no debe estar vacío")
    private String nombre;
    private String descripcion;
    private String estado;
}
