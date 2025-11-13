package dev.sdras.caixasugestoes.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SugestaoDTO {
    private Long id;
    private String autor;
    @NotNull(message = "Descricao nao pode ser nula")
    private String descricao;
    @NotNull(message = "Categoria nao pode ser nula")
    private CategoriaDTO categoria;
}
