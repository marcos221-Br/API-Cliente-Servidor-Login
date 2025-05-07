package api.tcs.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.tcs.login.dtos.mappers.UsuarioMapper;
import api.tcs.login.dtos.models.UsuarioDto;
import api.tcs.login.exceptions.DuplicateEntryException;
import api.tcs.login.models.Usuario;
import api.tcs.login.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    private PasswordEncoder passwordEncoder;

    private Usuario usuario;

    public UsuarioService(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public UsuarioDto createUsuario(UsuarioDto usuarioDto){
        this.usuario = usuarioMapper.usuarioDtoToUsuario(usuarioDto);
        this.usuario.setSenha(passwordEncoder.encode(this.usuario.getPassword()));
        try{
            return usuarioMapper.usuarioToUsuarioDto(this.usuarioRepository.save(usuario));
        }catch(DataIntegrityViolationException e){
            throw new DuplicateEntryException("Usuarioname already registered!");
        }
    }

    public UsuarioDto getUsuarioByEmail(String email){
        return usuarioMapper.usuarioToUsuarioDto(this.usuarioRepository.findByEmail(email).get());
    }

    public UsuarioDto updateUsuario(Integer id, UsuarioDto usuarioDto, String email){
        this.usuario = usuarioMapper.usuarioDtoToUsuario(usuarioDto);
        this.usuario.setId(id);
        this.usuario.setEmail(email);
        this.usuario.setSenha(passwordEncoder.encode(this.usuario.getPassword()));
        return usuarioMapper.usuarioToUsuarioDto(this.usuarioRepository.save(usuario));
    }

    public void deleteUsuario(String email){
        this.usuario = this.usuarioRepository.findByEmail(email).get();
        this.usuario.setEnabled(false);
        this.usuarioRepository.save(this.usuario);
    }
}
