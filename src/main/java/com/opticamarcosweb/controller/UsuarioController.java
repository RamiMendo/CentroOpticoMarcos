package com.opticamarcosweb.controller;

import com.opticamarcosweb.model.Usuario;
import com.opticamarcosweb.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path = "/", produces = "application/json")
    @Operation(summary = "", description = "", tags = {"Usuarios"})
    public @ResponseBody List<Usuario> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @PostMapping(path = "/", consumes = "application/json")
    @Operation(summary = "", description = "", tags = {"Usuarios"})
    public void addUsuario(@RequestParam Usuario usuario){
        usuarioService.addUsuario(usuario);
    }
}
