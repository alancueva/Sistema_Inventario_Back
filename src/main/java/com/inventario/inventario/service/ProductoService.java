package com.inventario.inventario.service;

import com.inventario.inventario.model.Producto.*;
import com.inventario.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.ErrorResponseException;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoDTO> buscarProductos(String numeroSerie, String nombre, String descripcion, String estado) {

        return productoRepository.buscarProducto(numeroSerie, nombre, descripcion, estado);
    }

    public List<ProductoDTO> obtenerProductos() {
        return productoRepository.seleccionarProductos();
    }

    @Transactional
    public ProductoDTO RecuperarProducto(int id_producto){
        return productoRepository.RecuperarRroducto(id_producto);
    }

    @Transactional
    public void createProducto(ProductoI producto) {
        try {
            productoRepository.insertProducto(
                    producto.getId_Proveedores(),
                    producto.getId_Categoria(),
                    producto.getId_unidadmedida(),
                    producto.getNumero_serie(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getFecha_adquisicion(),
                    producto.getFecha_vencimiento(),
                    producto.getMarca(),
                    producto.getModelo(),
                    producto.getEstado(),
                    producto.getMovimiento(),
                    producto.getPrecio(),
                    producto.getPeso(),
                    producto.getCantidad_entrada(),
                    producto.getCantidad_salida(),
                    producto.getUsuario_creacion()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error en transacción de base de datos", e);
        }
    }

    @Transactional
    public void procesarMovimientoStock(StockMovimientoRequest request) {
        try {
            productoRepository.procesarMovimientoStock (
                    request.getProductoId(),
                    request.getTipoMovimiento(),
                    request.getCantidad(),
                    request.getUsuarioAccion(),
                    request.getUbicacionEntrada(),
                    request.getUbicacionSalida(),
                    request.getMotivoEntrada(),
                    request.getMotivoSalida(),
                    request.getResponsableEntrada(),
                    request.getResponsableSalida()
            );

        } catch (Exception ex) {
            String errorMessage = "Error al procesar la operación de stock";

            if (ex.getCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) ex.getCause();
                if (sqlEx.getSQLState().equals("45000")) {
                    errorMessage = sqlEx.getMessage();
                }
            }

            throw new RuntimeException(errorMessage, ex);
        }
    }

    @Transactional
    public void updateProducto(ProductoU producto) {
        try {
            productoRepository.updateProducto(
                    producto.getId_producto(),
                    producto.getId_Proveedores(),
                    producto.getId_Categoria(),
                    producto.getId_unidadmedida(),
                    producto.getNumero_serie(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getFecha_adquisicion(),
                    producto.getFecha_vencimiento(),
                    producto.getMarca(),
                    producto.getModelo(),
                    producto.getEstado(),
                    producto.getMovimiento(),
                    producto.getPrecio(),
                    producto.getPeso(),
                    producto.getCantidad_entrada(),
                    producto.getCantidad_salida(),
                    producto.getCantidad_final(),
                    producto.getVigencia(),
                    producto.getUsuario_modificacion()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Error en transacción de base de datos", e);
        }
    }
}
