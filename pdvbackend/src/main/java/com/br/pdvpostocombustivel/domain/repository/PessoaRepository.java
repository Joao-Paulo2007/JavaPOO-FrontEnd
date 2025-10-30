package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByNome(String nomeCompleto);

    Optional<Pessoa> findByCpfCnpj(String cpfCnpj);

    boolean existsByNome(String nomeCompleto);

    boolean existsByCpfCnpj(String cpfCnpj);
}
