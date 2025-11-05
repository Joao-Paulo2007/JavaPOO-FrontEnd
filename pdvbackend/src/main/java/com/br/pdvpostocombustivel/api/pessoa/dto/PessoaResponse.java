package com.br.pdvpostocombustivel.api.pessoa.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PessoaResponse {
    private Long id;
    private String nomeCompleto;
    private String cpfCnpj;
    private Long numeroCtps;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;

    public PessoaResponse() {
    }

    public PessoaResponse(Long id, String nomeCompleto, String cpfCnpj, Long numeroCtps, LocalDate dataNascimento) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cpfCnpj;
        this.numeroCtps = numeroCtps;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Long getNumeroCtps() {
        return numeroCtps;
    }

    public void setNumeroCtps(Long numeroCtps) {
        this.numeroCtps = numeroCtps;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
