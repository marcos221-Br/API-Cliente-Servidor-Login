package api.tcs.login.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.tcs.login.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    
    public Optional<Usuario> findByEmail(String email);
}
