package com.br.pdvpostocombustivel.api.pessoa.dto;

//Para Resposta

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record PessoaResponse(
        String nomeCompleto,
    String cpfCnpj,
    Long numeroCtps,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dataNascimento)
{}