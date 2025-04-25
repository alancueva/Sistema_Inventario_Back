package com.inventario.inventario.Controller;


import com.inventario.inventario.model.Unidad_Medida.UnidadMedida;
import com.inventario.inventario.model.Unidad_Medida.UnidadMedidaDTO;
import com.inventario.inventario.service.UnidadMedidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidadmedida")
public class UnidadMedidaController {

    @Autowired
    private UnidadMedidaService unidadMedidaService;


    @Operation(summary = "Buscar unidades de medida por descripción",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de unidades de medida obtenida exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @GetMapping("/buscar_unidad_medida/{um_descripcion}")
    public ResponseEntity<List<UnidadMedidaDTO>> buscar_unidad_medida(@PathVariable String um_descripcion){
        try {
            if (um_descripcion == null || um_descripcion.isEmpty()) {
                throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
            }
            List<UnidadMedidaDTO> unidadMedidaDTOList = unidadMedidaService.buscarUnidadMedida(um_descripcion);
            if (unidadMedidaDTOList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(unidadMedidaDTOList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Recuperar unidad de medida por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Unidad de medida recuperada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Unidad de medida no encontrada"),
                    @ApiResponse(responseCode = "400", description = "Parámetro inválido")
            }
    )
    @GetMapping("/recuperar_unidad_medida/{um_id_unidadmedida}")
    public ResponseEntity<List<UnidadMedidaDTO>> recuperar_unidad_medida(@PathVariable int um_id_unidadmedida){
        try{
            List<UnidadMedidaDTO> unidadMedidaDTOList = unidadMedidaService.RecuperarUnidadMedida(um_id_unidadmedida);
            if (unidadMedidaDTOList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return  ResponseEntity.ok(unidadMedidaDTOList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Buscar unidades de medida por descripción",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de unidades de medida obtenida exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                    @ApiResponse(responseCode = "404", description = "Unidad de medida no encontrada")
            }
    )
    @GetMapping("/buscar/{um_descripcion}")
    public ResponseEntity<List<UnidadMedidaDTO>> buscarPorDescripcion(@Valid @RequestParam String descripcion) {
        try {
            List<UnidadMedidaDTO> unidades = unidadMedidaService.buscarPorDescripcion(descripcion);
            if (unidades.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(unidades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Insertar unidad de medida",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Unidad de medida creada exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @PostMapping("/insert_unidadMedida")
    public ResponseEntity<Void> createUnidadMedida(@RequestBody UnidadMedidaDTO unidadMedida) {
        try {
            unidadMedida.setIdunidadmedida(null); // Aseguramos que estamos creando uno nuevo
            unidadMedidaService.saveUnidadMedida(unidadMedida);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Actualizar unidad de medida",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            }
    )
    @PutMapping("/actualizar_unidad_medida/{id}")
    public ResponseEntity<Void> updateUnidadMedida( @RequestBody UnidadMedidaDTO unidadMedida) {
        try {
            unidadMedidaService.saveUnidadMedida(unidadMedida);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
