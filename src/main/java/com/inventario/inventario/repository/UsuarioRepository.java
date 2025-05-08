package com.inventario.inventario.repository;

import com.inventario.inventario.model.Usuario.UsuarioDTO;
import com.inventario.inventario.model.Usuario.UsuarioLst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDTO, Integer> {

    @Query(value = "CALL sp_usuario_buscar_usuario(:u_dni, " +
            ":u_nombre, :u_apellido_paterno, " +
            ":u_apellido_materno, :u_vigencia)", nativeQuery = true)
    List<UsuarioDTO> buscarUsuario(
            @Param("u_dni") String dni,
            @Param("u_nombre") String nombre,
            @Param("u_apellido_paterno") String apellidoPaterno,
            @Param("u_apellido_materno") String apellidoMaterno,
            @Param("u_vigencia") String estado
    );


    @Query(value = "CALL sp_usuario_recuperar_usuario(:u_id_usuario)", nativeQuery = true)
    UsuarioLst recuperarUsuario(@Param("u_id_usuario") int id_usuario);

    @Query(value = "CALL sp_usuario_insertar_usuario(" +
            ":u_role_id, " +
            ":u_dni, :u_nombre, :u_apellido_paterno, " +
            ":u_apellido_materno, :u_telefono, " +
            ":u_correo, :u_clave, :u_vigencia, " +
            ":u_usuario_creacion)", nativeQuery = true)
    void insertUsuario(
            @Param("u_role_id") int roleId,
            @Param("u_dni") String dni,
            @Param("u_nombre") String nombre,
            @Param("u_apellido_paterno") String apellidoPaterno,
            @Param("u_apellido_materno") String apellidoMaterno,
            @Param("u_telefono") String telefono,
            @Param("u_correo") String correo,
            @Param("u_clave") String clave,
            @Param("u_vigencia") String vigencia,
            @Param("u_usuario_creacion") String usuarioCreacion
    );

    @Modifying
    @Transactional
    @Query(value = "CALL sp_usuario_modificar_usuario(" +
            ":u_id_usuario, :u_role_id, " +
            ":u_dni, :u_nombre, :u_apellido_paterno, " +
            ":u_apellido_materno, :u_telefono, " +
            ":u_correo, :u_clave, :u_vigencia, " +
            ":u_usuario_modificacion)", nativeQuery = true)
    void updateUsuario(
            @Param("u_id_usuario") int id_usuario,
            @Param("u_role_id") int roleId,
            @Param("u_dni") String dni,
            @Param("u_nombre") String nombre,
            @Param("u_apellido_paterno") String apellidoPaterno,
            @Param("u_apellido_materno") String apellidoMaterno,
            @Param("u_telefono") String telefono,
            @Param("u_correo") String correo,
            @Param("u_clave") String clave,
            @Param("u_vigencia") String vigencia,
            @Param("u_usuario_modificacion") String usuarioModificacion
    );

}
