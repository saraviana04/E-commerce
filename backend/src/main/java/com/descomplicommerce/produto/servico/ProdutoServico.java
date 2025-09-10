package com.descomplicommerce.produto.servico;

import com.descomplicommerce.produto.repositorio.ProdutoRepositorio;
import com.descomplicommerce.produto.entidade.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServico {
    private final ProdutoRepositorio repo;
    public ProdutoServico(ProdutoRepositorio repo) { this.repo = repo; }

    public List<Produto> listar() { return repo.findAll(); }
    public Produto criar(Produto p) { return repo.save(p); }
}
