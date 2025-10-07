package br.com.wellington.business.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private String nome;
    private String email;
    private String senha;

    private List<EnderecoDto> enderecos;
    private List<TelefoneDto> telefone;
}
