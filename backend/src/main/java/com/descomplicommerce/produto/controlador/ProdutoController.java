package com.descomplicommerce.produto.controlador;

import com.descomplicommerce.produto.entidade.Produto;
import com.descomplicommerce.produto.servico.ProdutoServico;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoServico servico;
    public ProdutoController(ProdutoServico servico) { this.servico = servico; }

    @GetMapping public List<Produto> listar() { return servico.listar(); }
    @PostMapping public Produto criar(@RequestBody Produto p) { return servico.criar(p); }
}
