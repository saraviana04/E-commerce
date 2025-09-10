package com.descomplicommerce.carrinho.repositorio;

import com.descomplicommerce.carrinho.entidade.Carrinho;
import com.descomplicommerce.carrinho.entidade.ItemCarrinho;
import com.descomplicommerce.produto.entidade.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCarrinhoRepositorio extends JpaRepository<ItemCarrinho, Long> {
    Optional<ItemCarrinho> findByCarrinhoAndProduto(Carrinho carrinho, Produto produto);
}
