package com.inventario.inventario.Controller;


import com.inventario.inventario.model.Unidad_Medida.UnidadMedida;
import com.inventario.inventario.model.Unidad_Medida.UnidadMedidaDTO;
import com.inventario.inventario.service.UnidadMedidaService;
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


    @GetMapping("/buscar_unidad_medida/{um_descripcion}")
    public List<UnidadMedidaDTO> buscar_unidad_medida(@PathVariable String um_descripcion){
        return unidadMedidaService.buscarUnidadMedida(um_descripcion);
    }

    @GetMapping("/recuperar_unidad_medida/{um_id_unidadmedida}")
    public List<UnidadMedidaDTO> recuperar_unidad_medida(@PathVariable int um_id_unidadmedida){
        return unidadMedidaService.RecuperarUnidadMedida(um_id_unidadmedida);
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<UnidadMedidaDTO> getUnidadMedidaById(@PathVariable Integer id) {
        try {
            UnidadMedidaDTO unidadMedida = unidadMedidaService.getUnidadMedidaById(id);
            if (unidadMedida == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(unidadMedida);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar unidades de medida por descripción
    @GetMapping("/buscar/{um_descripcion}")
    public ResponseEntity<List<UnidadMedidaDTO>> buscarPorDescripcion(@RequestParam String descripcion) {
        try {
            List<UnidadMedidaDTO> unidades = unidadMedidaService.buscarPorDescripcion(descripcion);
            return ResponseEntity.ok(unidades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


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

    @PutMapping("/actualizar_unidad_medida/{id}")
    public ResponseEntity<Void> updateUnidadMedida(@PathVariable Integer id, @RequestBody UnidadMedidaDTO unidadMedida) {
        try {
            unidadMedida.setIdunidadmedida(id);
            unidadMedidaService.saveUnidadMedida(unidadMedida);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
