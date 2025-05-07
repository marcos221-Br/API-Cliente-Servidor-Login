package api.tcs.login.dtos.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import api.tcs.login.dtos.models.UsuarioDto;
import api.tcs.login.models.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    @Mapping(target = "senha", ignore = true)
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    @Mapping(target = "senha", ignore = true)
    List<UsuarioDto> usuariosToUsuarioDtos(List<Usuario> usuarios);
}
