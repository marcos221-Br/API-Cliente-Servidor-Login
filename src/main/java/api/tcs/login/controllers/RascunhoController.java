package api.tcs.login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.tcs.login.consumers.RascunhoClient;
import api.tcs.login.dtos.models.RascunhoDto;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rascunhos")
public class RascunhoController {

    @Autowired
    private RascunhoClient rascunhoClient;

    @PostMapping
    public ResponseEntity<?> createRascunho(@RequestBody RascunhoDto rascunhoDto, HttpServletRequest request){
        try{
            return this.rascunhoClient.createRascunho(request.getHeader("Authorization"), rascunhoDto);
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.valueOf(e.status());
            String body = e.contentUTF8();
            return ResponseEntity.status(status).body(body);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateRascunho(@RequestBody RascunhoDto rascunhoDto, HttpServletRequest request){
        try{
            return this.rascunhoClient.updateRascunho(request.getHeader("Authorization"), rascunhoDto);
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.valueOf(e.status());
            String body = e.contentUTF8();
            return ResponseEntity.status(status).body(body);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRascunhosUsuario(HttpServletRequest request){
        try{
            return this.rascunhoClient.getAllRascunhosUsuario(request.getHeader("Authorization"));
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.valueOf(e.status());
            String body = e.contentUTF8();
            return ResponseEntity.status(status).body(body);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRascunho(@PathVariable Integer id, HttpServletRequest request){
        try{
            return this.rascunhoClient.getRascunho(request.getHeader("Authorization"), id);
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.valueOf(e.status());
            String body = e.contentUTF8();
            return ResponseEntity.status(status).body(body);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRascunho(@PathVariable Integer id, HttpServletRequest request){
        try{
            return this.rascunhoClient.deleteRascunho(request.getHeader("Authorization"), id);
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.valueOf(e.status());
            String body = e.contentUTF8();
            return ResponseEntity.status(status).body(body);
        }
    }
}
