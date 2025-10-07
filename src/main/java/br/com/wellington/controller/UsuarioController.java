package br.com.wellington.controller;

import br.com.wellington.business.UsuarioService;
import br.com.wellington.business.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    public ResponseEntity <UsuarioDto> salvarUsuario(@RequestBody UsuarioDto usuarioDto){

        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDto));


    }
}
