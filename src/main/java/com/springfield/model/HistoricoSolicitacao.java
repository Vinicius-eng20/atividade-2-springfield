package com.springfield.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HistoricoSolicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int idCidadao;
    private String estado;
    private LocalDate data;
    private String descricao;

    public HistoricoSolicitacao() {}
    public HistoricoSolicitacao(Long id, int idCidadao, String estado, LocalDate data, String descricao) {
        this.id = id;
        this.idCidadao = idCidadao;
        this.estado = estado;
        this.data = data;
        this.descricao = descricao;
    }

    // Getters e setters
    public Long getId() { return id; }
    public int getIdCidadao() { return idCidadao; }
    public String getEstado() { return estado; }
    public LocalDate getData() { return data; }
    public String getDescricao() { return descricao; }
}