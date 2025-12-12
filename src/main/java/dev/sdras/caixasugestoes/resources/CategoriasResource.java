package dev.sdras.caixasugestoes.resources;

import dev.sdras.caixasugestoes.config.exception.RegraDeNegocioException;
import dev.sdras.caixasugestoes.config.exception.RecursoNaoLocalizadoException;
import dev.sdras.caixasugestoes.domain.dtos.CategoriaDTO;
import dev.sdras.caixasugestoes.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Tag(name = "Categorias", description = "Categorias das sugestões")
@RequiredArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriasResource {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody CategoriaDTO dto) {
        CategoriaDTO categoria = categoriaService.salvar(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualiza categoria existente")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id,
                                                  @Valid @RequestBody CategoriaDTO dto)
            throws RecursoNaoLocalizadoException {
        CategoriaDTO atualizado = categoriaService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Busca categoria por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoria não localizada")
    })
    @GetMapping("/{id}")
    public CategoriaDTO buscarPorId(@PathVariable Long id)
            throws RecursoNaoLocalizadoException {
        return categoriaService.buscarPorId(id);
    }

    @Operation(summary = "Exclui categoria existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria excluida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não localizada"),
            @ApiResponse(responseCode = "422", description = "Regra de negócio violada")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id)
            throws RecursoNaoLocalizadoException, RegraDeNegocioException {

        categoriaService.excluir(id);
    }

    @GetMapping
    public List<CategoriaDTO> getCategorias() {
        return categoriaService.listar();
    }
}

    //TODO: Criar end-point para atualização de categórias

    //TODO: Criar end-point para busca de categorias por id

    //TODO: Criar end-point excluir categoria validando se a categoria está associada a alguma sugestão, caso esteja, não permitir a exclusão lançando uma RegraDeNegocioException, inclua também a documentação em Swagger

