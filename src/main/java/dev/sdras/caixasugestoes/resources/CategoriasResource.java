package dev.sdras.caixasugestoes.resources;

import dev.sdras.caixasugestoes.domain.dtos.CategoriaDTO;
import dev.sdras.caixasugestoes.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriasResource {

    private final CategoriaService categoriaService;

    public CategoriasResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Void> criar(@Valid @RequestBody CategoriaDTO dto) {
        categoriaService.salvar(dto);
        return ResponseEntity.ok().build();
    }
}
