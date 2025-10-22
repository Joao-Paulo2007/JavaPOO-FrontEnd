package com.br.pdvpostocombustivel.api.preco;

import br.com.pdvfrontend.model.Preco;
import com.br.pdvpostocombustivel.api.preco.dto.PrecoRequest;
import com.br.pdvpostocombustivel.api.preco.dto.PrecoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrecoService {

    public PrecoResponse create(PrecoRequest req) {
        // Mock implementation
        PrecoResponse res = new PrecoResponse();
        res.setId(1L);
        res.setValor(req.getValor());
        res.setDataAlteracao(req.getDataAlteracao());
        res.setHoraAlteracao(req.getHoraAlteracao());
        return res;
    }

    public PrecoResponse getById(Long id) {
        // Mock implementation
        PrecoResponse res = new PrecoResponse();
        res.setId(id);
        return res;
    }

    public Page<PrecoResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        // Mock implementation
        List<PrecoResponse> list = new ArrayList<>();
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return new PageImpl<>(list, pageable, list.size());
    }

    public PrecoResponse update(Long id, PrecoRequest req) {
        // Mock implementation
        PrecoResponse res = new PrecoResponse();
        res.setId(id);
        res.setValor(req.getValor());
        res.setDataAlteracao(req.getDataAlteracao());
        res.setHoraAlteracao(req.getHoraAlteracao());
        return res;
    }

    public PrecoResponse patch(Long id, PrecoRequest req) {
        // Mock implementation
        PrecoResponse res = new PrecoResponse();
        res.setId(id);
        res.setValor(req.getValor());
        res.setDataAlteracao(req.getDataAlteracao());
        res.setHoraAlteracao(req.getHoraAlteracao());
        return res;
    }

    public void delete(Long id) {
        // Mock implementation
    }

    public List<Preco> getAllPrecos() {
        return null;
    }
}
