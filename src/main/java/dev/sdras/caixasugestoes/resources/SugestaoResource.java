package dev.sdras.caixasugestoes.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import dev.sdras.caixasugestoes.domain.dtos.SugestaoDTO;
import dev.sdras.caixasugestoes.services.SugestaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
@RequestMapping("/sugestoes")
public class SugestaoResource {

    private final SugestaoService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody SugestaoDTO dto) {
        SugestaoDTO sugestao = service.salvar(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sugestao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public List<SugestaoDTO> listar() {
        return service.listar();
    }
}
