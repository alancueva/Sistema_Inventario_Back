package com.inventario.inventario.model.Usuario;


import lombok.Data;

@Data
public class UsuarioI {
    private int role_id;
    private String dni;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String telefono;
    private String correo;
    private String clave;
    private String vigencia;
    private String usuario_creacion;
}
