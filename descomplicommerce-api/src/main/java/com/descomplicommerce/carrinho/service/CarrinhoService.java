package com.descomplicommerce.carrinho.service;

import com.descomplicommerce.carrinho.dto.ItemCarrinhoDTO;
import com.descomplicommerce.carrinho.dto.ReciboCompraDTO;
import com.descomplicommerce.carrinho.entidade.Carrinho;
import com.descomplicommerce.carrinho.entidade.ItemCarrinho;
import com.descomplicommerce.carrinho.repositorio.CarrinhoRepositorio;
import com.descomplicommerce.carrinho.repositorio.ItemCarrinhoRepositorio;
import com.descomplicommerce.produto.entidade.Produto;
import com.descomplicommerce.produto.repositorio.ProdutoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarrinhoService {

    private final CarrinhoRepositorio carrinhoRepo;
    private final ItemCarrinhoRepositorio itemRepo;
    private final ProdutoRepositorio produtoRepo;

    private static final String CHAVE = "default";

    public CarrinhoService(CarrinhoRepositorio carrinhoRepo,
                           ItemCarrinhoRepositorio itemRepo,
                           ProdutoRepositorio produtoRepo) {
        this.carrinhoRepo = carrinhoRepo;
        this.itemRepo = itemRepo;
        this.produtoRepo = produtoRepo;
    }

    private Carrinho obterOuCriar() {
        return carrinhoRepo.findByChave(CHAVE).orElseGet(() -> carrinhoRepo.save(new Carrinho(CHAVE)));
    }

    public List<ItemCarrinhoDTO> listar() {
        Carrinho c = obterOuCriar();
        return c.getItens().stream().map(ic -> {
            ItemCarrinhoDTO dto = new ItemCarrinhoDTO();
            dto.produtoId = ic.getProduto().getId();
            dto.nome = ic.getProduto().getNome();
            dto.precoUnitario = ic.getProduto().getPreco();
            dto.quantidade = ic.getQuantidade();
            dto.totalLinha = dto.precoUnitario.multiply(BigDecimal.valueOf(dto.quantidade));
            return dto;
        }).collect(Collectors.toList());
    }

    public void adicionar(Long produtoId, int quantidade) {
        Carrinho c = obterOuCriar();
        Produto p = produtoRepo.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + produtoId));

        ItemCarrinho existente = itemRepo.findByCarrinhoAndProduto(c, p).orElse(null);
        if (existente == null) {
            ItemCarrinho novo = new ItemCarrinho(p, quantidade);
            c.adicionar(novo);
        } else {
            existente.setQuantidade(existente.getQuantidade() + quantidade);
        }
        carrinhoRepo.save(c);
    }

    public void atualizarQuantidade(Long produtoId, int quantidade) {
        Carrinho c = obterOuCriar();
        Produto p = produtoRepo.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + produtoId));
        ItemCarrinho item = itemRepo.findByCarrinhoAndProduto(c, p)
                .orElseThrow(() -> new IllegalArgumentException("Item não está no carrinho"));

        if (quantidade <= 0) {
            c.remover(item);
        } else {
            item.setQuantidade(quantidade);
        }
        carrinhoRepo.save(c);
    }

    public void remover(Long produtoId) {
        Carrinho c = obterOuCriar();
        Produto p = produtoRepo.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + produtoId));
        ItemCarrinho item = itemRepo.findByCarrinhoAndProduto(c, p).orElse(null);
        if (item != null) c.remover(item);
        carrinhoRepo.save(c);
    }

    public void limpar() {
        Carrinho c = obterOuCriar();
        c.getItens().forEach(i -> i.setCarrinho(null));
        c.getItens().clear();
        carrinhoRepo.save(c);
    }

    public BigDecimal total() {
        return listar().stream()
                .map(i -> i.totalLinha)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public ReciboCompraDTO finalizar() {
        // monta recibo com os itens atuais
        var itens = listar(); // já monta ItemCarrinhoDTO
        ReciboCompraDTO r = new ReciboCompraDTO();
        r.itens = itens;
        r.total = itens.stream().map(i -> i.totalLinha)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        r.mensagem = "Compra finalizada com sucesso!";
        // esvazia carrinho
        limpar();
        return r;
    }
}
