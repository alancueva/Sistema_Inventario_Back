package com.inventario.inventario.repository;


import com.inventario.inventario.model.Producto.ConsultaProducto;
import com.inventario.inventario.model.Unidad_Medida.UnidadMedidaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Unidad_medidaRepository extends JpaRepository<UnidadMedidaDTO, Integer> {

    //Buscar Unidades de Medidas
    @Query(value = "CALL sp_unidad_medida_buscar_descripcion(:um_descripcion)", nativeQuery = true)
    List<UnidadMedidaDTO> buscarUnidadMedida(@Param("um_descripcion") String um_descripcion);

    //Recuparar Unidades de Medidas
    @Query(value = "CALL sp_unidad_medida_recuperar(:um_id_unidadmedida)", nativeQuery = true)
    List<UnidadMedidaDTO> RecuperarUnidadMedida(@Param("um_id_unidadmedida") int um_id_unidadmedida);

    // Insertar Unidades de Medidas
    @Query(value = "CALL sp_unidad_medida_insert(:um_descripcion, :um_simbolo)", nativeQuery = true)
    void insertarUnidadMedida(@Param("um_descripcion") String descripcion, @Param("um_simbolo") String simbolo);

    // Actualizar Unidades de Medidas
    @Query(value = "CALL sp_unidad_medida_update(:um_id_unidadmedida, :um_descripcion, :um_simbolo)", nativeQuery = true)
    void actualizarUnidadMedida(@Param("um_id_unidadmedida") int idUnidadmedida,
                                @Param("um_descripcion") String descripcion,
                                @Param("um_simbolo") String simbolo);
}
