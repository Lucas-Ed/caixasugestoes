package dev.sdras.caixasugestoes.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.sdras.caixasugestoes.config.exception.RecursoNaoLocalizadoException;
import dev.sdras.caixasugestoes.domain.dtos.SugestaoDTO;
import dev.sdras.caixasugestoes.services.SugestaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Sugestões", description = "Sugestões citadas pelo Darlan")
@RequiredArgsConstructor
@RestController
@RequestMapping("/sugestoes")
public class SugestaoResource {

    private final SugestaoService service;

    @Operation(summary = "Cria uma nova sugestão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sugestão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar a sugestão")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody SugestaoDTO dto) {
        SugestaoDTO sugestao = service.salvar(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sugestao.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Listar todas as sugestões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sugestões listadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sugestões não localizadas")
    })
    @GetMapping
    public List<SugestaoDTO> listar() {
        return service.listar();
    }

    // -------------------------------
    // TODO 1 — Buscar sugestão por ID
    // -------------------------------
    @Operation(summary = "Busca sugestão por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sugestão encontrada"),
            @ApiResponse(responseCode = "404", description = "Sugestão não localizada")
    })
    @GetMapping("/{id}")
    public SugestaoDTO buscarPorId(@PathVariable Long id)
            throws RecursoNaoLocalizadoException {

        return service.buscarPorId(id);
    }

    // -------------------------------
    // TODO 2 — Excluir sugestão
    // -------------------------------
    @Operation(summary = "Exclui sugestão existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sugestão excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sugestão não localizada")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id)
            throws RecursoNaoLocalizadoException {

        service.excluir(id);
    }
}