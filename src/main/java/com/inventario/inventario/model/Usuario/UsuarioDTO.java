package com.inventario.inventario.model.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioDTO {
    @Id
    @Column(name = "id_usuario")
    private int id_usuario;
    @Column(name = "role_id")
    private int role_id;
    @Column(name = "dni")
    private String dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido_paterno")
    private String apellido_paterno;
    @Column(name = "apellido_materno")
    private String apellido_materno;
    @Column(name = "usua_datos")
    private String usua_datos;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correo")
    private String correo;
    @Column(name = "clave")
    private String clave;
    @Column(name = "vigencia")
    private String vigencia;
    @Column(name = "usuario_creacion")
    private String usuario_creacion;
    @Column(name = "fecha_creacion")
    private String fecha_creacion;
    @Column(name = "usuario_modificacion")
    private String usuario_modificacion;
    @Column(name = "fecha_modificacion")
    private String fecha_modificacion;
    @Column(name = "usuario_baja")
    private String usuario_baja;
    @Column(name = "fecha_baja")
    private String fecha_baja;

}
