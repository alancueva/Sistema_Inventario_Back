package com.inventario.inventario.Controller;


import com.inventario.inventario.model.Producto.StockMovimientoRequest;
import com.inventario.inventory.exception.StockOperationException;
import com.inventario.inventario.model.Producto.ProductoDTO;
import com.inventario.inventario.model.Producto.ProductoI;
import com.inventario.inventario.model.Producto.ProductoU;
import com.inventario.inventario.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/buscar-producto")
    @Operation (summary = "Buscar productos por filtros",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public List<ProductoDTO> buscarProductos(
            @RequestParam(required = false) String numeroSerie,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) String estado
    ){
        return productoService.buscarProductos(numeroSerie,nombre,descripcion,estado);
    }

    @GetMapping("/seleccionar")
    @Operation(summary = "Obtener todos los productos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public List<ProductoDTO> seleccionarProductos() {
        return productoService.obtenerProductos();
    }

    @GetMapping("/recuperar_producto/{id_producto}")
    @Operation (summary = "Recuperar producto por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto recuperado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
                    @ApiResponse(responseCode = "400", description = "Parámetro inválido")
            }
    )
    public ResponseEntity<ProductoDTO> RecuperarProducto(@PathVariable int id_producto){
        ProductoDTO pdto = productoService.RecuperarProducto(id_producto);
        return ResponseEntity.ok(pdto);
    }

    @PostMapping("/movimiento_stock")
    @Operation (summary = "Actualizar Stock de producto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto insertado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Error al procesar la operación")
            }
    )
    public ResponseEntity<?> actualizarStock(@RequestBody StockMovimientoRequest request) {
        try {
            productoService.procesarMovimientoStock(request);
            return ResponseEntity.ok();
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/insertar_producto")
    @Operation (summary = "Insertar producto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto insertado exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<Void> crearProducto(@RequestBody ProductoI producto) {
        productoService.createProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update_producto")
    @Operation (summary = "Actualizar producto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<Void> actualizarProducto(@RequestBody ProductoU producto) {
        productoService.updateProducto(producto);
        return ResponseEntity.ok().build();
    }
}
