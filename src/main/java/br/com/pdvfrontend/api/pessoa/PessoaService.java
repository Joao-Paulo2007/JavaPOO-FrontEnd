package br.com.pdvfrontend.api.pessoa;

import br.com.pdvfrontend.api.pessoa.dto.PessoaRequest;
import br.com.pdvfrontend.api.pessoa.dto.PessoaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {

    public PessoaResponse create(PessoaRequest req) {
        // Mock implementation
        PessoaResponse res = new PessoaResponse();
        res.setId(1L);
        res.setNome(req.getNome());
        res.setCpfCnpj(req.getCpfCnpj());
        return res;
    }

    public PessoaResponse getById(Long id) {
        // Mock implementation
        PessoaResponse res = new PessoaResponse();
        res.setId(id);
        res.setNome("Pessoa Teste");
        res.setCpfCnpj("12345678901");
        return res;
    }

    public PessoaResponse getByCpfCnpj(String cpfCnpj) {
        // Mock implementation
        PessoaResponse res = new PessoaResponse();
        res.setId(1L);
        res.setNome("Pessoa Teste");
        res.setCpfCnpj(cpfCnpj);
        return res;
    }

    public Page<PessoaResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        // Mock implementation
        List<PessoaResponse> list = new ArrayList<>();
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return new PageImpl<>(list, pageable, list.size());
    }

    public PessoaResponse update(Long id, PessoaRequest req) {
        // Mock implementation
        PessoaResponse res = new PessoaResponse();
        res.setId(id);
        res.setNome(req.getNome());
        res.setCpfCnpj(req.getCpfCnpj());
        return res;
    }

    public PessoaResponse patch(Long id, PessoaRequest req) {
        // Mock implementation
        PessoaResponse res = new PessoaResponse();
        res.setId(id);
        res.setNome(req.getNome());
        res.setCpfCnpj(req.getCpfCnpj());
        return res;
    }

    public void delete(Long id) {
        // Mock implementation
    }
}
