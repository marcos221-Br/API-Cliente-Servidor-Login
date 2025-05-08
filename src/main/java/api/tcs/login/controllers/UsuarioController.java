package api.tcs.login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.tcs.login.dtos.UsuarioResponseDto;
import api.tcs.login.dtos.models.UsuarioDto;
import api.tcs.login.models.JsonMessage;
import api.tcs.login.models.Usuario;
import api.tcs.login.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        this.usuarioService.createUsuario(usuarioDto);
        return ResponseEntity.ok().body(new JsonMessage("Sucesso ao cadastrar usuario"));
    }

    @GetMapping
    public ResponseEntity<?> getUsuario(Authentication authentication){
        return ResponseEntity.ok().body(new UsuarioResponseDto("Sucesso ao buscar usuario", this.usuarioService.getUsuarioByEmail(((Usuario)authentication.getPrincipal()).getEmail())));
    }

    @PutMapping
    public UsuarioDto updateUsuario(Authentication authentication, @RequestBody @Valid UsuarioDto usuarioDto){
        return this.usuarioService.updateUsuario(((Usuario)authentication.getPrincipal()).getId(), usuarioDto, ((Usuario)authentication.getPrincipal()).getEmail());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUsuario(Authentication authentication){
        this.usuarioService.deleteUsuario(((Usuario)authentication.getPrincipal()).getEmail());
        return ResponseEntity.ok().body(new JsonMessage("Sucesso ao excluir o usuario"));
    }
}
