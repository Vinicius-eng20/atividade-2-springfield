package com.springfield.model;

import jakarta.persistence.*;

@Entity
public class ParcelaIPTU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int idCidadao;
    private int mes;
    private double valor;
    private boolean pago;

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getIdCidadao() { return idCidadao; }
    public void setIdCidadao(int idCidadao) { this.idCidadao = idCidadao; }

    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public boolean isPago() { return pago; }
    public void setPago(boolean pago) { this.pago = pago; }
}