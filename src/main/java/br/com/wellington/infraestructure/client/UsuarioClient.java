package br.com.wellington.infraestructure.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "usuario", url = "${usuario.url}")

public interface UsuarioClient {
}
