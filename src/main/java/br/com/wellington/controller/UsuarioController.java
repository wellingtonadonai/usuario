package br.com.wellington.controller;

import br.com.wellington.business.UsuarioService;
import br.com.wellington.business.dto.EnderecoDto;
import br.com.wellington.business.dto.TelefoneDto;
import br.com.wellington.business.dto.UsuarioDto;
import br.com.wellington.infraestructure.entity.Usuario;
import br.com.wellington.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity <UsuarioDto> salvarUsuario(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDto));

    }
    @PostMapping("/login")
    public String login(@RequestBody UsuarioDto usuarioDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDto.getEmail(),
                        usuarioDto.getSenha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());

    }
    @GetMapping
    public ResponseEntity<UsuarioDto> buscaUsuarioPorEmail(@RequestParam("email")String email){
        return ResponseEntity.ok( usuarioService.buscaUsuarioPorEmail(email));

    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletarUsuarioEmail(@PathVariable String email){
        usuarioService.deletaUsuarioPorEmail(email);

        return ResponseEntity.ok().build();

    }
    @PutMapping
    public ResponseEntity<UsuarioDto> atualizaadosUsuarios(@RequestBody UsuarioDto dto,
                                                           @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }
    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDto> atualizarEndereco(@RequestBody EnderecoDto dto,
                                                         @RequestParam("id")Long id){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto));
    }
    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDto> atualizarTelefone(@RequestBody TelefoneDto dto,
                                                         @RequestParam("id")Long id){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto));
    }
}
