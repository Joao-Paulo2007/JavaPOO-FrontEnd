package br.com.pdvfrontend.api.contato;

import br.com.pdvfrontend.api.contato.dto.ContatoRequest;
import br.com.pdvfrontend.api.contato.dto.ContatoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContatoService {

    public ContatoResponse create(ContatoRequest req) {
        // Mock implementation
        ContatoResponse res = new ContatoResponse();
        res.setId(1L);
        res.setTelefone(req.getTelefone());
        res.setEmail(req.getEmail());
        res.setEndereco(req.getEndereco());
        return res;
    }

    public ContatoResponse getById(Long id) {
        // Mock implementation
        ContatoResponse res = new ContatoResponse();
        res.setId(id);
        res.setTelefone("99999-9999");
        res.setEmail("teste@email.com");
        res.setEndereco("Endereco de teste");
        return res;
    }

    public Page<ContatoResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        // Mock implementation
        List<ContatoResponse> list = new ArrayList<>();
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return new PageImpl<>(list, pageable, list.size());
    }

    public ContatoResponse update(Long id, ContatoRequest req) {
        // Mock implementation
        ContatoResponse res = new ContatoResponse();
        res.setId(id);
        res.setTelefone(req.getTelefone());
        res.setEmail(req.getEmail());
        res.setEndereco(req.getEndereco());
        return res;
    }

    public ContatoResponse patch(Long id, ContatoRequest req) {
        // Mock implementation
        ContatoResponse res = new ContatoResponse();
        res.setId(id);
        res.setTelefone(req.getTelefone());
        res.setEmail(req.getEmail());
        res.setEndereco(req.getEndereco());
        return res;
    }

    public void delete(Long id) {
        // Mock implementation
    }
}
