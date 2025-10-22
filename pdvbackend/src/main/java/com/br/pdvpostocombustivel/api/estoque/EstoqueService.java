package com.br.pdvpostocombustivel.api.estoque;

import br.com.pdvfrontend.model.Estoque;
import com.br.pdvpostocombustivel.api.estoque.dto.EstoqueRequest;
import com.br.pdvpostocombustivel.api.estoque.dto.EstoqueResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstoqueService {

    private List<Estoque> allEstoques;

    public EstoqueResponse create(EstoqueRequest req) {
        // Mock implementation
        EstoqueResponse res = new EstoqueResponse();
        res.setId(1L);
        res.setQuantidade(req.getQuantidade());
        res.setLocalTanque(req.getLocalTanque());
        res.setLocalEndereco(req.getLocalEndereco());
        res.setLoteFabricacao(req.getLoteFabricacao());
        res.setDataValidade(req.getDataValidade());
        return res;
    }

    public EstoqueResponse getById(Long id) {
        // Mock implementation
        EstoqueResponse res = new EstoqueResponse();
        res.setId(id);
        return res;
    }

    public Page<EstoqueResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        // Mock implementation
        List<EstoqueResponse> list = new ArrayList<>();
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return new PageImpl<>(list, pageable, list.size());
    }

    public EstoqueResponse update(Long id, EstoqueRequest req) {
        // Mock implementation
        EstoqueResponse res = new EstoqueResponse();
        res.setId(id);
        res.setQuantidade(req.getQuantidade());
        res.setLocalTanque(req.getLocalTanque());
        res.setLocalEndereco(req.getLocalEndereco());
        res.setLoteFabricacao(req.getLoteFabricacao());
        res.setDataValidade(req.getDataValidade());
        return res;
    }

    public EstoqueResponse patch(Long id, EstoqueRequest req) {
        // Mock implementation
        EstoqueResponse res = new EstoqueResponse();
        res.setId(id);
        res.setQuantidade(req.getQuantidade());
        res.setLocalTanque(req.getLocalTanque());
        res.setLocalEndereco(req.getLocalEndereco());
        res.setLoteFabricacao(req.getLoteFabricacao());
        res.setDataValidade(req.getDataValidade());
        return res;
    }

    public void delete(Long id) {
        // Mock implementation
    }

    public void addEstoque(Estoque estoque) {
    }

    public List<Estoque> getAllEstoques() {
        return allEstoques;
    }

    public void setAllEstoques(List<Estoque> allEstoques) {
        this.allEstoques = allEstoques;
    }
}
