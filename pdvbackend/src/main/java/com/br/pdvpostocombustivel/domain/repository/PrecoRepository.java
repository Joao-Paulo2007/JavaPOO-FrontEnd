package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.Preco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Date;
import java.math.BigDecimal;

public interface PrecoRepository extends JpaRepository<Preco, Long> {
    Optional<Preco> findByDataAlteracao(Date dataAlteracao);
    boolean existsByValor(BigDecimal valor);
}