package com.br.pdvpostocombustivel.api.estoque;

import com.br.pdvpostocombustivel.api.estoque.dto.EstoqueRequest;
import com.br.pdvpostocombustivel.api.estoque.dto.EstoqueResponse;
import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.repository.EstoqueRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstoqueService {

    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    public EstoqueResponse create(EstoqueRequest req) {
        Estoque novoEstoque = toEntity(req);
        return toResponse(repository.save(novoEstoque));
    }

    @Transactional(readOnly = true)
    public EstoqueResponse getById(Long id) {
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado. id=" + id));
        return toResponse(estoque);
    }

    @Transactional(readOnly = true)
    public Page<EstoqueResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return repository.findAll(pageable).map(this::toResponse);
    }

    public EstoqueResponse update(Long id, EstoqueRequest req) {
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado. id=" + id));

        estoque.setQuantidade(req.getQuantidade());
        estoque.setLocalTanque(req.getLocalTanque());
        estoque.setLocalEndereco(req.getLocalEndereco());
        estoque.setLoteFabricacao(req.getLoteFabricacao());
        estoque.setDataValidade(req.getDataValidade());

        return toResponse(repository.save(estoque));
    }

    public EstoqueResponse patch(Long id, EstoqueRequest req) {
        Estoque estoque = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado. id=" + id));

        if (req.getQuantidade() != null) {
            estoque.setQuantidade(req.getQuantidade());
        }
        if (req.getLocalTanque() != null) {
            estoque.setLocalTanque(req.getLocalTanque());
        }
        if (req.getLocalEndereco() != null) {
            estoque.setLocalEndereco(req.getLocalEndereco());
        }
        if (req.getLoteFabricacao() != null) {
            estoque.setLoteFabricacao(req.getLoteFabricacao());
        }
        if (req.getDataValidade() != null) {
            estoque.setDataValidade(req.getDataValidade());
        }

        return toResponse(repository.save(estoque));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Estoque não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private Estoque toEntity(EstoqueRequest req) {
        return new Estoque(
                null, // id is auto-generated
                req.getQuantidade(),
                req.getLocalTanque(),
                req.getLocalEndereco(),
                req.getLoteFabricacao(),
                req.getDataValidade()
        );
    }

    private EstoqueResponse toResponse(Estoque estoque) {
        return new EstoqueResponse(
                estoque.getId(),
                estoque.getQuantidade(),
                estoque.getLocalTanque(),
                estoque.getLocalEndereco(),
                estoque.getLoteFabricacao(),
                estoque.getDataValidade()
        );
    }
}
