package com.inventario.inventario.Controller;

import com.inventario.inventario.model.Usuario.*;
import com.inventario.inventario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.inventario.inventario.utils.ApiResponse;
import com.inventario.inventario.utils.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/buscar-usuario")
//    @Operation(summary = "Buscar usuarios por filtros",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
//                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
//                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
//            }
//    )
    public ResponseEntity<?> buscarUsuario(@RequestBody UsuarioC usuarioC) {
        try {
            List<UsuarioLstB> usuarioList = usuarioService.buscarUsuario(
                    usuarioC.getDni(),
                    usuarioC.getNombre(),
                    usuarioC.getApellido_paterno(),
                    usuarioC.getApellido_materno(),
                    usuarioC.getVigencia()
            );

            if (usuarioList == null || usuarioList.isEmpty()) {
                ApiError error = new ApiError(404, "No se encontraron usuarios", LocalDateTime.now().toString());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            } else {
                ApiResponse<List<UsuarioLstB>> response = new ApiResponse<>(true, "Usuarios encontrados", usuarioList);
                return new ResponseEntity<>(response, HttpStatus.OK);

            }
        }  catch (IllegalArgumentException e) {
            ApiError error = new ApiError(400, e.getMessage(), LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        catch (Exception e) {
            ApiError error = new ApiError(500, "Error interno del servidor", LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/recuperar-usuario/{id_usuario}")
    public ResponseEntity<?> recuperarUsuario(@PathVariable int id_usuario) {
        try {
            UsuarioLst usuario = usuarioService.recuperarUsuario(id_usuario);
            if(usuario == null) {
                ApiError error = new ApiError(404, "Usuario no encontrado", LocalDateTime.now().toString());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            ApiResponse<UsuarioLst> response = new ApiResponse<>(true, "Usuarios encontrados", usuario);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ApiError error = new ApiError(400, e.getMessage(), LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        catch (Exception e) {
            ApiError error = new ApiError(500, "Error interno del servidor", LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/insetar-usuario")
    public ResponseEntity<?> insertUsuario(@RequestBody UsuarioI usuarioI) {
        try {
            usuarioService.insertUsuario(
                    usuarioI.getRole_id(),
                    usuarioI.getDni(),
                    usuarioI.getNombre(),
                    usuarioI.getApellido_paterno(),
                    usuarioI.getApellido_materno(),
                    usuarioI.getTelefono(),
                    usuarioI.getCorreo(),
                    usuarioI.getClave(),
                    usuarioI.getVigencia(),
                    usuarioI.getUsuario_creacion());
            ApiResponse<String> response = new ApiResponse<>(true, "Usuario creado exitosamente", null);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ApiError error = new ApiError(400, e.getMessage(), LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApiError error = new ApiError(500, "Error al insertar usuario", LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/modificar-usuario")
    public ResponseEntity<?> updateUsuario(@RequestBody UsuarioU usuarioU) {
        try {
            usuarioService.updateUsuario(
                    usuarioU.getId_usuario(),
                    usuarioU.getRole_id(),
                    usuarioU.getDni(),
                    usuarioU.getNombre(),
                    usuarioU.getApellido_paterno(),
                    usuarioU.getApellido_materno(),
                    usuarioU.getTelefono(),
                    usuarioU.getCorreo(),
                    usuarioU.getClave(),
                    usuarioU.getVigencia(),
                    usuarioU.getUsuario_modificacion());
            ApiResponse<String> response = new ApiResponse<>(true, "Usuario modificado exitosamente", null);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ApiError error = new ApiError(400, e.getMessage(), LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApiError error = new ApiError(500, "Error al modificar usuario", LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
