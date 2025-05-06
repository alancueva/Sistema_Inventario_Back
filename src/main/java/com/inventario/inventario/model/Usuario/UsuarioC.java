package com.inventario.inventario.model.Usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioC {
    private String dni;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    @NotBlank(message = "Ingrese la Vigencia")
    private String vigencia;
}
