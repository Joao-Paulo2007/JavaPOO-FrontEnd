package com.br.pdvpostocombustivel;

import com.br.pdvpostocombustivel.domain.entity.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class PdvpostocombustivelApplication {

	public static void main(String[] args) {
		//SpringApplication.run(PdvpostocombustivelApplication.class, args);

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNomeCompleto("José Saldanha Revaldo");
        pessoa1.setCpfCnpj("07864343576");
        pessoa1.setNumeroCtps(32453L);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNomeCompleto("Maria Junqueira Melgaço");
        pessoa2.setCpfCnpj("65798241008");
        pessoa2.setNumeroCtps(87312L);

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setNomeCompleto("João Scarcela Emmanuel");
        pessoa3.setCpfCnpj("26174097534");
        pessoa3.setNumeroCtps(23769L);

        Contato contato1 = new Contato();
        contato1.setTelefone("+55 (62) 998370317");
        contato1.setEmail("zezinho@gmail.com");
        contato1.setEndereco("Av.Sen.José Rodrigues de Morais Neto, Qd. 213 - LT.34 - Parque Amazonia");

        Contato contato2 = new Contato();
        contato2.setTelefone("+55 (62) 982367238");
        contato2.setEmail("mari01234@gmail.com");
        contato2.setEndereco("Av. T-14, 250 - Serrinha");

        Contato contato3 = new Contato();
        contato3.setTelefone("+55 (62) 987630297");
        contato3.setEmail("joaozinho34@gmail.com");
        contato3.setEndereco("Rua 44, 399, Araguaia Shopping.");

        Acesso acesso1 = new Acesso();
        acesso1.setUsuario("José");
        acesso1.setSenha("ze0129");

        Acesso acesso2 = new Acesso();
        acesso2.setUsuario("Maria");
        acesso2.setSenha("mj6438");

        Acesso acesso3 = new Acesso();
        acesso3.setUsuario("João");
        acesso3.setSenha("js5598");

        Custo custo1 = new Custo();
        custo1.setImposto(15000.75);
        custo1.setCustoVariavel(4750.85);
        custo1.setCustoFixo(1750.00);
        custo1.setMargemLucro(6500.00);

        Estoque estoque1 = new Estoque();
        estoque1.setQuantidade(new BigDecimal(1000));
        estoque1.setLocalTanque("Tanque 01");
        estoque1.setLocalEndereco("Estoque Principal");
        estoque1.setLoteFabricacao("Lote-450");

        Estoque estoque2 = new Estoque();
        estoque2.setQuantidade(new BigDecimal(6750));
        estoque2.setLocalTanque("Tanque 02");
        estoque2.setLocalEndereco("Estoque Reserva");
        estoque2.setLoteFabricacao("Lote-490");

        Estoque estoque3 = new Estoque();
        estoque3.setQuantidade(new BigDecimal(4820));
        estoque3.setLocalTanque("Tanque 03");
        estoque3.setLocalEndereco("Estoque Subterrâneo");
        estoque3.setLoteFabricacao("Lote-467");

        Produto produto1 = new Produto();
        produto1.setNome("Gasolina Comum");
        produto1.setReferencia("GCOM");
        produto1.setFornecedor("Petrobras");
        produto1.setCategoria("Combustível");
        produto1.setMarca("Petrobras");

        Produto produto2 = new Produto();
        produto2.setNome("Gasolina Aditivada");
        produto2.setReferencia("GADT");
        produto2.setFornecedor("Petrobras");
        produto2.setCategoria("Combustível");
        produto2.setMarca("Petrobras");

        Produto produto3 = new Produto();
        produto3.setNome("Etanol");
        produto3.setReferencia("ETAN--001");
        produto3.setFornecedor("Uniao");
        produto3.setCategoria("Combustível");
        produto3.setMarca("Uniao");

        Produto produto4 = new Produto();
        produto4.setNome("Diesel");
        produto4.setReferencia("DIESEL--003");
        produto4.setFornecedor("Shell");
        produto4.setCategoria("Combustível");
        produto4.setMarca("Shell");

        Preco preco1 = new Preco();
        preco1.setValor(new BigDecimal(3.75));
        preco1.setHoraAlteracao();
        preco1.setDataAlteracao();


        System.out.println("Frentista 01");

        System.out.println("Nome Completo: " + pessoa1.getNomeCompleto());
      System.out.println("CPF/CNPJ" + pessoa1.getCpfCnpj());
      System.out.println("NumeroCtps" + pessoa1.getNumeroCtps());
      System.out.println("Telefone: " + contato1.getTelefone());
      System.out.println("Email: " + contato1.getEmail());
      System.out.println("Endereco: " + contato1.getEndereco());
      System.out.println("Usuario: " + acesso1.getUsuario());
      System.out.println("Senha: "+ acesso1.getSenha());

      System.out.println("-----------------------------------------------------------------");

        System.out.println("Frentista 02");

      System.out.println("Nome Completo: " +pessoa2.getNomeCompleto());
      System.out.println("CPF/CNPJ: " +pessoa2.getCpfCnpj());
      System.out.println("NumeroCtps: " +pessoa2.getNumeroCtps());
      System.out.println("Telefone: " + contato2.getTelefone());
      System.out.println("Email: " + contato2.getEmail());
      System.out.println("Endereco: " + contato2.getEndereco());
      System.out.println("Usuario: " + acesso2.getUsuario());
      System.out.println("Senha: "+ acesso2.getSenha());

      System.out.println("-----------------------------------------------------------------");

        System.out.println("Frentista 03");
      System.out.println("Nome Completo: " +pessoa3.getNomeCompleto());
      System.out.println("CPF/CNPJ: " +pessoa3.getCpfCnpj());
      System.out.println("NumeroCtps: " +pessoa3.getNumeroCtps());
      System.out.println("Telefone: " + contato3.getTelefone());
      System.out.println("Email: " + contato3.getEmail());
      System.out.println("Endereco: " + contato3.getEndereco());
      System.out.println("Usuario: " + acesso3.getUsuario());
      System.out.println("Senha: "+ acesso3.getSenha());

      System.out.println("-----------------------------------------------------------------");
      System.out.println("Custos do Posto");
      System.out.println("Imposto: R$" + custo1.getImposto());
      System.out.println("Custo Variavel: R$" + custo1.getCustoVariavel());
      System.out.println("Custo Fixo: R$" + custo1.getCustoFixo());
      System.out.println("Margem de Lucro: R$" + custo1.getMargemLucro());

      System.out.println("-----------------------------------------------------------------");
      System.out.println("Estoque");
      System.out.println("Quantidade: " +estoque1.getQuantidade());
      System.out.println("Local do Tanque: " +estoque1.getLocalTanque());
      System.out.println("Local do Endereco: " +estoque1.getLocalEndereco());
      System.out.println("Lote de Fabricacao: " +estoque1.getLoteFabricacao());
      System.out.println("");
      System.out.println("Quantidade: " +estoque2.getQuantidade());
      System.out.println("Local do Tanque: " +estoque2.getLocalTanque());
      System.out.println("Local do Endereco: " +estoque2.getLocalEndereco());
      System.out.println("Lote de Fabricacao: " +estoque2.getLoteFabricacao());
      System.out.println("");
      System.out.println("Quantidade: " +estoque3.getQuantidade());
      System.out.println("Local do Tanque: " +estoque3.getLocalTanque());
      System.out.println("Local do Endereco: " +estoque3.getLocalEndereco());
      System.out.println("Lote de Fabricacao: " +estoque3.getLoteFabricacao());

      System.out.println("-----------------------------------------------------------------");
      System.out.println("Produtos no tanque");
      System.out.println("Nome do Produto: " +produto1.getNome());
      System.out.println("Referencia: " +produto1.getReferencia());
      System.out.println("Fornecedor: " +produto1.getFornecedor());
      System.out.println("Categoria: " +produto1.getCategoria());
      System.out.println("Marca: " +produto1.getMarca());
      System.out.println("");
      System.out.println("Nome do Produto: " +produto2.getNome());
      System.out.println("Referencia: " +produto2.getReferencia());
      System.out.println("Fornecedor: " +produto2.getFornecedor());
      System.out.println("Categoria: " +produto2.getCategoria());
      System.out.println("Marca: " +produto2.getMarca());
      System.out.println("");
      System.out.println("Nome do Produto: " +produto3.getNome());
      System.out.println("Referencia: " +produto3.getReferencia());
      System.out.println("Fornecedor: " +produto3.getFornecedor());
      System.out.println("Categoria: " +produto3.getCategoria());
      System.out.println("Marca: " +produto3.getMarca());
      System.out.println("");
      System.out.println("Nome do Produto: " +produto4.getNome());
      System.out.println("Referencia: " +produto4.getReferencia());
      System.out.println("Fornecedor: " +produto4.getFornecedor());
      System.out.println("Categoria: " +produto4.getCategoria());
      System.out.println("Marca: " +produto4.getMarca());


    }
}
