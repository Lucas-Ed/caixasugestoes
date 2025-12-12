package dev.sdras.caixasugestoes.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dev.sdras.caixasugestoes.config.exception.RegraDeNegocioException;
import dev.sdras.caixasugestoes.config.exception.RecursoNaoLocalizadoException;
import dev.sdras.caixasugestoes.domain.CategoriaEntity;
import dev.sdras.caixasugestoes.domain.dtos.CategoriaDTO;
import dev.sdras.caixasugestoes.respositories.CategoriaRepository;
import dev.sdras.caixasugestoes.respositories.SugestaoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoriaService {

    private final CategoriaRepository repository;
    private final SugestaoRepository sugestaoRepository;
    private final ModelMapper modelMapper;

    public CategoriaDTO salvar(CategoriaDTO dto) {
        CategoriaEntity entity = modelMapper.map(dto, CategoriaEntity.class);
        CategoriaEntity saved = repository.save(entity);
        return modelMapper.map(saved, CategoriaDTO.class);
    }

    public List<CategoriaDTO> listar() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, CategoriaDTO.class))
                .collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(Long id) {
        CategoriaEntity categoria = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoLocalizadoException("Categoria não encontrada"));
        return modelMapper.map(categoria, CategoriaDTO.class);
    }


    public CategoriaDTO atualizar(Long id, CategoriaDTO dto) {
        CategoriaEntity categoria = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoLocalizadoException("Categoria não encontrada"));

        categoria.setDescricao(dto.getDescricao());

        repository.save(categoria);
        return modelMapper.map(categoria, CategoriaDTO.class);
    }

    public void excluir(Long id) {

        CategoriaEntity categoria = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoLocalizadoException("Categoria não encontrada"));

        long qtd = sugestaoRepository.countByCategoriaId(id);
        if (qtd > 0) {
            throw new RegraDeNegocioException("Categoria possui sugestões associadas e não pode ser removida.");
        }

        repository.delete(categoria);
    }
}