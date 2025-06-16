package api.tcs.login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.tcs.login.consumers.EmailClient;
import api.tcs.login.dtos.models.EmailDto;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/emails")
public class EmailController {
    
    @Autowired
    private EmailClient emailClient;

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody EmailDto emailDto, HttpServletRequest request){
        try{
            ResponseEntity<?> response =  this.emailClient.sendEmail(request.getHeader("Authorization"), emailDto);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.valueOf(e.status());
            String body = e.contentUTF8();
            return ResponseEntity.status(status).body(body);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> sendRascunho(@PathVariable Integer id, HttpServletRequest request){
        try{
            ResponseEntity<?> response =  this.emailClient.sendRascunho(request.getHeader("Authorization"), id);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.valueOf(e.status());
            String body = e.contentUTF8();
            return ResponseEntity.status(status).body(body);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> getEmail(@PathVariable Integer id, HttpServletRequest request){
        try{
            ResponseEntity<?> response =  this.emailClient.getEmail(request.getHeader("Authorization"), id);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.valueOf(e.status());
            String body = e.contentUTF8();
            return ResponseEntity.status(status).body(body);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEmails(HttpServletRequest request){
        try{
            ResponseEntity<?> response =  this.emailClient.getAllEmails(request.getHeader("Authorization"));
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.valueOf(e.status());
            String body = e.contentUTF8();
            return ResponseEntity.status(status).body(body);
        }
    }
}
