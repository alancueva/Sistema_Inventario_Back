package com.inventario.inventario.service;

import com.inventario.inventario.model.Producto.ConsultaProducto;
import com.inventario.inventario.model.Producto.ProductoDTO;
import com.inventario.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ProductoDTO RecuperarProducto(int id_producto){
        return productoRepository.RecuperarRroducto(id_producto);
    }
}
