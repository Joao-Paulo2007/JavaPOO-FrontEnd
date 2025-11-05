package com.br.pdvpostocombustivel.api.preco.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PrecoRequest {
    private BigDecimal valor;
    private Date dataAlteracao;
    private Date horaAlteracao;

    public PrecoRequest(BigDecimal valor, Date dataAlteracao, Date horaAlteracao) {
        this.valor = valor;
        this.dataAlteracao = dataAlteracao;
        this.horaAlteracao = horaAlteracao;
    }

    // Getters and Setters
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Date getHoraAlteracao() {
        return horaAlteracao;
    }

    public void setHoraAlteracao(Date horaAlteracao) {
        this.horaAlteracao = horaAlteracao;
    }
}
