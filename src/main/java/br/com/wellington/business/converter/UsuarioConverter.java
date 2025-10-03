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
    public List<Telefone> paraListaTelefone(List<TelefoneDto> telefoneDtos){
        return telefoneDtos.stream().map(this::paraTelefone).toList();
    }

    ////// converter novamente /////

    public UsuarioDto paraUsuarioDto(Usuario usuarioDto){

        return UsuarioDto.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail())
                .senha(usuarioDto.getSenha())
                .enderecos(paraListaEndercecoDto(usuarioDto.getEnderecos()))
                .telefone(paraListaTelefoneDto(usuarioDto.getTelefones()))
                .build();
    }


    public EnderecoDto paraEnderecoDto(Endereco enderecoDto){
        return EnderecoDto.builder()
                .rua(enderecoDto.getRua())
                .numero(enderecoDto.getNumero())
                .cidade(enderecoDto.getCidade())
                .complemento(enderecoDto.getComplemento())
                .estado(enderecoDto.getEstado())
                .build();
    }
    public TelefoneDto paraTelefoneDto(Telefone telefoneDto){
        return TelefoneDto.builder()
                .numero(telefoneDto.getNumero())
                .ddd(telefoneDto.getDdd())
                .build();
    }
    public List<EnderecoDto> paraListaEndercecoDto(List<Endereco> enderecoDtos){
        return enderecoDtos.stream().map(this::paraEnderecoDto).toList();

    }
    public List<TelefoneDto> paraListaTelefoneDto(List<Telefone> telefoneDtos){
        return telefoneDtos.stream().map(this::paraTelefoneDto).toList();
    }

}
