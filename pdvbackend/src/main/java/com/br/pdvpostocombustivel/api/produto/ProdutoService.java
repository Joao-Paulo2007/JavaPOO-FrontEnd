package com.br.pdvpostocombustivel.api.produto;

import com.br.pdvpostocombustivel.api.produto.dto.ProdutoRequest;
import com.br.pdvpostocombustivel.api.produto.dto.ProdutoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    public ProdutoResponse create(ProdutoRequest req) {
        // Mock implementation
        ProdutoResponse res = new ProdutoResponse();
        res.setId(1L);
        res.setNome(req.getNome());
        res.setReferencia(req.getReferencia());
        res.setFornecedor(req.getFornecedor());
        res.setCategoria(req.getCategoria());
        res.setMarca(req.getMarca());
        return res;
    }

    public ProdutoResponse getById(Long id) {
        // Mock implementation
        ProdutoResponse res = new ProdutoResponse();
        res.setId(id);
        res.setNome("Produto Teste");
        res.setReferencia("REF123");
        res.setFornecedor("Fornecedor Teste");
        res.setCategoria("Categoria Teste");
        res.setMarca("Marca Teste");
        return res;
    }

    public ProdutoResponse getByReferencia(String referencia) {
        // Mock implementation
        ProdutoResponse res = new ProdutoResponse();
        res.setId(1L);
        res.setNome("Produto Teste");
        res.setReferencia(referencia);
        res.setFornecedor("Fornecedor Teste");
        res.setCategoria("Categoria Teste");
        res.setMarca("Marca Teste");
        return res;
    }

    public Page<ProdutoResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        // Mock implementation
        List<ProdutoResponse> list = new ArrayList<>();
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return new PageImpl<>(list, pageable, list.size());
    }

    public ProdutoResponse update(Long id, ProdutoRequest req) {
        // Mock implementation
        ProdutoResponse res = new ProdutoResponse();
        res.setId(id);
        res.setNome(req.getNome());
        res.setReferencia(req.getReferencia());
        res.setFornecedor(req.getFornecedor());
        res.setCategoria(req.getCategoria());
        res.setMarca(req.getMarca());
        return res;
    }

    public ProdutoResponse patch(Long id, ProdutoRequest req) {
        // Mock implementation
        ProdutoResponse res = new ProdutoResponse();
        res.setId(id);
        res.setNome(req.getNome());
        res.setReferencia(req.getReferencia());
        res.setFornecedor(req.getFornecedor());
        res.setCategoria(req.getCategoria());
        res.setMarca(req.getMarca());
        return res;
    }

    public void delete(Long id) {
        // Mock implementation
    }
}
