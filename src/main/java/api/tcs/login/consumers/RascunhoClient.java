package api.tcs.login.consumers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import api.tcs.login.dtos.models.RascunhoDto;

@FeignClient(url = "http://localhost:20002/api/rascunhos", name="rascunhos")
public interface RascunhoClient {
    
    @PostMapping
    ResponseEntity<?> createRascunho(@RequestHeader("Authorization") String token, @RequestBody RascunhoDto rascunhoDto);

    @PutMapping
    ResponseEntity<?> updateRascunho(@RequestHeader("Authorization") String token, @RequestBody RascunhoDto rascunhoDto);

    @GetMapping
    ResponseEntity<?> getAllRascunhosUsuario(@RequestHeader("Authorization") String token);

    @GetMapping("/{id}")
    ResponseEntity<?> getRascunho(@RequestHeader("Authorization") String token, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRascunho(@RequestHeader("Authorization") String token, @PathVariable Integer id);
}
