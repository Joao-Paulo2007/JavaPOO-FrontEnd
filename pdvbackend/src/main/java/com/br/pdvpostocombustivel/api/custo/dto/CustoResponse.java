package com.br.pdvpostocombustivel.api.custo.dto;

import java.util.Date;

public class CustoResponse {
    private Long id;
    private Double imposto;
    private Double frete;
    private Double custoVariavel;
    private Double custoFixo;
    private Double margemLucro;
    private Date dataProcessamento;

    public CustoResponse() {
    }

    public CustoResponse(Long id, Double imposto, Double frete, Double custoVariavel, Double custoFixo, Double margemLucro, Date dataProcessamento) {
        this.id = id;
        this.imposto = imposto;
        this.frete = frete;
        this.custoVariavel = custoVariavel;
        this.custoFixo = custoFixo;
        this.margemLucro = margemLucro;
        this.dataProcessamento = dataProcessamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getImposto() {
        return imposto;
    }

    public void setImposto(Double imposto) {
        this.imposto = imposto;
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public Double getCustoVariavel() {
        return custoVariavel;
    }

    public void setCustoVariavel(Double custoVariavel) {
        this.custoVariavel = custoVariavel;
    }

    public Double getCustoFixo() {
        return custoFixo;
    }

    public void setCustoFixo(Double custoFixo) {
        this.custoFixo = custoFixo;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public Date getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }
}
