package com.inventario.inventario.repository;

import com.inventario.inventario.model.Producto.ConsultaProducto;
import com.inventario.inventario.model.Producto.ProductoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface ProductoRepository extends JpaRepository<ConsultaProducto, Integer> {

    @Transactional(readOnly = true)
    @Query(value = "CALL sp_producto_buscar_producto(:p_NumeroSerie, :p_Nombre, :p_Descripcion, :p_Estado)", nativeQuery = true)
    List<ProductoDTO> buscarProducto(
            @Param("p_NumeroSerie") String numeroSerie,
            @Param("p_Nombre") String nombre,
            @Param("p_Descripcion") String descripcion,
            @Param("p_Estado") String estado
    );

    @Query(value = "CALL sp_select_producto()", nativeQuery = true)
    List<ProductoDTO> seleccionarProductos();

    @Query(value = "CALL sp_producto_recuperar_producto(:p_id_producto)", nativeQuery = true)
    ProductoDTO RecuperarRroducto(@Param("p_id_producto") int id_producto);
}
