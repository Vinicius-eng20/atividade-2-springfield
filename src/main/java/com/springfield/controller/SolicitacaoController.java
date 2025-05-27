package com.springfield.controller;

import com.springfield.model.HistoricoSolicitacao;
import com.springfield.repository.HistoricoSolicitacaoRepository;
import com.springfield.state.EstadoSolicitacao;
import com.springfield.state.EventoSolicitacao;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/solicitacao")
@OpenAPIDefinition(info = @Info(title = "Controle de Solicitações"))
public class SolicitacaoController {

    @Autowired
    private StateMachineFactory<EstadoSolicitacao, EventoSolicitacao> factory;
    @Autowired
    private HistoricoSolicitacaoRepository historicoRepo;

    @PostMapping("/registrar")
    public void registrarSolicitacao(@RequestParam int idCidadao, @RequestParam String descricao) {
        var sm = factory.getStateMachine();
        sm.start();
        salvarHistorico(idCidadao, sm.getState().getId(), descricao);
    }

    @PutMapping("/analisar")
    public void analisar(@RequestParam int idCidadao, @RequestParam String descricao) {
        var sm = factory.getStateMachine();
        sm.start();
        sm.sendEvent(EventoSolicitacao.ANALISAR);
        salvarHistorico(idCidadao, sm.getState().getId(), descricao);
    }

    @PutMapping("/concluir")
    public void concluir(@RequestParam int idCidadao, @RequestParam String descricao) {
        var sm = factory.getStateMachine();
        sm.start();
        sm.sendEvent(EventoSolicitacao.CONCLUIR);
        salvarHistorico(idCidadao, sm.getState().getId(), descricao);
    }

    @GetMapping("/historico/{idCidadao}")
    public List<HistoricoSolicitacao> getHistorico(@PathVariable int idCidadao) {
        return historicoRepo.findByIdCidadao(idCidadao);
    }

    private void salvarHistorico(int id, EstadoSolicitacao estado, String descricao) {
        historicoRepo.save(new HistoricoSolicitacao(null, id, estado.name(), LocalDate.now(), descricao));
    }
}