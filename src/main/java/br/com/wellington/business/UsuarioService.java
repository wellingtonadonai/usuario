package br.com.wellington.business;

import br.com.wellington.business.converter.UsuarioConverter;
import br.com.wellington.business.dto.UsuarioDto;
import br.com.wellington.infraestructure.entity.Usuario;
import br.com.wellington.infraestructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDto salvarUsuario(UsuarioDto usuarioDto){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDto);
        return usuarioConverter.paraUsuarioDto(usuarioRepository.save(usuario));
    }
}
