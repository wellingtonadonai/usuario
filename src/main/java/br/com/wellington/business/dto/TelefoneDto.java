package br.com.wellington.business.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDto {

    private Long id;
    private String numero;
    private String ddd;
}
