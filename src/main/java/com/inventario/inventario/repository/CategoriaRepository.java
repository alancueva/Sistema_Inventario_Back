package com.inventario.inventario.repository;

import com.inventario.inventario.model.Categoria.CategoriaDTO;
import com.inventario.inventario.model.Categoria.ConsultaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaDTO, Integer> {

    @Query(value = "CALL sp_categoria_mostrar_nombres_descripcion()", nativeQuery = true)
    List<ConsultaCategoria> MostrarNombreCategia();

    @Query(value = "CALL sp_categoria_consultar_categoria(:descripcion)", nativeQuery = true)
    List<ConsultaCategoria> ConsultarCategoria(
            @Param("descripcion") String descripcion
    );

    @Query(value = "CALL sp_categoria_recuperar_categoria(:id)", nativeQuery = true)
    CategoriaDTO RecuperaCategoria(@Param("id") int id );

    @Procedure(name = "sp_categoria_insertar_categoria")
    void InsertarCategoria(String nombreCategoria, String vigencia, String descripcion, String usuarioCreacion);

    @Procedure(name = "sp_categoria_modificar_categoria")
    void actualizarCategoria(int idCategoria, String nombreCategoria, String vigencia,String descripcion, String usuarioModificacion);
}
