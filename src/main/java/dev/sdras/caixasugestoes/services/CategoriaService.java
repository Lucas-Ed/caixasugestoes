package dev.sdras.caixasugestoes.services;

import dev.sdras.caixasugestoes.domain.CategoriaEntity;
import dev.sdras.caixasugestoes.domain.dtos.CategoriaDTO;
import dev.sdras.caixasugestoes.respositories.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public void salvar(CategoriaDTO dto) {
        var entity = new CategoriaEntity(null, dto.getDescricao());
        repository.save(entity);
    }
}
