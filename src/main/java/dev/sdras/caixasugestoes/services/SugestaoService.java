package dev.sdras.caixasugestoes.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        var entity = modelMapper.map(dto, SugestaoEntity.class);
        var entitySaved = repository.save(entity);
        return modelMapper.map(entitySaved, SugestaoDTO.class);
    }

    public List<SugestaoDTO> listar() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, SugestaoDTO.class))
                .collect(Collectors.toList());
    }
}
