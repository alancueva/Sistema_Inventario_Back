package com.inventario.inventario.Controller;


import com.inventario.inventario.model.Ubigeo.UbigeoDTO;
import com.inventario.inventario.service.UbigeoService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubigeo")
public class UbigeoController {

    @Autowired
    private UbigeoService ubigeoService;


    /**
     * * Endpoint para obtener todos los ubigeos.
     * @return Lista de ubigeos.
     */
     @GetMapping("/mostrar_ubigeos")
     @Operation(summary = "Obtener todos los ubigeos",
             responses = {
                     @ApiResponse(responseCode = "200", description = "Lista de ubigeos obtenida exitosamente"),
                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
             }
     )
    public ResponseEntity<List<UbigeoDTO>> obtenerUbigeos() {
        List<UbigeoDTO> ubigeos = ubigeoService.obtenerUbigeos();
        return ResponseEntity.ok(ubigeos);
    }

    /**
     * * Endpoint para buscar un ubigeo por su nombre.
     * return UbigeoDTO que contiene la información del ubigeo encontrado.
     */
     @GetMapping("/buscar_ubigeo/{ubigeoNombre}")
     @Operation(summary = "Buscar ubigeo por nombre",
             responses = {
                     @ApiResponse(responseCode = "200", description = "Ubigeo encontrado"),
                     @ApiResponse(responseCode = "404", description = "Ubigeo no encontrado"),
                     @ApiResponse(responseCode = "400", description = "Parámetro inválido")
             }
     )
     public ResponseEntity<UbigeoDTO> buscarUbigeo(
             //@Parameter(description = "Nombre del ubigeo a buscar", required = true)
             @PathVariable String ubigeoNombre) {
         UbigeoDTO ubigeo = ubigeoService.buscarUbigeo(ubigeoNombre);
        return ResponseEntity.ok(ubigeo);
    }

}
