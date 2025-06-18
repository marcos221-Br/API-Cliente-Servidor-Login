package api.tcs.login.consumers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import api.tcs.login.dtos.models.EmailDto;

@FeignClient(url = "http://localhost:20002/api/emails", name="emails")
public interface EmailClient {
    
    @PostMapping
    ResponseEntity<?> sendEmail(@RequestHeader("Authorization") String token, @RequestBody EmailDto emailDto);

    @PostMapping("/{id}")
    ResponseEntity<?> sendRascunho(@RequestHeader("Authorization") String token, @PathVariable Integer id);

    @GetMapping("/{id}")
    ResponseEntity<?> getEmail(@RequestHeader("Authorization") String token, @PathVariable Integer id);

    @GetMapping
    ResponseEntity<?> getAllEmails(@RequestHeader("Authorization") String token);
}
