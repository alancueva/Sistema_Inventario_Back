package com.inventario.inventario.Controller;

import com.inventario.inventario.model.Usuario.*;
import com.inventario.inventario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/buscar-usuario")
    @Operation(summary = "Buscar usuarios por filtros",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public ResponseEntity<List<UsuarioDTO>> buscarUsuario(@RequestBody UsuarioC usuarioC) {
        try {
        List<UsuarioDTO> usuarioList = usuarioService.buscarUsuario(
                usuarioC.getDni(),
                usuarioC.getNombre(),
                usuarioC.getApellido_paterno(),
                usuarioC.getApellido_materno(),
                usuarioC.getVigencia()
        );

        return ResponseEntity.ok(usuarioList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/recuperar-usuario/{id_usuario}")
    public ResponseEntity<UsuarioLst> recuperarUsuario(@PathVariable int id_usuario) {
        try {
            UsuarioLst usuario = usuarioService.recuperarUsuario(id_usuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/insetar-usuario")
    public ResponseEntity<Void> insertUsuario(@RequestBody UsuarioI usuarioI) {
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
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/modificar-usuario")
    public ResponseEntity<Void> updateUsuario(@RequestBody UsuarioU usuarioU) {
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
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
