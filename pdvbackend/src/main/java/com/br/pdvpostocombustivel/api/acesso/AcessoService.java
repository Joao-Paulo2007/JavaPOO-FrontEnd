package com.br.pdvpostocombustivel.api.acesso;

import br.com.pdvfrontend.model.Acesso;
import com.br.pdvpostocombustivel.api.acesso.dto.AcessoRequest;
import com.br.pdvpostocombustivel.api.acesso.dto.AcessoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcessoService {

    private List<Acesso> allAcessos;

    public AcessoResponse create(AcessoRequest req) {
        // Mock implementation
        AcessoResponse res = new AcessoResponse();
        res.setId(1L);
        res.setUsuario(req.getUsuario());
        return res;
    }

    public AcessoResponse getById(Long id) {
        // Mock implementation
        AcessoResponse res = new AcessoResponse();
        res.setId(id);
        res.setUsuario("usuario.teste");
        return res;
    }

    public AcessoResponse getByUsuario(String usuario) {
        // Mock implementation
        AcessoResponse res = new AcessoResponse();
        res.setId(1L);
        res.setUsuario(usuario);
        return res;
    }

    public Page<AcessoResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        // Mock implementation
        List<AcessoResponse> list = new ArrayList<>();
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return new PageImpl<>(list, pageable, list.size());
    }

    public AcessoResponse update(Long id, AcessoRequest req) {
        // Mock implementation
        AcessoResponse res = new AcessoResponse();
        res.setId(id);
        res.setUsuario(req.getUsuario());
        return res;
    }

    public AcessoResponse patch(Long id, AcessoRequest req) {
        // Mock implementation
        AcessoResponse res = new AcessoResponse();
        res.setId(id);
        res.setUsuario(req.getUsuario());
        return res;
    }

    public void delete(Long id) {
        // Mock implementation
    }

    public void addAcesso(Acesso acesso) {
    }

    public List<Acesso> getAllAcessos() {
        return allAcessos;
    }

    public void setAllAcessos(List<Acesso> allAcessos) {
        this.allAcessos = allAcessos;
    }
}
