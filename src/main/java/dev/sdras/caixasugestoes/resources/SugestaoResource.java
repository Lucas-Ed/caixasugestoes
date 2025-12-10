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

import dev.sdras.caixasugestoes.domain.dtos.SugestaoDTO;
import dev.sdras.caixasugestoes.services.SugestaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


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
                .buildAndExpand(sugestao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Listar todas as sugestões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", 
                description = "Sugestões listadas com sucesso"),
            @ApiResponse(responseCode = "404", 
                description = "Sugestões não localizadas") })
    @GetMapping
    public List<SugestaoDTO> listar() {
        return service.listar();    
    }

    //TODO: Criar end-point para busca de sugestões por id incluindo a documentação em Swagger

    //TODO: Criar end-point excluir sugestão, inclua também a documentação em Swagger
}
