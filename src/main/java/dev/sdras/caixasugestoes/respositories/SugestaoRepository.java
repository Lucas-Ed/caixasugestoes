package dev.sdras.caixasugestoes.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sdras.caixasugestoes.domain.SugestaoEntity;

@Repository
public interface SugestaoRepository extends JpaRepository<SugestaoEntity, Long> {

    long countByCategoriaId(Long categoriaId);
}