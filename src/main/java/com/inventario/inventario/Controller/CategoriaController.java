package com.inventario.inventario.Controller;

import com.inventario.inventario.model.Categoria.CategoriaDTO;
import com.inventario.inventario.model.Categoria.ConsultaCategoria;
import com.inventario.inventario.model.Categoria.InsertarCategoria;
import com.inventario.inventario.model.Categoria.UpdateCategoria;
import com.inventario.inventario.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@Tag(name = "com/inventario/inventario/model/Categoria", description = "API para gestionar Categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/mostrar_Categoria")
    @Operation(summary = "Mostrar Categoria", description = "Devuelve Listado de todas las categorias")
    public List<ConsultaCategoria> mostrarCategoria(){
        return  categoriaService.MostrarNombreCategoria();
    }

    @GetMapping("/consultar_categoria/{descripcion}")
    @Operation(summary = "Consultar Categoria", description = "Devuelve un listado de la busqueda de categoria")
    public List<ConsultaCategoria> consultarCategoria(@PathVariable String descripcion){
        return categoriaService.ConsultarCategoria(descripcion);
    }

    @GetMapping("/recuperar_categoria/{id}")
    @Operation(summary = "Recuperar Categoria", description = "Devuelve un listado de la categoria por el id")
    public ResponseEntity<CategoriaDTO>  recuperarCategoria(@PathVariable int id){
//        return categoriaService.RecuperarCategoria(id);

        CategoriaDTO dto = categoriaService.RecuperarCategoria(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/insertar_categoria")
    public ResponseEntity<String> crearCategoria(@RequestBody InsertarCategoria categoria) {
        categoriaService.insertarCategoria(categoria);
        return ResponseEntity.ok("Categoría creada correctamente");
    }

    @PutMapping("/update_categoria")
    public ResponseEntity<String> actualizarCategoria(@RequestBody UpdateCategoria categoria) {
        categoriaService.updateCategoria(categoria);
        return ResponseEntity.ok("Categoría actualizada correctamente");
    }

}
