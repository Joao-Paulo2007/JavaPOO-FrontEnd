package com.br.pdvpostocombustivel.api.preco;

import com.br.pdvpostocombustivel.api.preco.dto.PrecoRequest;
import com.br.pdvpostocombustivel.api.preco.dto.PrecoResponse;
import com.br.pdvpostocombustivel.domain.entity.Preco;
import com.br.pdvpostocombustivel.domain.repository.PrecoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrecoService {

    private final PrecoRepository repository;

    public PrecoService(PrecoRepository repository) {
        this.repository = repository;
    }

    public PrecoResponse create(PrecoRequest req) {
        Preco novoPreco = toEntity(req);
        return toResponse(repository.save(novoPreco));
    }

    @Transactional(readOnly = true)
    public PrecoResponse getById(Long id) {
        Preco preco = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Preço não encontrado. id=" + id));
        return toResponse(preco);
    }

    @Transactional(readOnly = true)
    public Page<PrecoResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return repository.findAll(pageable).map(this::toResponse);
    }

    public PrecoResponse update(Long id, PrecoRequest req) {
        Preco preco = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Preço não encontrado. id=" + id));

        preco.setValor(req.getValor());
        preco.setDataAlteracao(req.getDataAlteracao());
        preco.setHoraAlteracao(req.getHoraAlteracao());

        return toResponse(repository.save(preco));
    }

    public PrecoResponse patch(Long id, PrecoRequest req) {
        Preco preco = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Preço não encontrado. id=" + id));

        if (req.getValor() != null) {
            preco.setValor(req.getValor());
        }
        if (req.getDataAlteracao() != null) {
            preco.setDataAlteracao(req.getDataAlteracao());
        }
        if (req.getHoraAlteracao() != null) {
            preco.setHoraAlteracao(req.getHoraAlteracao());
        }

        return toResponse(repository.save(preco));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Preço não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private Preco toEntity(PrecoRequest req) {
        return new Preco(null, req.getValor(), req.getDataAlteracao(), req.getHoraAlteracao());
    }

    private PrecoResponse toResponse(Preco preco) {
        return new PrecoResponse(preco.getId(), preco.getValor(), preco.getDataAlteracao(), preco.getHoraAlteracao());
    }
}
