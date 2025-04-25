package com.inventario.inventario.Controller;


import com.inventario.inventario.model.Producto.*;
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

    @Operation (summary = "Buscar productos por filtros",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @PostMapping("/buscar-producto")
    public ResponseEntity<List<ProductoDTO>> buscarProductos(@RequestBody ProductoB productoB){
        List<ProductoDTO> productolstList = productoService.buscarProductos(
                productoB.getNumeroSerie(),
                productoB.getNombre(),
                productoB.getDescripcion(),
                productoB.getEstado());

        return ResponseEntity.ok(productolstList);
    }


    @Operation(summary = "Obtener todos los productos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @GetMapping("/seleccionar")
    public List<ProductoDTO> seleccionarProductos() {
        return productoService.obtenerProductos();
    }


    @Operation (summary = "Recuperar producto por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto recuperado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
                    @ApiResponse(responseCode = "400", description = "Parámetro inválido")
            }
    )
    @GetMapping("/recuperar_producto/{id_producto}")
    public ResponseEntity<ProductoDTO> RecuperarProducto(@PathVariable int id_producto){
        ProductoDTO pdto = productoService.RecuperarProducto(id_producto);
        return ResponseEntity.ok(pdto);
    }

    @Operation (summary = "Actualizar Stock de producto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto insertado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Error al procesar la operación")
            }
    )
    @PostMapping("/movimiento_stock")
    public ResponseEntity<?> actualizarStock(@RequestBody StockMovimientoRequest request) {
        try {
            productoService.procesarMovimientoStock(request);
            return ResponseEntity.ok().build();
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
