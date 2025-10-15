package br.com.pdvfrontend.api.custo;

import br.com.pdvfrontend.api.custo.dto.CustoRequest;
import br.com.pdvfrontend.api.custo.dto.CustoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustoService {

    public CustoResponse create(CustoRequest req) {
        // Mock implementation
        CustoResponse res = new CustoResponse();
        res.setId(1L);
        res.setImposto(req.getImposto());
        res.setFrete(req.getFrete());
        res.setCustoVariavel(req.getCustoVariavel());
        res.setCustoFixo(req.getCustoFixo());
        res.setMargemLucro(req.getMargemLucro());
        res.setDataProcessamento(req.getDataProcessamento());
        return res;
    }

    public CustoResponse getById(Long id) {
        // Mock implementation
        CustoResponse res = new CustoResponse();
        res.setId(id);
        return res;
    }

    public Page<CustoResponse> list(int page, int size, String sortBy, Sort.Direction dir) {
        // Mock implementation
        List<CustoResponse> list = new ArrayList<>();
        PageRequest pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        return new PageImpl<>(list, pageable, list.size());
    }

    public CustoResponse update(Long id, CustoRequest req) {
        // Mock implementation
        CustoResponse res = new CustoResponse();
        res.setId(id);
        res.setImposto(req.getImposto());
        res.setFrete(req.getFrete());
        res.setCustoVariavel(req.getCustoVariavel());
        res.setCustoFixo(req.getCustoFixo());
        res.setMargemLucro(req.getMargemLucro());
        res.setDataProcessamento(req.getDataProcessamento());
        return res;
    }

    public CustoResponse patch(Long id, CustoRequest req) {
        // Mock implementation
        CustoResponse res = new CustoResponse();
        res.setId(id);
        res.setImposto(req.getImposto());
        res.setFrete(req.getFrete());
        res.setCustoVariavel(req.getCustoVariavel());
        res.setCustoFixo(req.getCustoFixo());
        res.setMargemLucro(req.getMargemLucro());
        res.setDataProcessamento(req.getDataProcessamento());
        return res;
    }

    public void delete(Long id) {
        // Mock implementation
    }
}
