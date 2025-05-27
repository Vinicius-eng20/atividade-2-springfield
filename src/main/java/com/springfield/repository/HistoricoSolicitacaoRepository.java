package com.springfield.repository;

import com.springfield.model.HistoricoSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoricoSolicitacaoRepository extends JpaRepository<HistoricoSolicitacao, Long> {
    List<HistoricoSolicitacao> findByIdCidadao(int idCidadao);
}