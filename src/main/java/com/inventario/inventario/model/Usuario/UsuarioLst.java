package com.inventario.inventario.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLst {
    private int id_usuario;
    private int role_id;
    private String role_nombre;
    private String dni;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String usua_datos;
    private String telefono;
    private String correo;
    private String clave;
    private String vigencia;
    private String usuario_creacion;
    private String fecha_creacion;
    private String usuario_modificacion;
    private String fecha_modificacion;
    private String usuario_baja;
    private String fecha_baja;
}
