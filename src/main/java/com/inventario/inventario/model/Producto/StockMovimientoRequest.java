package com.inventario.inventario.model.Producto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StockMovimientoRequest {
    @NotNull(message = "El ID de producto no puede ser nulo")
    private Integer productoId;

    @NotBlank(message = "El tipo de movimiento es requerido")
    @Pattern(regexp = "Entrada|Salida", message = "Tipo de movimiento inválido")
    private String tipoMovimiento;

    @Positive(message = "La cantidad debe ser mayor a cero")
    @Digits(integer = 10, fraction = 2, message = "Formato de cantidad inválido")
    private Double cantidad;

    private String usuarioAccion;
    private String ubicacionEntrada;
    private String ubicacionSalida;
    private String motivoEntrada;
    private String motivoSalida;
    private String responsableEntrada;
    private String responsableSalida;

}
