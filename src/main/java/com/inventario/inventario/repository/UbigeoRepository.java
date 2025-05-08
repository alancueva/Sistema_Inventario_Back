package com.inventario.inventario.repository;


import com.inventario.inventario.model.Ubigeo.Ubigeo;
import com.inventario.inventario.model.Ubigeo.UbigeoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UbigeoRepository extends JpaRepository<Ubigeo, String> {

    /**
     * Método para mostrara todos los ubigeos.
     *
     * @return UbigeoDTO que contiene la información del ubigeo encontrado.
     */
    @Query(value = "CALL sp_ubigeo_select()", nativeQuery = true)
    List<UbigeoDTO> seleccionarUbigeos();


    /**
     * Método para buscar un ubigeo por su nombre.
     *
     * @param ubigeoNombre Nombre del ubigeo a buscar.
     * @return UbigeoDTO que contiene la información del ubigeo encontrado.
     */
    @Query(value = "CALL sp_ubigeo_busqueda(:ubigeo_nombre)", nativeQuery = true)
    UbigeoDTO BusquedaUbigeo(@Param("ubigeo_nombre") String ubigeoNombre);

}
