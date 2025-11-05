package com.br.pdvpostocombustivel.api.acesso;

import com.br.pdvpostocombustivel.api.acesso.dto.AcessoRequest;
import com.br.pdvpostocombustivel.api.acesso.dto.AcessoResponse;
import com.br.pdvpostocombustivel.domain.entity.Acesso;
import com.br.pdvpostocombustivel.domain.repository.AcessoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AcessoService {

    private AcessoRepository repository;

    public AcessoService() {
        this.repository = repository;
    }

    public AcessoResponse create(AcessoRequest req) {
        validarUnicidadeUsuario(req.getUsuario(), null);
        Acesso novoAcesso = toEntity(req);
        return toResponse(repository.save(novoAcesso));
    }

    @Transactional(readOnly = true)
    public AcessoResponse getById(Long id) {
        Acesso acesso = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado. id=" + id));
        return toResponse(acesso);
    }

    @Transactional(readOnly = true)
    public AcessoResponse getByUsuario(String usuario) {
        Acesso acesso = repository.findByUsuario(usuario)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado. usuario=" + usuario));
        return toResponse(acesso);
    }

    @Transactional(readOnly = true)
    public Page<AcessoResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return repository.findAll(pageable).map(this::toResponse);
    }

    public AcessoResponse update(Long id, AcessoRequest req) {
        Acesso acesso = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado. id=" + id));

        if (req.getUsuario() != null && !req.getUsuario().equals(acesso.getUsuario())) {
            validarUnicidadeUsuario(req.getUsuario(), id);
        }

        acesso.setUsuario(req.getUsuario());
        acesso.setSenha(req.getSenha());

        return toResponse(repository.save(acesso));
    }

    public AcessoResponse patch(Long id, AcessoRequest req) {
        Acesso acesso = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado. id=" + id));

        if (req.getUsuario() != null) {
            if (!req.getUsuario().equals(acesso.getUsuario())) {
                validarUnicidadeUsuario(req.getUsuario(), id);
            }
            acesso.setUsuario(req.getUsuario());
        }
        if (req.getSenha() != null) {
            acesso.setSenha(req.getSenha());
        }

        return toResponse(repository.save(acesso));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Acesso não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private void validarUnicidadeUsuario(String usuario, Long idAtual) {
        repository.findByUsuario(usuario).ifPresent(existente -> {
            if (idAtual == null || !existente.getId().equals(idAtual)) {
                throw new DataIntegrityViolationException("Usuário já cadastrado: " + usuario);
            }
        });
    }

    private Acesso toEntity(AcessoRequest req) {
        return new Acesso(null, req.getUsuario(), req.getSenha());
    }

    private AcessoResponse toResponse(Acesso acesso) {
        return new AcessoResponse(acesso.getId(), acesso.getUsuario());
    }
}
