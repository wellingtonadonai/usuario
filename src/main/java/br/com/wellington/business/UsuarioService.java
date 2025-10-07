package br.com.wellington.business;

import br.com.wellington.business.converter.UsuarioConverter;
import br.com.wellington.business.dto.UsuarioDto;
import br.com.wellington.infraestructure.entity.Usuario;
import br.com.wellington.infraestructure.exception.ConflitException;
import br.com.wellington.infraestructure.exception.ResourceNotFoundException;
import br.com.wellington.infraestructure.repository.UsuarioRepository;
import br.com.wellington.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioDto salvarUsuario(UsuarioDto usuarioDto){
        emailExistente (usuarioDto.getEmail());
        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDto);
        return usuarioConverter.paraUsuarioDto(usuarioRepository.save(usuario));
    }
    public void emailExistente(String email){
        try {
            boolean exist = verificaEmailExistente(email);
            if (exist){
                throw new ConflitException("email ja cadastrado" + email);
            }
        }catch (ConflitException e){
            throw new ConflitException("email já cadastrado" + e.getCause());


            }

        }
    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }
    public Usuario buscaUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Email não encontrado" + email));
    }
    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
    public UsuarioDto atualizaDadosUsuario(String token, UsuarioDto dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null );
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("email não localizado"));
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

        return usuarioConverter.paraUsuarioDto(usuarioRepository.save(usuario));

    }

}
