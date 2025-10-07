package br.com.wellington.business.converter;

import br.com.wellington.business.dto.EnderecoDto;
import br.com.wellington.business.dto.TelefoneDto;
import br.com.wellington.business.dto.UsuarioDto;
import br.com.wellington.infraestructure.entity.Endereco;
import br.com.wellington.infraestructure.entity.Telefone;
import br.com.wellington.infraestructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {
    public Usuario paraUsuario(UsuarioDto usuarioDto){

        return Usuario.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail())
                .senha(usuarioDto.getSenha())
                .enderecos(paraListaEnderceco(usuarioDto.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDto.getTelefone()))
                .build();
    }


    public Endereco paraEndereco(EnderecoDto enderecoDto){
        return Endereco.builder()
                .rua(enderecoDto.getRua())
                .numero(enderecoDto.getNumero())
                .cidade(enderecoDto.getCidade())
                .complemento(enderecoDto.getComplemento())
                .estado(enderecoDto.getEstado())
                .build();
    }
    public Telefone paraTelefone(TelefoneDto telefoneDto){
        return Telefone.builder()
                .numero(telefoneDto.getNumero())
                .ddd(telefoneDto.getDdd())
                .build();
    }
    public List<Endereco> paraListaEnderceco(List<EnderecoDto> enderecoDtos){
        return enderecoDtos.stream().map(this::paraEndereco).toList();

    }
    public List<Telefone> paraListaTelefone(List<TelefoneDto> telefoneDto){
        return telefoneDto.stream().map(this::paraTelefone).toList();
    }

    ////// converter novamente /////

    public UsuarioDto paraUsuarioDto(Usuario usuario){

        return UsuarioDto.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEndercecoDto(usuario.getEnderecos()))
                .telefone(paraListaTelefoneDto(usuario.getTelefones()))
                .build();
    }


    public EnderecoDto paraEnderecoDto(Endereco endereco){
        return EnderecoDto.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .estado(endereco.getEstado())
                .build();
    }
    public TelefoneDto paraTelefoneDto(Telefone telefone){
        return TelefoneDto.builder()
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }
    public List<EnderecoDto> paraListaEndercecoDto(List<Endereco> endereco){
        return endereco.stream().map(this::paraEnderecoDto).toList();

    }
    public List<TelefoneDto> paraListaTelefoneDto(List<Telefone> telefone){
        return telefone.stream().map(this::paraTelefoneDto).toList();
    }
    public Usuario updateUsuario(UsuarioDto usuarioDto, Usuario entity){
        return Usuario.builder()
                .nome(usuarioDto.getNome()!= null ? usuarioDto.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(usuarioDto.getSenha() != null ? usuarioDto.getSenha() : entity.getSenha())
                .email(usuarioDto.getEmail() != null ? usuarioDto.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

}
