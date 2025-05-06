package com.inventario.inventario.service;


import com.inventario.inventario.model.Usuario.UsuarioDTO;
import com.inventario.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> buscarUsuario(
            String dni,
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            String estado
    ) {
        try {
            return usuarioRepository.buscarUsuario(dni, nombre, apellidoPaterno, apellidoMaterno, estado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UsuarioDTO recuperarUsuario(int id_usuario) {
        try {
            return usuarioRepository.recuperarUsuario(id_usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void insertUsuario(
            int roleId,
            String dni,
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correo,
            String clave,
            String vigencia,
            String usuarioCreacion
    ) {
        try {
            usuarioRepository.insertUsuario(roleId, dni, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, clave, vigencia, usuarioCreacion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void updateUsuario(
            int id_usuario,
            int roleId,
            String dni,
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono,
            String correo,
            String clave,
            String vigencia,
            String usuarioModificacion
    ) {
        try {
            usuarioRepository.updateUsuario(id_usuario, roleId, dni, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, clave, vigencia, usuarioModificacion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
