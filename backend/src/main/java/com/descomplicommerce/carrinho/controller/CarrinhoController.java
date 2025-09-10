package com.descomplicommerce.carrinho.controller;

import com.descomplicommerce.carrinho.dto.AdicionarItemRequest;
import com.descomplicommerce.carrinho.dto.ItemCarrinhoDTO;
import com.descomplicommerce.carrinho.dto.ReciboCompraDTO;
import com.descomplicommerce.carrinho.service.CarrinhoService; // <- service (INGLÊS)
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {

    private final CarrinhoService servico;

    public CarrinhoController(CarrinhoService servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<ItemCarrinhoDTO> listar() { return servico.listar(); }

    @PostMapping("/adicionar")
    public void adicionar(@Valid @RequestBody AdicionarItemRequest req) {
        servico.adicionar(req.produtoId, req.quantidade);
    }

    @PatchMapping("/{produtoId}")
    public void atualizar(@PathVariable Long produtoId, @RequestParam int quantidade) {
        servico.atualizarQuantidade(produtoId, quantidade);
    }

    @DeleteMapping("/{produtoId}")
    public void remover(@PathVariable Long produtoId) { servico.remover(produtoId); }

    @DeleteMapping
    public void limpar() { servico.limpar(); }

    @GetMapping("/total")
    public BigDecimal total() { return servico.total(); }

    @PostMapping("/finalizar")
    public ReciboCompraDTO finalizar() {
        return servico.finalizar();
    }
}
