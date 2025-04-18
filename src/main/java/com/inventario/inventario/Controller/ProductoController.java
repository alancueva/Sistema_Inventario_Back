package com.inventario.inventario.Controller;


import com.inventario.inventario.model.Producto.ConsultaProducto;
import com.inventario.inventario.model.Producto.ProductoDTO;
import com.inventario.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/buscar-producto")
    public List<ProductoDTO> buscarProductos(
            @RequestParam(required = false) String numeroSerie,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) String estado
    ){
        return productoService.buscarProductos(numeroSerie,nombre,descripcion,estado);
    }

    @GetMapping("/seleccionar")
    public List<ProductoDTO> seleccionarProductos() {
        return productoService.obtenerProductos();
    }

    @GetMapping("/recuperar_producto/{id_producto}")
    public ResponseEntity<ProductoDTO> RecuperarProducto(@PathVariable int id_producto){
        ProductoDTO pdto = productoService.RecuperarProducto(id_producto);
        return ResponseEntity.ok(pdto);
    }
}
