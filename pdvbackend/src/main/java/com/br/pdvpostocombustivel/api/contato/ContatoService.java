package com.br.pdvpostocombustivel.api.contato;

import com.br.pdvpostocombustivel.api.contato.dto.ContatoRequest;
import com.br.pdvpostocombustivel.api.contato.dto.ContatoResponse;
import com.br.pdvpostocombustivel.domain.entity.Contato;
import com.br.pdvpostocombustivel.domain.repository.ContatoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContatoService {

    private final ContatoRepository repository;

    public ContatoService(ContatoRepository repository) {
        this.repository = repository;
    }

    public ContatoResponse create(ContatoRequest req) {
        Contato novoContato = toEntity(req);
        return toResponse(repository.save(novoContato));
    }

    @Transactional(readOnly = true)
    public ContatoResponse getById(Long id) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado. id=" + id));
        return toResponse(contato);
    }

    @Transactional(readOnly = true)
    public Page<ContatoResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return repository.findAll(pageable).map(this::toResponse);
    }

    public ContatoResponse update(Long id, ContatoRequest req) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado. id=" + id));

        contato.setTelefone(req.getTelefone());
        contato.setEmail(req.getEmail());
        contato.setEndereco(req.getEndereco());

        return toResponse(repository.save(contato));
    }

    public ContatoResponse patch(Long id, ContatoRequest req) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado. id=" + id));

        if (req.getTelefone() != null) {
            contato.setTelefone(req.getTelefone());
        }
        if (req.getEmail() != null) {
            contato.setEmail(req.getEmail());
        }
        if (req.getEndereco() != null) {
            contato.setEndereco(req.getEndereco());
        }

        return toResponse(repository.save(contato));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Contato não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private Contato toEntity(ContatoRequest req) {
        return new Contato(null, req.getTelefone(), req.getEmail(), req.getEndereco());
    }

    private ContatoResponse toResponse(Contato contato) {
        return new ContatoResponse(contato.getId(), contato.getTelefone(), contato.getEmail(), contato.getEndereco());
    }
}
