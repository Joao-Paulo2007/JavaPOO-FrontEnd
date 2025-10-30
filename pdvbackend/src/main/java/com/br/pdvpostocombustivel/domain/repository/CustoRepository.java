package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.Custo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Date;

public interface CustoRepository extends JpaRepository<Custo, Long> {
    Optional<Custo> findByDataProcessamento(Date dataProcessamento);
    boolean existsByDataProcessamento(Date dataProcessamento);
}