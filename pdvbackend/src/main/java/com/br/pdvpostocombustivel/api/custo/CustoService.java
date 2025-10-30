package com.br.pdvpostocombustivel.api.custo;

import com.br.pdvpostocombustivel.api.custo.dto.CustoRequest;
import com.br.pdvpostocombustivel.api.custo.dto.CustoResponse;
import com.br.pdvpostocombustivel.domain.entity.Custo;
import com.br.pdvpostocombustivel.domain.repository.CustoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustoService {

    private final CustoRepository repository;

    public CustoService(CustoRepository repository) {
        this.repository = repository;
    }

    public CustoResponse create(CustoRequest req) {
        Custo novoCusto = toEntity(req);
        return toResponse(repository.save(novoCusto));
    }

    @Transactional(readOnly = true)
    public CustoResponse getById(Long id) {
        Custo custo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Custo não encontrado. id=" + id));
        return toResponse(custo);
    }

    @Transactional(readOnly = true)
    public Page<CustoResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return repository.findAll(pageable).map(this::toResponse);
    }

    public CustoResponse update(Long id, CustoRequest req) {
        Custo custo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Custo não encontrado. id=" + id));

        custo.setImposto(req.getImposto());
        custo.setFrete(req.getFrete());
        custo.setCustoVariavel(req.getCustoVariavel());
        custo.setCustoFixo(req.getCustoFixo());
        custo.setMargemLucro(req.getMargemLucro());
        custo.setDataProcessamento(req.getDataProcessamento());

        return toResponse(repository.save(custo));
    }

    public CustoResponse patch(Long id, CustoRequest req) {
        Custo custo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Custo não encontrado. id=" + id));

        if (req.getImposto() != null) {
            custo.setImposto(req.getImposto());
        }
        if (req.getFrete() != null) {
            custo.setFrete(req.getFrete());
        }
        if (req.getCustoVariavel() != null) {
            custo.setCustoVariavel(req.getCustoVariavel());
        }
        if (req.getCustoFixo() != null) {
            custo.setCustoFixo(req.getCustoFixo());
        }
        if (req.getMargemLucro() != null) {
            custo.setMargemLucro(req.getMargemLucro());
        }
        if (req.getDataProcessamento() != null) {
            custo.setDataProcessamento(req.getDataProcessamento());
        }

        return toResponse(repository.save(custo));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Custo não encontrado. id=" + id);
        }
        repository.deleteById(id);
    }

    private Custo toEntity(CustoRequest req) {
        return new Custo(
                null, // id is auto-generated
                req.getImposto(),
                req.getFrete(),
                req.getCustoVariavel(),
                req.getCustoFixo(),
                req.getMargemLucro(),
                req.getDataProcessamento()
        );
    }

    private CustoResponse toResponse(Custo custo) {
        return new CustoResponse(
                custo.getId(),
                custo.getImposto(),
                custo.getFrete(),
                custo.getCustoVariavel(),
                custo.getCustoFixo(),
                custo.getMargemLucro(),
                custo.getDataProcessamento()
        );
    }
}
