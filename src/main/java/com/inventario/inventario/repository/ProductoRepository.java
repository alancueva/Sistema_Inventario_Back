package com.inventario.inventario.repository;

import com.inventario.inventario.model.Producto.ConsultaProducto;
import com.inventario.inventario.model.Producto.ProductoDTO;
import com.inventario.inventario.model.Producto.ProductoI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query(value = "CALL sp_producto_stock_producto("
            + ":p_id_producto, :p_tipo_movimiento, :p_cantidad, "
            + ":p_usuario_salida, :p_ubi_entrada, :p_ubi_salida, "
            + ":p_motivo_entrada, :p_motivo_salida, "
            + ":p_res_entrada, :p_res_salida)", nativeQuery = true)
    void procesarMovimientoStock(
            @Param("p_id_producto") int productoId,
            @Param("p_tipo_movimiento") String tipoMovimiento,
            @Param("p_cantidad") double cantidad,
            @Param("p_usuario_salida") String usuarioAccion,
            @Param("p_ubi_entrada") String ubicacionEntrada,
            @Param("p_ubi_salida") String ubicacionSalida,
            @Param("p_motivo_entrada") String motivoEntrada,
            @Param("p_motivo_salida") String motivoSalida,
            @Param("p_res_entrada") String responsableEntrada,
            @Param("p_res_salida") String responsableSalida
    );


    @Modifying
    @Query(value = "CALL sp_producto_insert("
            + ":p_id_proveedor, :p_id_categoria, :p_id_unidadmedida, "
            + ":p_numero_serie, :p_nombre, :p_descripcion, "
            + ":p_fecha_adquisicion, :p_fecha_vencimiento, :p_marca, "
            + ":p_modelo, :p_estado, :p_movimiento, :p_precio, :p_peso, "
            + ":p_cantidad_entrada, :p_cantidad_salida, :p_usuario_creacion)",
            nativeQuery = true)
    void insertProducto(
            @Param("p_id_proveedor") int idProveedor,
            @Param("p_id_categoria") int idCategoria,
            @Param("p_id_unidadmedida") int idUnidadMedida,
            @Param("p_numero_serie") String numeroSerie,
            @Param("p_nombre") String nombre,
            @Param("p_descripcion") String descripcion,
            @Param("p_fecha_adquisicion") String fechaAdquisicion,
            @Param("p_fecha_vencimiento") String fechaVencimiento,
            @Param("p_marca") String marca,
            @Param("p_modelo") String modelo,
            @Param("p_estado") String estado,
            @Param("p_movimiento") String movimiento,
            @Param("p_precio") double precio,
            @Param("p_peso") double peso,
            @Param("p_cantidad_entrada") double cantidadEntrada,
            @Param("p_cantidad_salida") double cantidadSalida,
            @Param("p_usuario_creacion") String usuarioCreacion);

    @Modifying
    @Query(value = "CALL sp_producto_update("
            + ":p_id_producto, :p_id_proveedor, :p_id_categoria, "
            + ":p_id_unidadmedida, :p_numero_serie, :p_nombre, "
            + ":p_descripcion,:p_fecha_adquisicion, :p_fecha_vencimiento, :p_marca, "
            + ":p_modelo, :p_estado, :p_movimiento, :p_precio, :p_peso, "
            + ":p_cantidad_entrada, :p_cantidad_salida,:p_cantidad_final, :p_vigencia , :p_usuario_modificacion)",
            nativeQuery = true)
    void updateProducto(
            @Param("p_id_producto") int idProducto,
            @Param("p_id_proveedor") int idProveedor,
            @Param("p_id_categoria") int idCategoria,
            @Param("p_id_unidadmedida") int idUnidadMedida,
            @Param("p_numero_serie") String numeroSerie,
            @Param("p_nombre") String nombre,
            @Param("p_descripcion") String descripcion,
            @Param("p_fecha_adquisicion") String fechaAdquisicion,
            @Param("p_fecha_vencimiento") String fechaVencimiento,
            @Param("p_marca") String marca,
            @Param("p_modelo") String modelo,
            @Param("p_estado") String estado,
            @Param("p_movimiento") String movimiento,
            @Param("p_precio") double precio,
            @Param("p_peso") double peso,
            @Param("p_cantidad_entrada") double cantidadEntrada,
            @Param("p_cantidad_salida") double cantidadSalida,
            @Param("p_cantidad_final") double cantidadFinal,
            @Param("p_vigencia") String vigencia,
            @Param("p_usuario_modificacion") String usuarioModificacion);




}
