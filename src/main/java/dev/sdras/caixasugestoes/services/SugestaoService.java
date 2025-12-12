package dev.sdras.caixasugestoes.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dev.sdras.caixasugestoes.config.exception.RecursoNaoLocalizadoException;
import dev.sdras.caixasugestoes.domain.SugestaoEntity;
import dev.sdras.caixasugestoes.domain.dtos.SugestaoDTO;
import dev.sdras.caixasugestoes.respositories.SugestaoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SugestaoService {

    private final SugestaoRepository repository;
    private final ModelMapper modelMapper;

    public SugestaoDTO salvar(SugestaoDTO dto) {
        SugestaoEntity entity = modelMapper.map(dto, SugestaoEntity.class);
        SugestaoEntity saved = repository.save(entity);
        return modelMapper.map(saved, SugestaoDTO.class);
    }

    public List<SugestaoDTO> listar() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, SugestaoDTO.class))
                .collect(Collectors.toList());
    }

    public SugestaoDTO buscarPorId(Long id) {
        SugestaoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoLocalizadoException("Sugestão não encontrada"));
        return modelMapper.map(entity, SugestaoDTO.class);
    }

    public void excluir(Long id) {
        SugestaoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoLocalizadoException("Sugestão não encontrada"));

        repository.delete(entity);
    }
}