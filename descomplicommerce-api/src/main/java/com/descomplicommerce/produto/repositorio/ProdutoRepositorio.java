package com.descomplicommerce.produto.repositorio;

import com.descomplicommerce.produto.entidade.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> { }
