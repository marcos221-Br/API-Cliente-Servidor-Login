package api.tcs.login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import api.tcs.login.dtos.LoginDto;
import api.tcs.login.dtos.LoginResponseDto;
import api.tcs.login.models.JsonMessage;
import api.tcs.login.models.Usuario;
import api.tcs.login.services.AuthenticationService;
import api.tcs.login.services.JwtService;
import jakarta.validation.Valid;

@RestController
public class AuthenticationController {
    
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody @Valid LoginDto loginDto) {
        Usuario authenticatedUsuario = authenticationService.authenticate(loginDto.usuarioObject());
        var token = jwtService.generateToken(authenticatedUsuario);
        jwtService.whitelistToken(token);
        return LoginResponseDto.loginDto(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader){
        jwtService.removeWhitelistToken(authorizationHeader.substring(7));
        return ResponseEntity.ok().body(new JsonMessage("Logout realizado com sucesso"));
    }

    @GetMapping("/logged")
    public ResponseEntity<?> logged(){
        return ResponseEntity.ok().body(new JsonMessage(jwtService.getLoggedUsers()));
    }
}
